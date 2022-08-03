package paint.brush;

import paint.*;
import paint.InvalidRangeException;
import paint.ink.Palette;
import paint.store.BufferStore;
import paint.brush.validator.RangeValidator;

public abstract class AbstractBrush implements HasRange {

    protected Palette palette;
    protected RangeValidator rangeValidator;
    protected Range range;

    @Override
    public Range getRange() { return range; }
    public void setRange(Range range) throws InvalidRangeException {
        if (rangeValidator != null && !rangeValidator.validate(range)) {
            throw new InvalidRangeException();
        }
        this.range = range;
    }

    protected AbstractBrush(RangeValidator rangeValidator, Palette palette) {
        this.rangeValidator = rangeValidator;
        this.palette = palette;
    }

    protected abstract void paint(BufferStore buffer) throws OutOfRangeException;
}
