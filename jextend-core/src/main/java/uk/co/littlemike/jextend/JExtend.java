package uk.co.littlemike.jextend;

import uk.co.littlemike.jextend.impl.Extender;
import uk.co.littlemike.jextend.impl.NoImplementationOnClasspathException;
import uk.co.littlemike.jextend.validation.ExtensionClassMustBeAnInterface;

public class JExtend {

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
        return extender.getExtension(baseClass, extensionInterface);
    }
}
