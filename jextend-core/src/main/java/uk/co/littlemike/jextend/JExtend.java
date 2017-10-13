package uk.co.littlemike.jextend;

import uk.co.littlemike.jextend.impl.Extender;

public class JExtend {

    private static Extender extender;

    @VisibleForTesting
    static void setExtender(Extender extender) {
        JExtend.extender = extender;
    }

    @SuppressWarnings("unchecked")
    public static <C, E> E extend(C object, Class<E> extensionInterface) {
        if (extender == null) {
            throw new NoImplementationOnClasspathException(object.getClass(), extensionInterface);
        }
        return extender.getExtension((Class<C>) object.getClass(), extensionInterface).extend(object);
    }
}
