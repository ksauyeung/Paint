package paint.store;

public interface StoreFactory {

    BufferStore create(int horizontal, int vertical);

}
