package paint.validator;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import paint.Range;
import paint.brush.validator.ZeroDimensionRangeValidator;

public class ZeroDimensionRangeValidatorTest {

    private ZeroDimensionRangeValidator validatorUnderTest;

    public ZeroDimensionRangeValidatorTest() {
        MockitoAnnotations.openMocks(this);

        validatorUnderTest = new ZeroDimensionRangeValidator();
    }

    @Test
    public void validateShouldReturnFalseForNullRange() {

        Assert.assertFalse(validatorUnderTest.validate(null));

    }

    @Test
    public void validateShouldReturnFalseForZeroCoordinates() {

        Assert.assertFalse(validatorUnderTest.validate(new Range(0,0,0,0)));

    }

    @Test
    public void validateShouldReturnFalseForNegativeStartCoordinates() {

        Assert.assertFalse(validatorUnderTest.validate(new Range(-1, 1,2,2)));
        Assert.assertFalse(validatorUnderTest.validate(new Range(1, -1,2,2)));

    }

    @Test
    public void validateShouldReturnTrueForPointCoordinate() {

        Assert.assertTrue(validatorUnderTest.validate(new Range(2,2,2,2)));

    }

    @Test
    public void validateShouldReturnFalseForLineCoordinates() {

        Assert.assertFalse(validatorUnderTest.validate(new Range(2,3, 2, 4)));
        Assert.assertFalse(validatorUnderTest.validate(new Range(3,4, 2,4)));

    }

    @Test
    public void validateShouldReturnFalseForRectangularCoordinates() {

        Assert.assertFalse(validatorUnderTest.validate(new Range(2,3, 5, 4)));

    }
}
