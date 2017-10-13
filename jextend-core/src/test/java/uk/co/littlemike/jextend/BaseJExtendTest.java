package uk.co.littlemike.jextend;

import org.junit.After;
import org.junit.Before;
import uk.co.littlemike.jextend.impl.Extender;

import static org.mockito.Mockito.mock;
import static uk.co.littlemike.jextend.JExtend.setExtender;

/**
 * Ensures that a (fake) extender implementation is set during test run
 */
public class BaseJExtendTest {

    protected Extender extender = mock(Extender.class);

    @Before
    public void overrideExtender() {
        setExtender(extender);
    }

    @After
    public void resetExtender() {
        setExtender(null);
    }
}
