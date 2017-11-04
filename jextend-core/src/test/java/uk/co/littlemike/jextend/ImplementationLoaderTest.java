package uk.co.littlemike.jextend;

import org.junit.After;
import org.junit.Test;
import uk.co.littlemike.jextend.impl.MultipleImplementationsOnClasspathException;
import uk.co.littlemike.jextend.impl.NoImplementationOnClasspathException;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singleton;
import static uk.co.littlemike.jextend.JExtend.*;

public class ImplementationLoaderTest {

    @After
    public void autodetectExtender() {
        resetServiceLoader();
    }

    @Test(expected = NoImplementationOnClasspathException.class)
    public void throwsExceptionIfNoImplementationAvailableOnClasspath() {
        getExtension(List.class, ListExtension.class);
    }

    @Test
    public void usesImplementationRepeatedlyWhenOneAvailableOnClasspath() {
        setServiceLoader(c -> singleton(new StubExtender()));

        getExtension(List.class, ListExtension.class);
        getExtension(List.class, ListExtension.class);
    }

    @Test(expected = MultipleImplementationsOnClasspathException.class)
    public void throwsExceptionWhenMultipleImplementationsAreAvailableOnClasspath() {
        setServiceLoader(c -> Arrays.asList(new StubExtender(), new StubExtender()));

        getExtension(List.class, ListExtension.class);
    }
}
