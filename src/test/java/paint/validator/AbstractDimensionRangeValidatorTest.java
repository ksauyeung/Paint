package paint.validator;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import paint.Range;
import paint.brush.validator.AbstractDimensionRangeValidator;

public class AbstractDimensionRangeValidatorTest {

    private AbstractDimensionRangeValidator abstractDimensionRangeValidatorUnderTest;

    public AbstractDimensionRangeValidatorTest() {
        MockitoAnnotations.openMocks(this);

        abstractDimensionRangeValidatorUnderTest = Mockito.mock(AbstractDimensionRangeValidator.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    public void validateShouldReturnFalseForNullRange() {

        Assert.assertFalse(abstractDimensionRangeValidatorUnderTest.validate(null));

    }

    @Test
    public void validateShouldReturnFalseForNegativeStartCoordinates() {

        Assert.assertFalse(abstractDimensionRangeValidatorUnderTest.validate(new Range(-1, 1,2,2)));
        Assert.assertFalse(abstractDimensionRangeValidatorUnderTest.validate(new Range(1, -1,2,2)));

    }
}
