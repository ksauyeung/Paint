package paint.renderer;

public class RendererFactory {

    private static RendererFactory instance;

    private RendererFactory() {}

    public static RendererFactory getInstance() {
        if(instance == null) {
            synchronized(RendererFactory.class) {
                if(instance == null) {
                    instance = new RendererFactory();
                }
            }
        }
        return instance;
    }

    public Renderer create(boolean useNull) {
        if(useNull) {
            return new NullRenderer();
        }
        return new CharacterRenderer(System.out);
    }

}

