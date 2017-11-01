package uk.co.littlemike.jextend;

import java.util.List;

public interface ListExtension extends List {

    default boolean isNotEmpty() {
        return !isEmpty();
    }
}
