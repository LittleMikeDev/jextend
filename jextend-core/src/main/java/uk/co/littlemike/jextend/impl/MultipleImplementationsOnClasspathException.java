package uk.co.littlemike.jextend.impl;

import uk.co.littlemike.jextend.JExtendException;

import java.util.Collection;

import static java.util.stream.Collectors.joining;

public class MultipleImplementationsOnClasspathException extends JExtendException {
    public MultipleImplementationsOnClasspathException(
            Class<?> baseClass,
            Class<?> extensionInterface,
            Collection<Extender> implementations
    ) {
        super(baseClass, extensionInterface, String.format(
                "There are %s implementations of JExtend on the classpath (%s). Please ensure there is only one.",
                implementations.size(),
                implementations.stream()
                        .map(implementation -> implementation.getClass().toString())
                        .collect(joining(", "))
        ));
    }
}
