package uk.co.littlemike.jextend;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.littlemike.jextend.JExtend.resetServiceLoader;

public abstract class BaseExtensionTest {

    @BeforeClass
    public static void unloadImplementation() {
        resetServiceLoader();
    }

    @Test
    public void canExtendUsingImplementationOnClasspath() {
        List list = new ArrayList();
        Extension<List, ListExtension> extension = JExtend.getExtension(List.class, ListExtension.class);

        ListExtension extendedList = extension.extend(list);

        assertThat(extendedList).isNotNull();
    }
}
