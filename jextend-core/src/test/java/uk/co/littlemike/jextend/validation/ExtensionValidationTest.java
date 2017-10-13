package uk.co.littlemike.jextend.validation;

import org.junit.Test;
import uk.co.littlemike.jextend.BaseJExtendTest;
import uk.co.littlemike.jextend.JExtend;

import java.util.ArrayList;

public class ExtensionValidationTest extends BaseJExtendTest {

    @Test(expected = ExtensionClassMustBeAnInterface.class)
    public void throwsExceptionWhenExtensionIsNotAnInterface() {
        JExtend.getExtension(Object.class, ArrayList.class);
    }
}
