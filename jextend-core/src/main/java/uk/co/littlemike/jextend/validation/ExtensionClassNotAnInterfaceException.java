package uk.co.littlemike.jextend.validation;

import uk.co.littlemike.jextend.JExtendException;

public class ExtensionClassNotAnInterfaceException extends JExtendException {
    public ExtensionClassNotAnInterfaceException(Class<?> baseClass, Class<?> extensionInterface) {
        super(baseClass, extensionInterface, "The extension must be an interface.");
    }
}
