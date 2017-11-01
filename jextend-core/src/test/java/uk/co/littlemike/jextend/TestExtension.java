package uk.co.littlemike.jextend;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestExtension<C, E> implements Extension<C, E> {
    private static final InvocationHandler noOpInvocationHandler = (proxy, method, args) -> null;

    private final Class<C> baseClass;
    private final Class<E> extensionInterface;

    public TestExtension(Class<C> baseClass, Class<E> extensionInterface) {
        this.baseClass = baseClass;
        this.extensionInterface = extensionInterface;
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
