package uk.co.littlemike.jextend.validation;

public class ExtensionClassMustBeAnInterface extends RuntimeException {
    public ExtensionClassMustBeAnInterface(Class<?> baseClass, Class<?> extensionInterface) {
        super(String.format(
                "Unable to extend base class %s with extension %s, the extension must be an interface.",
                baseClass.getName(),
                extensionInterface.getName()
        ));
    }
}
