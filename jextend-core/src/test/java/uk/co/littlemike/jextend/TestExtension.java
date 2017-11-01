package uk.co.littlemike.jextend;

import uk.co.littlemike.jextend.impl.ExtensionConfiguration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestExtension<C, E extends C> implements Extension<C, E> {
    private static final InvocationHandler noOpInvocationHandler = (proxy, method, args) -> null;

    private final Class<C> baseClass;
    private final Class<E> extensionInterface;

    public TestExtension(ExtensionConfiguration<C, E> extensionConfiguration) {
        this.baseClass = extensionConfiguration.getBaseClass();
        this.extensionInterface = extensionConfiguration.getExtensionInterface();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E extend(C object) {
        return (E) Proxy.newProxyInstance(
                TestExtension.class.getClassLoader(),
                new Class[] {extensionInterface},
                noOpInvocationHandler
        );
    }

    public Class<C> getBaseClass() {
        return baseClass;
    }

    public Class<E> getExtensionInterface() {
        return extensionInterface;
    }
}
