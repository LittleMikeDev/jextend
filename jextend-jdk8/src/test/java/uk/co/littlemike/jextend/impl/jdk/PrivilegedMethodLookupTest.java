package uk.co.littlemike.jextend.impl.jdk;

import org.junit.Test;

public class PrivilegedMethodLookupTest {

    private void privateMethod() {
    }

    @Test(expected = MethodLookupException.class)
    public void throwsExceptionWhenAttemptingToLookupPrivateMethodOnDifferentClass() throws Exception {
        PrivilegedMethodLookup lookup = new PrivilegedMethodLookup(Object.class);

        lookup.lookup(PrivilegedMethodLookupTest.class.getDeclaredMethod("privateMethod"));
    }

    @Test(expected = MethodLookupException.class)
    public void throwsExceptionWhenAttemptingToLookupPrivateMethodOnDifferentClass_EvenIfPrivileged() throws Exception {
        PrivilegedMethodLookup lookup = new PrivilegedMethodLookup(Object.class);

        lookup.lookupDefault(PrivilegedMethodLookupTest.class.getDeclaredMethod("privateMethod"));
    }
}
