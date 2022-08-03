package paint;

import paint.brush.Brush;
import paint.ink.Palette;
import paint.renderer.Renderer;
import paint.store.BufferStore;
import paint.store.StoreFactory;
import paint.brush.validator.TwoDimensionRangeValidator;

public class SimpleCanvas implements Canvas {

    private BufferStore buffer;
    private final Range range;
    private final StoreFactory storeFactory;
    private final Renderer renderer;
    private Palette palette;

    @Override
    public int getHorizontal() {
        return range.getEndX();
    }

    @Override
    public int getVertical() {
        return range.getEndY();
    }

    @Override
    public boolean isValidCoordinate(int x, int y) {
        return buffer.isValid(x, y);
    }

    private static final TwoDimensionRangeValidator canvasRangevalidator = new TwoDimensionRangeValidator();
    /**
     * A Rectangular canvas
     * @param horizontal
     * @param vertical
     * @param storeFactory
     */
    public SimpleCanvas(int horizontal, int vertical, StoreFactory storeFactory, Palette palette, Renderer renderer) throws InvalidRangeException {

        this.range = new Range(1, 1, horizontal, vertical);
        if(!canvasRangevalidator.validate(this.range)) {
            throw new InvalidRangeException();
        }
        this.storeFactory = storeFactory;
        this.buffer = storeFactory.create(horizontal, vertical);
        this.palette = palette;
        this.renderer = renderer;
    }

    @Override
    public synchronized Boolean resize(int newHorizontal, int newVertical) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public synchronized void draw(Brush brush) throws OutOfRangeException {
        brush.paint(buffer);
    }

    @Override
    public synchronized void clear() {
        buffer.clear();
    }

    @Override
    public synchronized void render() {

        try {
            // canvas top
            if(getVertical() >= 0) {
                for (int x = 0; x <= getHorizontal()+1; x++) {
                    renderer.renderElement('-');
                }
            }

            for(int y=1; y<=getVertical(); y++) {
                renderer.renderElement('\n');
                // canvas left
                renderer.renderElement('|');

                for(int x=1; x<=getHorizontal(); x++) {
                    // content
                    renderer.renderElement(buffer.read(x, y));
                }

                // canvas right
                renderer.renderElement('|');
            }

            // canvas bottom
            if(getVertical() >= 0) {
                renderer.renderElement('\n');
                for (int x = 0; x <= getHorizontal()+1; x++) {
                    renderer.renderElement('-');
                }
                renderer.renderElement('\n');
            }

        } catch (Exception e) {
            Util.log(e);
        }
    }


}
