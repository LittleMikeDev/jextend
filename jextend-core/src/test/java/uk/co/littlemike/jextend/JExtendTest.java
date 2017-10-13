package uk.co.littlemike.jextend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.jextend.impl.Extender;
import uk.co.littlemike.jextend.impl.NoImplementationOnClasspathException;
import uk.co.littlemike.jextend.impl.TestExtender;

import java.io.Serializable;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.littlemike.jextend.JExtend.extend;
import static uk.co.littlemike.jextend.JExtend.setExtender;

public class JExtendTest {

    Extender extender = new TestExtender();

    @Before
    public void overrideExtender() {
        setExtender(extender);
    }

    @After
    public void resetExtender() {
        setExtender(null);
    }

    @Test
    public void returnsExtendedObject() {
        Object object = new Object();

        Serializable returnedObject = extend(object, Serializable.class);

        assertThat(returnedObject).isInstanceOf(Serializable.class);
    }

    @Test(expected = NoImplementationOnClasspathException.class)
    public void throwsExceptionIfNoImplementationAvailableOnClasspath() {
        setExtender(null);

        extend(new Object(), Serializable.class);
    }
}
