package solid.interfaces;

public interface Factory<T> {

    T produce(String input);
}
