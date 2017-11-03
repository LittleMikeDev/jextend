package uk.co.littlemike.jextend;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.co.littlemike.jextend.validation.ExtensionClassNotAnInterfaceException;
import uk.co.littlemike.jextend.validation.ExtensionInterfaceDoesNotExtendBaseClassException;
import uk.co.littlemike.jextend.validation.UnimplementedExtensionMethodException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

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

    @SuppressWarnings("unchecked")
    @Test(expected = ExtensionInterfaceDoesNotExtendBaseClassException.class)
    public void extensionInterfaceMustExtendBaseClass() {
        BiFunction<Class, Class, Extension> extendFunction = JExtend::getExtension;
        ((BiFunction) extendFunction).apply(ListExtension.class, List.class);
    }
}
