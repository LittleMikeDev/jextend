package uk.co.littlemike.jextend.impl.jdk;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

public class MethodLookup {

    private final MethodHandles.Lookup lookup;

    public MethodLookup(Class<?> lookupClass) {
        lookup = MethodHandles.lookup().in(lookupClass);
    }

    public MethodHandle lookup(Method method) {
        try {
            return lookup.unreflect(method);
        } catch (IllegalAccessException e) {
            throw new MethodLookupException(method, lookup.lookupClass(), e);
        }
    }
}
