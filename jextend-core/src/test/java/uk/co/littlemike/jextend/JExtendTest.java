package uk.co.littlemike.jextend;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JExtendTest extends BaseJExtendTest {

    private StubExtension<List, ListExtension> returnedExtension;

    @Test
    public void returnsExtensionForBaseClassAndExtensionInterface() {
        returnedExtension = getExtension(List.class, ListExtension.class);

        assertThat(returnedExtension.getBaseClass()).isEqualTo(List.class);
        assertThat(returnedExtension.getExtensionInterface()).isEqualTo(ListExtension.class);
    }

    @Test
    public void listsDelegateMethodsInConfiguration() throws Exception {
        returnedExtension = getExtension(List.class, ListExtension.class);

        assertThat(returnedExtension.getDelegateMethods()).contains(
                List.class.getMethod("size"),
                List.class.getMethod("add", Object.class)
        );
        assertThat(returnedExtension.getDelegateMethods()).doesNotContain(
                ListExtension.class.getMethod("isNotEmpty")
        );
    }

    @Test
    public void listsDefaultMethodsInConfiguration() throws Exception {
        returnedExtension = getExtension(List.class, ListExtension.class);

        assertThat(returnedExtension.getDefaultMethods()).contains(
                ListExtension.class.getMethod("isNotEmpty")
        );
        assertThat(returnedExtension.getDefaultMethods()).doesNotContain(
                List.class.getMethod("isEmpty")
        );
    }
}
