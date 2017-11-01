package uk.co.littlemike.jextend;

import uk.co.littlemike.jextend.impl.Extender;
import uk.co.littlemike.jextend.impl.ExtensionConfiguration;
import uk.co.littlemike.jextend.impl.NoImplementationOnClasspathException;

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

        ExtensionConfiguration<C, E> configuration = new ExtensionConfiguration<>(baseClass, extensionInterface);
        configuration.validate();
        return extender.getExtension(configuration);
    }

}
