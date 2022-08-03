package paint.store;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import paint.OutOfRangeException;
import java.util.Objects;

public class ObjectArrayBufferTest {

    private ObjectArrayBuffer objectArrayBufferTest;

    public ObjectArrayBufferTest() {
        MockitoAnnotations.openMocks(this);
        objectArrayBufferTest = new ObjectArrayBuffer(2, 2);
    }

    @Test(expected=IllegalArgumentException.class)
    public void ctorShouldThrowIllegalArgumentException() {
        new ObjectArrayBuffer(-1, -1);
    }

    @Test
    public void readShouldReturnObjectWritten() throws OutOfRangeException {

        Character c = Character.valueOf('c');
        objectArrayBufferTest.write(c, 1,2);

        Object C = objectArrayBufferTest.read(1,2);

        Assert.assertTrue(C.equals(c));
    }

    @Test(expected=OutOfRangeException.class)
    public void readShouldThrowOutOfRangeException() throws OutOfRangeException {

        Object C = objectArrayBufferTest.read(3, 3);
    }

    @Test(expected=OutOfRangeException.class)
    public void readShouldThrowOutOfRangeExceptionForHorizontalOutOfRange() throws OutOfRangeException {
        //checks index calc
        Object C = objectArrayBufferTest.read(1, 3);
    }

    @Test
    public void writeShouldNotThrowOutOfRangeException() {

        Character c = Character.valueOf('c');
        objectArrayBufferTest.write(c, 3,3);
    }

    @Test
    public void writeShouldNotThrowOutOfRangeExceptionForHorizontalOutOfRange()  {

        Character c = Character.valueOf('c');
        objectArrayBufferTest.write(c, 1,3);

    }

    @Test
    public void isValidShouldReturnTrue() {

        Assert.assertTrue(objectArrayBufferTest.isValid(1,1));
        Assert.assertTrue(objectArrayBufferTest.isValid(1,2));
        Assert.assertTrue(objectArrayBufferTest.isValid(2,1));
        Assert.assertTrue(objectArrayBufferTest.isValid(2,2));

    }

    @Test
    public void isValidShouldReturnFalse() {
        Assert.assertFalse(objectArrayBufferTest.isValid(2,-1));
        Assert.assertFalse(objectArrayBufferTest.isValid(-1,-1));
        Assert.assertFalse(objectArrayBufferTest.isValid(0,0));
        Assert.assertFalse(objectArrayBufferTest.isValid(1,3));
        Assert.assertFalse(objectArrayBufferTest.isValid(3,3));
    }

    @Test
    public void clearShouldWriteBufferWithNull() throws OutOfRangeException {
        Character c = Character.valueOf('c');
        objectArrayBufferTest.write(c, 1,1);
        objectArrayBufferTest.write(c, 1,2);
        objectArrayBufferTest.write(c, 2,1);
        objectArrayBufferTest.write(c, 2,2);

        Assert.assertFalse(Objects.equals(objectArrayBufferTest.read(1,1), null));
        Assert.assertFalse(Objects.equals(objectArrayBufferTest.read(1,2), null));
        Assert.assertFalse(Objects.equals(objectArrayBufferTest.read(2,1), null));
        Assert.assertFalse(Objects.equals(objectArrayBufferTest.read(2,2), null));

        objectArrayBufferTest.clear();

        Assert.assertTrue(Objects.equals(objectArrayBufferTest.read(1,1), null));
        Assert.assertTrue(Objects.equals(objectArrayBufferTest.read(1,2), null));
        Assert.assertTrue(Objects.equals(objectArrayBufferTest.read(2,1), null));
        Assert.assertTrue(Objects.equals(objectArrayBufferTest.read(2,2), null));
    }
}

