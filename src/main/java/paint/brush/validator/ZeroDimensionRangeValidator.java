package paint.brush.validator;

import paint.Range;

public class ZeroDimensionRangeValidator extends AbstractDimensionRangeValidator implements RangeValidator {

    public ZeroDimensionRangeValidator() {
        super();
    }

    @Override
    public boolean validate(Range range) {
        return super.validate(range) && (
                (range.getStartY() == range.getEndY() && (range.getStartX() == range.getEndX()))
        );
    }
}
