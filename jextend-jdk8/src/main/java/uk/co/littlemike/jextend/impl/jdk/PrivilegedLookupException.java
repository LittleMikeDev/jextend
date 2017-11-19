package uk.co.littlemike.jextend.impl.jdk;

public class PrivilegedLookupException extends RuntimeException {
    public PrivilegedLookupException(Throwable cause) {
        super("Failed to create privileged method lookup.", cause);
    }
}
