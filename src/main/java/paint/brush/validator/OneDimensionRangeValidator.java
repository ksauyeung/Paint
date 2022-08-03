package paint.brush.validator;

import paint.Range;

public class OneDimensionRangeValidator extends TwoDimensionRangeValidator implements RangeValidator {

    public OneDimensionRangeValidator() {
        super();
    }

    @Override
    public boolean validate(Range range) {
        return super.validate(range) && (
                (range.getStartY() == range.getEndY()  || (range.getStartX() == range.getEndX()))
        );
    }
}
