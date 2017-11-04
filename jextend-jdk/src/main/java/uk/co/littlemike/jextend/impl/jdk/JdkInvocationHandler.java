package uk.co.littlemike.jextend.impl.jdk;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class JdkInvocationHandler implements InvocationHandler {

    private final Map<Method, MethodHandle> methodBindings = new HashMap<>();

    public JdkInvocationHandler(Object delegate, Map<Method, MethodHandle> delegateMethodBindings) {
        delegateMethodBindings.forEach((method, handle) -> methodBindings.put(method, handle.bindTo(delegate)));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return methodBindings.get(method).invokeWithArguments(args);
    }
}
