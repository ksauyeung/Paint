package paint.brush;

import paint.OutOfRangeException;
import paint.Range;
import paint.InvalidRangeException;
import paint.store.BufferStore;

public interface Brush {

    void setRange(Range range) throws InvalidRangeException;

    void paint(BufferStore buffer) throws OutOfRangeException;

}
