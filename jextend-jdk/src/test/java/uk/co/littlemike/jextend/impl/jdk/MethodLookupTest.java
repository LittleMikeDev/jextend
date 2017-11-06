package uk.co.littlemike.jextend.impl.jdk;

import org.junit.Test;

public class MethodLookupTest {

    private void privateMethod() {
    }

    @Test(expected = MethodLookupException.class)
    public void throwsExceptionWhenAttemptingToLookupPrivateMethod() throws Exception {
        MethodLookup lookup = new MethodLookup(Object.class);

        lookup.lookup(MethodLookupTest.class.getDeclaredMethod("privateMethod"));
    }
}
