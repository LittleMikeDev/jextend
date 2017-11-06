package uk.co.littlemike.jextend.impl.jdk;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class JdkProxyBuilder<E> {

    private final E proxy;
    private final Map<Method, MethodHandle> methodBindings = new HashMap<>();

    @SuppressWarnings("unchecked")
    public JdkProxyBuilder(
            Object delegate,
            Class<E> extensionInterface,
            Map<Method, MethodHandle> delegateMethodBindings,
            Map<Method, MethodHandle> defaultMethodBindings
    ) {
        proxy = (E) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[] { extensionInterface },
                (proxy, method, args) -> invoke(method, args)
        );

        delegateMethodBindings.forEach((method, handle) -> methodBindings.put(method, handle.bindTo(delegate)));
        defaultMethodBindings.forEach((method, handle) -> methodBindings.put(method, handle.bindTo(proxy)));
    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        return methodBindings.get(method).invokeWithArguments(args);
    }

    public E getProxy() {
        return proxy;
    }
}
