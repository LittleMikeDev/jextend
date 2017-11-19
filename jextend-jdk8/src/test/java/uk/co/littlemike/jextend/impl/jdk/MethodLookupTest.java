package uk.co.littlemike.jextend.impl.jdk;

import org.junit.Test;

public class MethodLookupTest {

    private void privateMethod() {
    }

    @Test(expected = MethodLookupException.class)
    public void throwsExceptionWhenAttemptingToLookupPrivateMethodOnDifferentClass() throws Exception {
        MethodLookup lookup = new MethodLookup(Object.class);

        lookup.lookup(MethodLookupTest.class.getDeclaredMethod("privateMethod"));
    }

    @Test(expected = MethodLookupException.class)
    public void throwsExceptionWhenAttemptingToLookupPrivateMethodOnDifferentClass_EvenIfPrivileged() throws Exception {
        PrivilegedMethodLookup lookup = new PrivilegedMethodLookup(Object.class);

        lookup.lookupDefault(MethodLookupTest.class.getDeclaredMethod("privateMethod"));
    }
}
