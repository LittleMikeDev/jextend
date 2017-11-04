package uk.co.littlemike.jextend;

import uk.co.littlemike.jextend.impl.Extender;
import uk.co.littlemike.jextend.impl.ExtensionConfiguration;
import uk.co.littlemike.jextend.impl.NoImplementationOnClasspathException;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.function.Function;

public class JExtend {

    private JExtend() {
    }

    private static Extender extender;
    private static Function<Class<Extender>, Iterable<Extender>> serviceLoader = ServiceLoader::load;

    static {
        resetServiceLoader();
    }

    @VisibleForTesting
    static void resetServiceLoader() {
        setServiceLoader(ServiceLoader::load);
    }

    @VisibleForTesting
    static void setServiceLoader(Function<Class<Extender>, Iterable<Extender>> serviceLoader) {
        extender = null;
        JExtend.serviceLoader = serviceLoader;
    }

    public static <C, E extends C> Extension<C, E> getExtension(Class<C> baseClass, Class<E> extensionInterface) {
        if (extender == null) {
            loadImplementation(baseClass, extensionInterface);
        }

        return extender.getExtension(new ExtensionConfiguration<>(baseClass, extensionInterface));
    }

    private static void loadImplementation(Class<?> baseClass, Class<?> extensionInterface) {
        List<Extender> implementations = new ArrayList<>();
        serviceLoader.apply(Extender.class).forEach(implementations::add);
        if (implementations.isEmpty()) {
            throw new NoImplementationOnClasspathException(baseClass, extensionInterface);
        }
        extender = implementations.get(0);
    }

}
