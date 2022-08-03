import paint.Painter;
import paint.SimplePainter;
import paint.Util;
import paint.brush.*;
import paint.ink.Ink;
import paint.ink.InkPalette;
import paint.ink.Letter;
import paint.ink.Palette;
import paint.renderer.Renderer;
import paint.renderer.RendererFactory;
import paint.store.BufferStoreFactory;
import paint.store.StoreFactory;

public class Main {

    public static void main(String[] args) {

        final RendererFactory rendererFactory = RendererFactory.getInstance();
        final StoreFactory storeFactory = BufferStoreFactory.getInstance();
        final BrushFactory brushFactory = SimpleBrushFactory.getInstance();
        final Renderer renderer = rendererFactory.create(args.length > 0 && args[0].equals("norender"));
        final Palette palette = new InkPalette(new Ink[] { new Letter('x'), new Letter('o') });
        brushFactory.initialize(palette);

        try {
            Painter painter = new SimplePainter(storeFactory, brushFactory, palette, renderer);
            painter.start();

        } catch (Exception e) {
            Util.log(e);
        }
    }

}
