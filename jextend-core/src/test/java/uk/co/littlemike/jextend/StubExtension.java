package uk.co.littlemike.jextend;

import uk.co.littlemike.jextend.impl.ExtensionConfiguration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

public class StubExtension<C, E extends C> implements Extension<C, E> {
    private static final InvocationHandler noOpInvocationHandler = (proxy, method, args) -> null;

    private final ExtensionConfiguration<C, E> configuration;

    public StubExtension(ExtensionConfiguration<C, E> configuration) {
        this.configuration = configuration;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E extend(C object) {
        return (E) Proxy.newProxyInstance(
                StubExtension.class.getClassLoader(),
                new Class[] { configuration.getExtensionInterface() },
                noOpInvocationHandler
        );
    }

    public Class<C> getBaseClass() {
        return configuration.getBaseClass();
    }

    public Class<E> getExtensionInterface() {
        return configuration.getExtensionInterface();
    }

    public Set<Method> getDelegateMethods() {
        return configuration.getDelegateMethods();
    }

    public Set<Method> getDefaultMethods() {
        return configuration.getDefaultMethods();
    }
}
