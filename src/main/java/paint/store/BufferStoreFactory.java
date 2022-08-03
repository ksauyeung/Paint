package paint.store;

public class BufferStoreFactory implements StoreFactory {

    private static BufferStoreFactory instance;

    private BufferStoreFactory() {}

    public static BufferStoreFactory getInstance() {
        if(instance == null) {
            synchronized(BufferStoreFactory.class) {
                if(instance == null) {
                    instance = new BufferStoreFactory();
                }
            }
        }
        return instance;
    }

    public BufferStore create(int horizontal, int vertical) {
        return new ObjectArrayBuffer(horizontal, vertical);
    }

}
