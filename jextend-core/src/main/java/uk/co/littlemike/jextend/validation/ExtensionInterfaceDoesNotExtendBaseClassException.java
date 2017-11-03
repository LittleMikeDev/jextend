package uk.co.littlemike.jextend.validation;

import uk.co.littlemike.jextend.JExtendException;

public class ExtensionInterfaceDoesNotExtendBaseClassException extends JExtendException {

    public ExtensionInterfaceDoesNotExtendBaseClassException(Class<?> baseClass, Class<?> extensionInterface) {
        super(baseClass, extensionInterface, String.format(
                "Extension interface %s does not extend base class %s",
                extensionInterface, baseClass
        ));
    }
}
