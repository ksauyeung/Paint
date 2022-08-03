package paint.brush.validator;

import paint.Range;

public class AbstractDimensionRangeValidator {

    protected AbstractDimensionRangeValidator() {

    }

    public boolean validate(Range range) {

        if(range == null) {
            return false;
        }

        if(range.getStartX() <= 0  ||  range.getStartY() <= 0 )
            return false;

        if(range.getEndX() < range.getStartX() || range.getEndY() < range.getStartY()) {
            return false;
        }

        return true;
    }


}
