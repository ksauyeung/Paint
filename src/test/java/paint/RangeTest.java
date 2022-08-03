package paint;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class RangeTest {

    public RangeTest() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void rangeShouldBeNormalized() {
        Range rangeUnderTest = new Range(4,5,1,2);
        Assert.assertTrue(rangeUnderTest.getStartX()==1);
        Assert.assertTrue(rangeUnderTest.getStartY()==2);
        Assert.assertTrue(rangeUnderTest.getEndX()==4);
        Assert.assertTrue(rangeUnderTest.getEndY()==5);
   }

}
