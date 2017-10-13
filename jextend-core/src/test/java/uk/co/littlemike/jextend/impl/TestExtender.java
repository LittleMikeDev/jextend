package uk.co.littlemike.jextend.impl;

import uk.co.littlemike.jextend.Extension;
import uk.co.littlemike.jextend.TestExtension;

public class TestExtender implements Extender {

    @Override
    public <C, E> Extension<C, E> getExtension(Class<C> baseClass, Class<E> extensionInterface) {
        return new TestExtension<>(extensionInterface);
    }
}
