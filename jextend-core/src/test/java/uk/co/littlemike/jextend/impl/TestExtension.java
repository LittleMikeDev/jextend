package uk.co.littlemike.jextend.impl;

import uk.co.littlemike.jextend.Extension;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestExtension<C, E> implements Extension<C, E> {
    private static final InvocationHandler noOpInvocationHandler = (proxy, method, args) -> null;
    private final Class<E> extensionClass;

    public TestExtension(Class<E> extensionClass) {
        this.extensionClass = extensionClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E extend(C object) {
        return (E) Proxy.newProxyInstance(
                TestExtension.class.getClassLoader(),
                new Class[] { extensionClass },
                noOpInvocationHandler
        );
    }
}
