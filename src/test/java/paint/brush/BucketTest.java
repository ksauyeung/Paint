package paint.brush;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import paint.Range;
import paint.ink.Ink;
import paint.ink.Palette;
import paint.store.BufferStore;
import paint.brush.validator.RangeValidator;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

public class BucketTest {

    @Mock
    private RangeValidator rangeValidatorMock;
    @Mock
    private Palette paletteMock;
    @Mock
    private BufferStore bufferStoreMock;
    @Mock
    private Ink inkMock;

    private Bucket bucketUnderTest;

    public BucketTest() {
        MockitoAnnotations.openMocks(this);
        bucketUnderTest = new Bucket(rangeValidatorMock, paletteMock);
    }

    @Test
    public void paintShouldDoNothingWhenRangeIsInvalid() {

        Mockito.when(bufferStoreMock.isValid(anyInt(), anyInt())).thenReturn(false);
        Mockito.when(rangeValidatorMock.validate(any())).thenReturn(true);

        // when
        try {
            bucketUnderTest.setRange(new Range(1,1, 1, 1));
            bucketUnderTest.paint(bufferStoreMock);
        } catch (Exception e) {
            fail(e);
        }

        // then
        verify(bufferStoreMock, Mockito.times(0)).write(any(), anyInt(), anyInt());
    }

    @Test
    public void paintShouldCallWriteWhenRangeIsValid() {
        try {
            // given
            Mockito.when(rangeValidatorMock.validate(any())).thenReturn(true);
            Mockito.when(inkMock.get()).thenReturn(Character.MAX_VALUE);
            Mockito.when(paletteMock.getSelected()).thenReturn(inkMock);
            Mockito.when(bufferStoreMock.isValid(1, 1)).thenReturn(true);
            Mockito.when(bufferStoreMock.read(1, 1)).thenReturn(Character.MIN_VALUE);

            // when
            bucketUnderTest.setRange(new Range(1,1));
            bucketUnderTest.paint(bufferStoreMock);

            // then
            verify(bufferStoreMock, Mockito.times(1)).write(inkMock.get(), 1, 1);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void paintShouldNotCallWriteForNeighborWhenDifferent() {
        try {
            // given
            Mockito.when(rangeValidatorMock.validate(any())).thenReturn(true);
            Mockito.when(inkMock.get()).thenReturn(Character.MAX_VALUE);
            Mockito.when(bufferStoreMock.isValid(anyInt(), anyInt())).thenReturn(true);
            Mockito.when(paletteMock.getSelected()).thenReturn(inkMock);
            Mockito.when(bufferStoreMock.isValid(1, 1)).thenReturn(true);
            Mockito.when(bufferStoreMock.isValid(1, 2)).thenReturn(true);
            Mockito.when(bufferStoreMock.read(1, 1)).thenReturn(Character.MIN_VALUE).thenReturn(Character.MAX_VALUE);
            Mockito.when(bufferStoreMock.read(1, 2)).thenReturn(Character.MAX_VALUE);

            // when
            bucketUnderTest.setRange(new Range(1,1));
            bucketUnderTest.paint(bufferStoreMock);

            // then
            verify(bufferStoreMock, Mockito.times(0)).write(paletteMock.getSelected().get(), 1, 2);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void paintShouldCallWriteForNeighborsWhenSame() {
        try {
            // given
            Mockito.when(rangeValidatorMock.validate(any())).thenReturn(true);
            Mockito.when(inkMock.get()).thenReturn(Character.MAX_VALUE);
            Mockito.when(paletteMock.getSelected()).thenReturn(inkMock);

            Mockito.when(bufferStoreMock.isValid(2, 2)).thenReturn(true);
            Mockito.when(bufferStoreMock.read(2, 2)).thenReturn(Character.MIN_VALUE).thenReturn(Character.MAX_VALUE);
            //neighbors
            Mockito.when(bufferStoreMock.isValid(2, 1)).thenReturn(true);
            Mockito.when(bufferStoreMock.isValid(1, 2)).thenReturn(true);
            Mockito.when(bufferStoreMock.isValid(3, 2)).thenReturn(true);
            Mockito.when(bufferStoreMock.isValid(2, 3)).thenReturn(true);
            Mockito.when(bufferStoreMock.read(2, 1)).thenReturn(Character.MIN_VALUE).thenReturn(Character.MAX_VALUE);
            Mockito.when(bufferStoreMock.read(1, 2)).thenReturn(Character.MIN_VALUE).thenReturn(Character.MAX_VALUE);
            Mockito.when(bufferStoreMock.read(3, 2)).thenReturn(Character.MIN_VALUE).thenReturn(Character.MAX_VALUE);
            Mockito.when(bufferStoreMock.read(2, 3)).thenReturn(Character.MIN_VALUE).thenReturn(Character.MAX_VALUE);

            // when
            bucketUnderTest.setRange(new Range(2,2));
            bucketUnderTest.paint(bufferStoreMock);

            // then
            verify(bufferStoreMock, Mockito.times(1)).write(paletteMock.getSelected().get(), 2, 1);
            verify(bufferStoreMock, Mockito.times(1)).write(paletteMock.getSelected().get(), 1, 2);
            verify(bufferStoreMock, Mockito.times(1)).write(paletteMock.getSelected().get(), 3, 2);
            verify(bufferStoreMock, Mockito.times(1)).write(paletteMock.getSelected().get(), 2, 3);

        } catch (Exception e) {
            fail(e);
        }
    }
}
