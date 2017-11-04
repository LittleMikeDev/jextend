package uk.co.littlemike.jextend.impl.jdk;

import uk.co.littlemike.jextend.Extension;
import uk.co.littlemike.jextend.impl.ExtensionConfiguration;

import java.lang.reflect.Proxy;

public class JdkProxyExtension<C, E extends C> implements Extension<C, E> {

    private final Class<E> extensionInterface;

    public JdkProxyExtension(ExtensionConfiguration<C, E> configuration) {
        extensionInterface = configuration.getExtensionInterface();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E extend(C object) {
        return (E) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[] { extensionInterface },
                new JdkInvocationHandler()
        );
    }
}
