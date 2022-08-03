package paint.brush;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import paint.InvalidRangeException;
import paint.Range;
import paint.brush.validator.RangeValidator;

import static org.mockito.ArgumentMatchers.any;

public class AbstractBrushTest {

    @Mock
    private RangeValidator rangeValidatorMock;

    private AbstractBrush abstractBrushUnderTest;

    public AbstractBrushTest() {
        MockitoAnnotations.openMocks(this);
        abstractBrushUnderTest = Mockito.mock(AbstractBrush.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test(expected=InvalidRangeException.class)
    public void setRangeShouldThrowInvalidRangeException() throws InvalidRangeException {

        Mockito.when(rangeValidatorMock.validate(any())).thenReturn(false);

        abstractBrushUnderTest.rangeValidator = rangeValidatorMock;
        // when
        abstractBrushUnderTest.setRange(new Range(1,1, 1, 1));
    }

    @Test
    public void setRangeShouldNotThrowInvalidRangeExceptionIfNullValidator() throws InvalidRangeException {

        abstractBrushUnderTest.rangeValidator = null;
        // when
        abstractBrushUnderTest.setRange(new Range(1,1, 1, 1));
    }

    @Test
    public void setRangeShouldNotThrowInvalidRangeException() throws InvalidRangeException {

        Mockito.when(rangeValidatorMock.validate(any())).thenReturn(true);

        abstractBrushUnderTest.rangeValidator = rangeValidatorMock;
        // when
        abstractBrushUnderTest.setRange(new Range(1,1, 1, 1));
    }
}
