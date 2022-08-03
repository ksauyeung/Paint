package paint.store;

import paint.OutOfRangeException;

/**
 * Buffer for canvas. Implementations should only store what's visible on the canvas.
 * @param <T>
 */
public interface BufferStore<T> {

    boolean write(T data, int posX, int posY);

    T read(int posX, int posY) throws OutOfRangeException;

    boolean isValid(int posX, int posY);

    void clear();

    boolean first();

    boolean next();

    boolean hasNext();

    boolean previous();

    Object get();
}
