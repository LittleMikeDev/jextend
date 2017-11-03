package uk.co.littlemike.jextend.impl.jdk;

import uk.co.littlemike.jextend.Extension;
import uk.co.littlemike.jextend.impl.Extender;
import uk.co.littlemike.jextend.impl.ExtensionConfiguration;

public class JdkExtender implements Extender {

    @Override
    public <C, E extends C> Extension<C, E> getExtension(ExtensionConfiguration<C, E> configuration) {
        return null;
    }
}
