package uk.co.littlemike.jextend.validation;

import uk.co.littlemike.jextend.JExtendException;

import java.lang.reflect.Method;
import java.util.Collection;

import static java.util.stream.Collectors.joining;

public class UnimplementedExtensionMethodException extends JExtendException {

    public UnimplementedExtensionMethodException(Class<?> baseClass, Class<?> extensionInterface, Collection<Method> methods) {
        super(baseClass, extensionInterface, String.format(
                "Extension has unimplemented methods: %s",
                describe(methods)
        ));
    }

    private static String describe(Collection<Method> methods) {
        return methods.stream()
                .map(Method::toString)
                .collect(joining(", "));
    }
}
