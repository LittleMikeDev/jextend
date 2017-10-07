package uk.co.littlemike.jextend;

public class NoImplementationOnClasspathException extends RuntimeException {
    public NoImplementationOnClasspathException(Class<?> objectClass, Class<?> extensionInterface) {
        super(String.format(
                "Unable to extend object of type %s with interface %s, there is no implementation of JExtend " +
                "available on the classpath.",
                objectClass.getName(),
                extensionInterface.getName()
        ));
    }
}
