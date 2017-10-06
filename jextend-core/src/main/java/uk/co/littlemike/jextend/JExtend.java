package uk.co.littlemike.jextend;

public class JExtend {

    private static Extender extender;

    @VisibleForTesting
    static void setExtender(Extender extender) {
        JExtend.extender = extender;
    }

    public static <E> E extend(Object object, Class<E> extensionInterface) {
        return extender.extend(object, extensionInterface);
    }
}
