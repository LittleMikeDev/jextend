package uk.co.littlemike.jextend;

import org.junit.After;
import org.junit.Before;

import static uk.co.littlemike.jextend.JExtend.setExtender;

/**
 * Ensures that a test extender implementation is set during test run
 */
public class BaseJExtendTest {

    @Before
    public void useDefaultTestExtender() {
        setExtender(new StubExtender());
    }

    @After
    public void autodetectExtender() {
        setExtender(null);
    }

    protected <C, E extends C> StubExtension<C, E> getExtension(Class<C> baseClass, Class<E> extensionInterface) {
        return (StubExtension<C, E>) JExtend.getExtension(baseClass, extensionInterface);
    }
}
