package uk.co.littlemike.jextend.impl;

import uk.co.littlemike.jextend.validation.ExtensionClassNotAnInterfaceException;
import uk.co.littlemike.jextend.validation.UnimplementedExtensionMethodException;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExtensionConfiguration<C, E extends C> {
    private final Class<C> baseClass;
    private final Class<E> extensionInterface;

    public ExtensionConfiguration(Class<C> baseClass, Class<E> extensionInterface) {
        this.baseClass = baseClass;
        this.extensionInterface = extensionInterface;
    }

    public Class<C> getBaseClass() {
        return baseClass;
    }

    public Class<E> getExtensionInterface() {
        return extensionInterface;
    }

    public void validate() {
        if (!extensionInterface.isInterface()) {
            throw new ExtensionClassNotAnInterfaceException(baseClass, extensionInterface);
        }

        List<Method> unimplementedMethods = Arrays.stream(extensionInterface.getMethods())
                .filter(m -> !isInBaseClass(m))
                .filter(m -> !m.isDefault())
                .collect(toList());
        if (!unimplementedMethods.isEmpty()) {
            throw new UnimplementedExtensionMethodException(baseClass, extensionInterface, unimplementedMethods);
        }
    }

    private boolean isInBaseClass(Method m) {
        return m.getDeclaringClass().isAssignableFrom(baseClass);
    }
}
