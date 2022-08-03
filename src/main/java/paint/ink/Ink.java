package paint.ink;

/**
 * Encapsulation of data to be used by Brush to draw onto a Canvas
 * @param <T>
 */
public interface Ink<T> {

    T get();

    InkKey getKey();
}
