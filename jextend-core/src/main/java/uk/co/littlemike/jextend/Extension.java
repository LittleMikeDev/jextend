package uk.co.littlemike.jextend;

public interface Extension<C, E> {
    E extend(C object);
}
