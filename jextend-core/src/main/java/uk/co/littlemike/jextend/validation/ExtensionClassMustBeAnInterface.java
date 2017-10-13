package uk.co.littlemike.jextend.validation;

import uk.co.littlemike.jextend.JExtendException;

public class ExtensionClassMustBeAnInterface extends JExtendException {
    public ExtensionClassMustBeAnInterface(Class<?> baseClass, Class<?> extensionInterface) {
        super(baseClass, extensionInterface, "The extension must be an interface.");
    }
}
