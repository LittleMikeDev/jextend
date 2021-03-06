package uk.co.littlemike.jextend;

import uk.co.littlemike.jextend.impl.Extender;
import uk.co.littlemike.jextend.impl.ExtensionConfiguration;

public class StubExtender implements Extender {

    @Override
    public <C, E extends C> Extension<C, E> getExtension(ExtensionConfiguration<C, E> configuration) {
        return new StubExtension<>(configuration);
    }
}
