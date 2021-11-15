package SOLID.interfaces;

public interface Factory<T> {

    T produce(String input);
}
