package uk.co.littlemike.jextend.impl;

import uk.co.littlemike.jextend.Extension;

public interface Extender {

    <C, E extends C> Extension<C, E> getExtension(ExtensionConfiguration<C, E> configuration);
}
