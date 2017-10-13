package uk.co.littlemike.jextend;

public class JExtendException extends RuntimeException {

    public JExtendException(Class<?> baseClass, Class<?> extensionInterface, String errorMessage) {
        super(String.format(
                "Unable to extend base class %s with extension %s. %s",
                baseClass.getName(),
                extensionInterface.getName(),
                errorMessage
        ));
    }
}
