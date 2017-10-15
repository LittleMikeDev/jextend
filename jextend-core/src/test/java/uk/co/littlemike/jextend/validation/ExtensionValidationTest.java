package uk.co.littlemike.jextend.validation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.co.littlemike.jextend.BaseJExtendTest;
import uk.co.littlemike.jextend.JExtend;

import java.util.ArrayList;
import java.util.List;

public class ExtensionValidationTest extends BaseJExtendTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throwsExceptionWhenExtensionIsNotAnInterface() {
        exception.expect(ExtensionClassMustBeAnInterface.class);
        exception.expectMessage("Object");
        exception.expectMessage("ArrayList");

        JExtend.getExtension(Object.class, ArrayList.class);
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

        JExtend.getExtension(List.class, UnimplementedMethod.class);
    }
}
