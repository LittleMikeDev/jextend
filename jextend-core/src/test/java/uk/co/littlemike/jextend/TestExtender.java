package uk.co.littlemike.jextend;

import uk.co.littlemike.jextend.impl.Extender;

public class TestExtender implements Extender {

    @Override
    public <C, E extends C> Extension<C, E> getExtension(Class<C> baseClass, Class<E> extensionInterface) {
        return new TestExtension<>(extensionInterface);
    }
}
