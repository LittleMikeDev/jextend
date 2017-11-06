package uk.co.littlemike.jextend.impl.jdk;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class JdkInvocationHandler<E> implements InvocationHandler {

    private final E proxy;
    private final Map<Method, MethodHandle> methodBindings = new HashMap<>();

    @SuppressWarnings("unchecked")
    public JdkInvocationHandler(Object delegate, Class<E> extensionInterface, Map<Method, MethodHandle> delegateMethodBindings) {
        proxy = (E) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[] { extensionInterface },
                this
        );

        delegateMethodBindings.forEach((method, handle) -> methodBindings.put(method, handle.bindTo(delegate)));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return methodBindings.get(method).invokeWithArguments(args);
    }

    E getProxy() {
        return proxy;
    }
}
