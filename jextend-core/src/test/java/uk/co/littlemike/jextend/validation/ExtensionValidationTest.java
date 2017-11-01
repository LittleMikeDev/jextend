package uk.co.littlemike.jextend.validation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.co.littlemike.jextend.BaseJExtendTest;

import java.util.ArrayList;
import java.util.List;

public class ExtensionValidationTest extends BaseJExtendTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throwsExceptionWhenExtensionIsNotAnInterface() {
        exception.expect(ExtensionClassNotAnInterfaceException.class);
        exception.expectMessage("Object");
        exception.expectMessage("ArrayList");

        getExtension(Object.class, ArrayList.class);
    }

    interface UnimplementedMethod extends List {
        void doStuff();
        String doStuff2(int num);
    }

    @Test
    public void throwsExceptionIfExtensionInterfaceHasANonDefaultMethod() {
        exception.expect(UnimplementedExtensionMethodException.class);
        exception.expectMessage("List");
        exception.expectMessage("UnimplementedMethod");
        exception.expectMessage("doStuff()");
        exception.expectMessage("doStuff2(int)");

        getExtension(List.class, UnimplementedMethod.class);
    }

    interface UnimplementedSubInterface extends UnimplementedMethod {
        default void doStuff3() {}
    }

    @Test
    public void throwsExceptionIfExtensionSuperInterfaceHasANonDefaultMethod() {
        exception.expect(UnimplementedExtensionMethodException.class);
        exception.expectMessage("List");
        exception.expectMessage("UnimplementedSubInterface");
        exception.expectMessage("UnimplementedMethod");

        getExtension(List.class, UnimplementedSubInterface.class);
    }

    interface FullyImplemented extends List {
        default void doStuff() {}
    }

    @Test
    public void doesNotThrowExceptionIfExtensionIsFullyImplemented() {
        getExtension(List.class, FullyImplemented.class);
    }
}
