package uk.co.littlemike.jextend;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public abstract class BaseExtensionTest {

    @BeforeClass
    public static void unloadImplementation() {
        JExtend.setExtender(null);
    }

    @Test
    public void canExtendUsingImplementationOnClasspath() {
        JExtend.getExtension(List.class, ListExtension.class);
    }
}
