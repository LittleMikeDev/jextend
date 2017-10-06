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

@RunWith(MockitoJUnitRunner.class)
public class JExtendTest {

    @Mock
    Extender extender;

    @Before
    public void overrideExtender() {
        JExtend.setExtender(extender);
    }

    @After
    public void resetExtender() {
        JExtend.setExtender(null);
    }

    @Test
    public void returnsExtendedObject() {
        Object object = new Object();
        Serializable extendedObject = new Serializable() {
        };
        when(extender.extend(object, Serializable.class)).thenReturn(extendedObject);

        Serializable returnedObject = JExtend.extend(object, Serializable.class);

        assertThat(returnedObject).isSameAs(extendedObject);
    }
}
