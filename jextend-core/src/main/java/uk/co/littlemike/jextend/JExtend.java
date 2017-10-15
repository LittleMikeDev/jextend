package uk.co.littlemike.jextend;

import uk.co.littlemike.jextend.impl.Extender;
import uk.co.littlemike.jextend.impl.NoImplementationOnClasspathException;
import uk.co.littlemike.jextend.validation.ExtensionClassMustBeAnInterface;
import uk.co.littlemike.jextend.validation.UnimplementedExtensionMethodException;

import java.lang.reflect.Method;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class JExtend {

    private JExtend() {
    }

    private static Extender extender;

    @VisibleForTesting
    static void setExtender(Extender extender) {
        JExtend.extender = extender;
    }

    public static <C, E extends C> Extension<C, E> getExtension(Class<C> baseClass, Class<E> extensionInterface) {
        if (extender == null) {
            throw new NoImplementationOnClasspathException(baseClass, extensionInterface);
        }
        if (!extensionInterface.isInterface()) {
            throw new ExtensionClassMustBeAnInterface(baseClass, extensionInterface);
        }

        List<Method> unimplementedMethods = stream(extensionInterface.getDeclaredMethods())
                .filter(m -> !m.isDefault())
                .collect(toList());
        if (!unimplementedMethods.isEmpty()) {
            throw new UnimplementedExtensionMethodException(baseClass, extensionInterface, unimplementedMethods);
        }

        return extender.getExtension(baseClass, extensionInterface);
    }
}
