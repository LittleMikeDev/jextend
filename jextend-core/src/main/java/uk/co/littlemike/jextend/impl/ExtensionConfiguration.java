package uk.co.littlemike.jextend.impl;

import uk.co.littlemike.jextend.validation.ExtensionClassNotAnInterfaceException;
import uk.co.littlemike.jextend.validation.UnimplementedExtensionMethodException;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class ExtensionConfiguration<C, E extends C> {
    private final Class<C> baseClass;
    private final Class<E> extensionInterface;
    private Set<Method> delegateMethods = new HashSet<>();
    private Set<Method> defaultMethods = new HashSet<>();
    private Set<Method> unimplementedMethods = new HashSet<>();

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
        } else if (method.isDefault()) {
            defaultMethods.add(method);
        } else {
            unimplementedMethods.add(method);
        }
    }

    private boolean isInBaseClass(Method m) {
        return m.getDeclaringClass().isAssignableFrom(baseClass);
    }

    private void validate() {
        if (!extensionInterface.isInterface()) {
            throw new ExtensionClassNotAnInterfaceException(baseClass, extensionInterface);
        }
        if (!unimplementedMethods.isEmpty()) {
            throw new UnimplementedExtensionMethodException(baseClass, extensionInterface, unimplementedMethods);
        }
    }

    public Class<C> getBaseClass() {
        return baseClass;
    }

    public Class<E> getExtensionInterface() {
        return extensionInterface;
    }

    public Set<Method> getDelegateMethods() {
        return delegateMethods;
    }

    public Set<Method> getDefaultMethods() {
        return defaultMethods;
    }
}
