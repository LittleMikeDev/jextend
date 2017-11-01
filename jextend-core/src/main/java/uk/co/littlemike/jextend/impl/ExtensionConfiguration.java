package uk.co.littlemike.jextend.impl;

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
}
