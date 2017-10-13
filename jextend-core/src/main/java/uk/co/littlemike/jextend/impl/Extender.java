package uk.co.littlemike.jextend.impl;

import uk.co.littlemike.jextend.Extension;

public interface Extender {

    <C, E> Extension<C, E> getExtension(Class<C> baseClass, Class<E> extensionInterface);
}
