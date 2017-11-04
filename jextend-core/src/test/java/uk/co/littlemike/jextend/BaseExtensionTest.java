package uk.co.littlemike.jextend;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static uk.co.littlemike.jextend.JExtend.resetServiceLoader;

public abstract class BaseExtensionTest {

    @BeforeClass
    public static void unloadImplementation() {
        resetServiceLoader();
    }

    @Test
    public void canExtendUsingImplementationOnClasspath() {
        JExtend.getExtension(List.class, ListExtension.class);
    }
}
