package uk.co.littlemike.jextend.impl;

import uk.co.littlemike.jextend.validation.ExtensionClassNotAnInterfaceException;
import uk.co.littlemike.jextend.validation.UnimplementedExtensionMethodException;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class ExtensionConfiguration<C, E extends C> {
    private final Class<C> baseClass;
    private final Class<E> extensionInterface;
    private Set<Method> delegateMethods = new HashSet<>();

    public ExtensionConfiguration(Class<C> baseClass, Class<E> extensionInterface) {
        this.baseClass = baseClass;
        this.extensionInterface = extensionInterface;
        for (Method method : extensionInterface.getMethods()) {
            categorizeMethod(method);
        }
        validate();
    }

    private void categorizeMethod(Method method) {
        if (isInBaseClass(method)) {
            delegateMethods.add(method);
        }
    }

    public Class<C> getBaseClass() {
        return baseClass;
    }

    public Class<E> getExtensionInterface() {
        return extensionInterface;
    }

    private void validate() {
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

    public Set<Method> getDelegateMethods() {
        return delegateMethods;
    }
}
