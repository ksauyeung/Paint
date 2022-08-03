package paint.brush;

import paint.ink.Palette;
import paint.brush.validator.RangeValidator;

/**
 * Draws a vertical or horizontal straight line.
 */
public class Line extends Rectangle implements Brush {

    public Line(RangeValidator rangeValidator, Palette palette) {
        super(rangeValidator, palette);
    }

}
