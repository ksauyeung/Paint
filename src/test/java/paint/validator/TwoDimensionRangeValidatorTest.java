package paint.validator;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import paint.Range;
import paint.brush.validator.TwoDimensionRangeValidator;

public class TwoDimensionRangeValidatorTest {

    private TwoDimensionRangeValidator validatorUnderTest;

    public TwoDimensionRangeValidatorTest() {
        MockitoAnnotations.openMocks(this);

        validatorUnderTest = new TwoDimensionRangeValidator();
    }

    @Test
    public void validateShouldReturnFalseForZeroCoordinates() {

        Assert.assertFalse(validatorUnderTest.validate(new Range(0,0,0,0)));

    }

    @Test
    public void validateShouldReturnFalseForNullRange() {

        Assert.assertFalse(validatorUnderTest.validate(null));

    }

    @Test
    public void validateShouldReturnFalseForNegativeStartCoordinates() {

        Assert.assertFalse(validatorUnderTest.validate(new Range(-1, 1,2,2)));
        Assert.assertFalse(validatorUnderTest.validate(new Range(1, -1,2,2)));

    }
}
