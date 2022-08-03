package paint.validator;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import paint.Range;
import paint.brush.validator.OneDimensionRangeValidator;

public class OneDimensionRangeValidatorTest {
    private OneDimensionRangeValidator validatorUnderTest;

    public OneDimensionRangeValidatorTest() {
        MockitoAnnotations.openMocks(this);

        validatorUnderTest = new OneDimensionRangeValidator();
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

    @Test
    public void validateShouldReturnFalseForRectangularRange() {
        Assert.assertFalse(validatorUnderTest.validate(new Range(2,2, 5,5)));
    }

    @Test
    public void validateShouldReturnTrueForLineRange() {
        Assert.assertTrue(validatorUnderTest.validate(new Range(2,5, 2,8)));
        Assert.assertTrue(validatorUnderTest.validate(new Range(2,5, 8,5)));
    }
}
