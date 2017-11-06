package uk.co.littlemike.jextend.impl.jdk;

import uk.co.littlemike.jextend.Extension;
import uk.co.littlemike.jextend.impl.ExtensionConfiguration;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class JdkProxyExtension<C, E extends C> implements Extension<C, E> {

    private final Class<E> extensionInterface;
    private final Map<Method, MethodHandle> delegateMethodBindings = new HashMap<>();
    private final Map<Method, MethodHandle> defaultMethodBindings = new HashMap<>();

    public JdkProxyExtension(ExtensionConfiguration<C, E> configuration) {
        extensionInterface = configuration.getExtensionInterface();

        MethodLookup delegateLookup = new MethodLookup(configuration.getBaseClass());
        configuration.getDelegateMethods().forEach(method ->
                delegateMethodBindings.put(method, delegateLookup.lookup(method))
        );
        PrivilegedMethodLookup privilegedLookup = new PrivilegedMethodLookup(configuration.getExtensionInterface());
        configuration.getDefaultMethods().forEach(method ->
                defaultMethodBindings.put(method, privilegedLookup.lookupDefault(method))
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public E extend(C object) {
        return new JdkProxyBuilder<>(
                object,
                extensionInterface,
                delegateMethodBindings,
                defaultMethodBindings
        ).getProxy();
    }
}
