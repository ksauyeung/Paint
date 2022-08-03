package paint.brush;

import paint.ink.Ink;
import paint.ink.Palette;
import paint.store.BufferStore;
import paint.brush.validator.RangeValidator;

/**
 * Draws an unfilled rectangle
 */
public class Rectangle extends AbstractBrush implements Brush {

    public Rectangle(RangeValidator rangeValidator, Palette palette) {
        super(rangeValidator, palette);
    }
    @Override
    public void paint(BufferStore buffer) {

        // draw lengths
        Ink ink = palette.getSelected();
        for(int x = range.getStartX(); x<= range.getEndX(); x++) {
            buffer.write(ink.get(), x, range.getStartY());
            buffer.write(ink.get(), x, range.getEndY());
        }

        // draw widths
        for(int y = range.getStartY() + 1; y< range.getEndY(); y++) {
            buffer.write(ink.get(), range.getStartX(), y);
            buffer.write(ink.get(), range.getEndX(), y);
        }
    }
}