package uk.co.littlemike.jextend.validation;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class InterfaceExtensionValidator {
    private final Class<?> baseClass;
    private final Class<?> extensionInterface;

    public InterfaceExtensionValidator(Class<?> baseClass, Class<?> extensionInterface) {
        this.baseClass = baseClass;
        this.extensionInterface = extensionInterface;
    }

    public void enforceValidation() {
        if (!extensionInterface.isInterface()) {
            throw new ExtensionClassNotAnInterfaceException(baseClass, extensionInterface);
        }

        List<Method> unimplementedMethods = Arrays.stream(extensionInterface.getMethods())
                .filter(m -> !isInBaseClass(m))
                .filter(m -> !m.isDefault())
                .collect(toList());
        if (!unimplementedMethods.isEmpty()) {
            throw new UnimplementedExtensionMethodException(baseClass, extensionInterface, unimplementedMethods);
        }
    }

    private boolean isInBaseClass(Method m) {
        return m.getDeclaringClass().isAssignableFrom(baseClass);
    }
}
