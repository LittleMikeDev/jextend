package uk.co.littlemike.jextend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.Serializable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static uk.co.littlemike.jextend.JExtend.extend;
import static uk.co.littlemike.jextend.JExtend.setExtender;

@RunWith(MockitoJUnitRunner.class)
public class JExtendTest {

    @Mock
    Extender extender;

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
        Serializable extendedObject = new Serializable() {
        };
        when(extender.extend(object, Serializable.class)).thenReturn(extendedObject);

        Serializable returnedObject = extend(object, Serializable.class);

        assertThat(returnedObject).isSameAs(extendedObject);
    }

    @Test(expected = NoImplementationOnClasspathException.class)
    public void throwsExceptionIfNoImplementationAvailableOnClasspath() {
        setExtender(null);

        extend(new Object(), Serializable.class);
    }
}
