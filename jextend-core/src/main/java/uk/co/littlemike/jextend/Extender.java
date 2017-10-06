package uk.co.littlemike.jextend;

public interface Extender {
    <E> E extend(Object object, Class<E> extensionInterface);
}
