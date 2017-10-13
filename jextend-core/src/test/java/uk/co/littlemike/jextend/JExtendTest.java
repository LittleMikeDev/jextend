package uk.co.littlemike.jextend;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.jextend.impl.Extender;
import uk.co.littlemike.jextend.impl.NoImplementationOnClasspathException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uk.co.littlemike.jextend.JExtend.setExtender;

public class JExtendTest {

    Extender extender = mock(Extender.class);

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
        TestExtension<List, ListExtension> extension = new TestExtension<>(ListExtension.class);
        when(extender.getExtension(List.class, ListExtension.class)).thenReturn(extension);

        Extension<List, ListExtension> returnedExtension = JExtend.getExtension(List.class, ListExtension.class);

        assertThat(returnedExtension).isSameAs(extension);
    }

    @Test(expected = NoImplementationOnClasspathException.class)
    public void throwsExceptionIfNoImplementationAvailableOnClasspath() {
        setExtender(null);

        JExtend.getExtension(List.class, ListExtension.class);
    }
}
