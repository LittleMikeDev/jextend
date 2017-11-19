package uk.co.littlemike.jextend.impl.jdk;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PrivilegedMethodLookup {
    private static MethodHandles.Lookup privilegedLookup;
    private final MethodHandles.Lookup lookup;

    public PrivilegedMethodLookup(Class<?> lookupClass) {
        lookup = privilegedLookup().in(lookupClass);
    }

    private static MethodHandles.Lookup privilegedLookup() {
        if (privilegedLookup == null) {
            try {
                Field privilegedLookupField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                privilegedLookupField.setAccessible(true);
                privilegedLookup = (MethodHandles.Lookup) privilegedLookupField.get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new PrivilegedLookupException(e);
            }
        }
        return privilegedLookup;
    }

    public MethodHandle lookup(Method method) {
        try {
            return lookup.unreflect(method);
        } catch (IllegalAccessException e) {
            throw new MethodLookupException(method, lookup.lookupClass(), e);
        }
    }

    public MethodHandle lookupDefault(Method method) {
        try {
            return lookup.unreflectSpecial(method, lookup.lookupClass());
        } catch (IllegalAccessException e) {
            throw new MethodLookupException(method, lookup.lookupClass(), e);
        }
    }
}
