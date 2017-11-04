package uk.co.littlemike.jextend;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.littlemike.jextend.JExtend.resetServiceLoader;

@SuppressWarnings("unchecked")
public abstract class BaseExtensionTest {

    List list = new ArrayList();

    @BeforeClass
    public static void unloadImplementation() {
        resetServiceLoader();
    }

    @Test
    public void canExtendUsingImplementationOnClasspath() {
        ListExtension extendedList = extendListWith(ListExtension.class);

        assertThat(extendedList).isNotNull();
    }

    @Test
    public void delegatesMethodCallsToUnderlyingObject() {
        ListExtension extendedList = extendListWith(ListExtension.class);

        list.add("Direct");
        extendedList.add("Proxy");
        assertThat(list).containsExactly("Direct", "Proxy");
        assertThat(extendedList).containsExactly("Direct", "Proxy");
    }

    private <E extends List> E extendListWith(Class<E> extensionInterface) {
        Extension<List, E> extension = JExtend.getExtension(List.class, extensionInterface);
        return extension.extend(list);
    }
}
