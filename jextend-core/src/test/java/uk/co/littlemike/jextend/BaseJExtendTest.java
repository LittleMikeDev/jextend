package uk.co.littlemike.jextend;

import org.junit.After;
import org.junit.Before;

import static java.util.Collections.singleton;
import static uk.co.littlemike.jextend.JExtend.resetServiceLoader;
import static uk.co.littlemike.jextend.JExtend.setServiceLoader;

/**
 * Ensures that a test extender implementation is set during test run
 */
public class BaseJExtendTest {

    @Before
    public void useDefaultStubExtender() {
        setServiceLoader(clazz -> singleton(new StubExtender()));
    }

    @After
    public void autodetectExtender() {
        resetServiceLoader();
    }

    protected <C, E extends C> StubExtension<C, E> getExtension(Class<C> baseClass, Class<E> extensionInterface) {
        return (StubExtension<C, E>) JExtend.getExtension(baseClass, extensionInterface);
    }
}
