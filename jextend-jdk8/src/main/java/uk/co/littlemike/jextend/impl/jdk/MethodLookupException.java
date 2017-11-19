package uk.co.littlemike.jextend.impl.jdk;

import java.lang.reflect.Method;

public class MethodLookupException extends RuntimeException {
    public MethodLookupException(Method method, Class<?> lookupClass, IllegalAccessException e) {
        super(String.format(
                "Failed to obtain method handle for method %s on class %s from lookup class %s",
                method.toString(),
                method.getDeclaringClass().toString(),
                lookupClass.toString()
        ), e);
    }
}
