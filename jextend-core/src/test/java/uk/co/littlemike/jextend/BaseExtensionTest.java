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
    
    interface TalkingList extends List {
        default String greeting() {
            return "Hello world!";
        }

        default String sizeDescription() { return "My size is " + size(); }
    }

    @Test
    public void executesDefaultMethod() {
        TalkingList talkingList = extendListWith(TalkingList.class);

        assertThat(talkingList.greeting()).isEqualTo("Hello world!");
    }

    @Test
    public void defaultMethodMayCallDelegateMethod() {
        TalkingList talkingList = extendListWith(TalkingList.class);
        talkingList.add("Item");

        assertThat(talkingList.sizeDescription()).isEqualTo("My size is 1");
    }

    public interface Base {
        default String getName() {
            return "Base";
        }

        default String sayName() {
            return "I am " + getName();
        }
    }

    public interface Extension extends Base {
        @Override
        default String getName() {
            return "Extension";
        }
    }

    @Test
    public void delegatedMethodCallDoesNotCallOverriddenMethod() {
        Base base = new Base() {};
        Extension extension = JExtend.getExtension(Base.class, Extension.class).extend(base);

        assertThat(base.getName()).isEqualTo("Base");
        assertThat(base.sayName()).isEqualTo("I am Base");
        assertThat(extension.getName()).isEqualTo("Extension");
        assertThat(extension.sayName()).isEqualTo("I am Base"); // NOTE!
    }

    private <E extends List> E extendListWith(Class<E> extensionInterface) {
        return JExtend.getExtension(List.class, extensionInterface).extend(list);
    }
}
