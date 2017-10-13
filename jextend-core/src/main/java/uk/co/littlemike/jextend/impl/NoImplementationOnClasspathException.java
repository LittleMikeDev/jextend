package uk.co.littlemike.jextend.impl;

import uk.co.littlemike.jextend.JExtendException;

public class NoImplementationOnClasspathException extends JExtendException {
    public NoImplementationOnClasspathException(Class<?> baseClass, Class<?> extensionInterface) {
        super(baseClass, extensionInterface, "There is no implementation of JExtend available on the classpath.");
    }
}
