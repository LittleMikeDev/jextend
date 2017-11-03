package uk.co.littlemike.jextend;

import uk.co.littlemike.jextend.impl.Extender;
import uk.co.littlemike.jextend.impl.ExtensionConfiguration;
import uk.co.littlemike.jextend.impl.NoImplementationOnClasspathException;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

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
            loadImplementation(baseClass, extensionInterface);
        }

        return extender.getExtension(new ExtensionConfiguration<>(baseClass, extensionInterface));
    }

    private static void loadImplementation(Class<?> baseClass, Class<?> extensionInterface) {
        List<Extender> implementations = new ArrayList<>();
        ServiceLoader.load(Extender.class).forEach(implementations::add);
        if (implementations.isEmpty()) {
            throw new NoImplementationOnClasspathException(baseClass, extensionInterface);
        }
        extender = implementations.get(0);
    }

}
