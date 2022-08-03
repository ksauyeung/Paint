package paint.brush;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import paint.InvalidRangeException;
import paint.Range;
import paint.ink.Ink;
import paint.ink.Palette;
import paint.store.BufferStore;
import paint.brush.validator.RangeValidator;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

public class RectangleTest {

    @Mock
    private RangeValidator rangeValidatorMock;
    @Mock
    private Palette paletteMock;
    @Mock
    private BufferStore bufferStoreMock;
    @Mock
    private Ink inkMock;

    private Rectangle rectangleBrushUnderTest;

    public RectangleTest() {
        MockitoAnnotations.openMocks(this);
        rectangleBrushUnderTest = new Rectangle(rangeValidatorMock, paletteMock);
    }

    @Test
    public void paintShouldCallWrite() throws InvalidRangeException {
        Mockito.when(inkMock.get()).thenReturn(Character.MAX_VALUE);
        Mockito.when(paletteMock.getSelected()).thenReturn(inkMock);
        Mockito.when(bufferStoreMock.isValid(anyInt(), anyInt())).thenReturn(false);
        Mockito.when(rangeValidatorMock.validate(any())).thenReturn(true);

        // when
        rectangleBrushUnderTest.setRange(new Range(1,1, 2, 2));
        rectangleBrushUnderTest.paint(bufferStoreMock);

        // then
        verify(bufferStoreMock, Mockito.times(1)).write(Character.MAX_VALUE, 1, 1);
        verify(bufferStoreMock, Mockito.times(1)).write(Character.MAX_VALUE, 1, 2);
        verify(bufferStoreMock, Mockito.times(1)).write(Character.MAX_VALUE, 2, 1);
        verify(bufferStoreMock, Mockito.times(1)).write(Character.MAX_VALUE, 2, 2);
    }

    @Test
    public void paintShouldCallWriteForZeroLength() throws InvalidRangeException {
        Mockito.when(inkMock.get()).thenReturn(Character.MAX_VALUE);
        Mockito.when(paletteMock.getSelected()).thenReturn(inkMock);
        Mockito.when(bufferStoreMock.isValid(anyInt(), anyInt())).thenReturn(false);
        Mockito.when(rangeValidatorMock.validate(any())).thenReturn(true);

        // when
        rectangleBrushUnderTest.setRange(new Range(1,1, 1, 2));
        rectangleBrushUnderTest.paint(bufferStoreMock);

        // then
        verify(bufferStoreMock, Mockito.atLeastOnce()).write(Character.MAX_VALUE, 1, 1);
        verify(bufferStoreMock, Mockito.atLeastOnce()).write(Character.MAX_VALUE, 1, 2);
    }

    @Test
    public void paintShouldCallWriteForZeroWidth() throws InvalidRangeException {
        Mockito.when(inkMock.get()).thenReturn(Character.MAX_VALUE);
        Mockito.when(paletteMock.getSelected()).thenReturn(inkMock);
        Mockito.when(bufferStoreMock.isValid(anyInt(), anyInt())).thenReturn(false);
        Mockito.when(rangeValidatorMock.validate(any())).thenReturn(true);

        // when
        rectangleBrushUnderTest.setRange(new Range(1,1, 2, 1));
        rectangleBrushUnderTest.paint(bufferStoreMock);

        // then
        verify(bufferStoreMock, Mockito.atLeastOnce()).write(Character.MAX_VALUE, 1, 1);
        verify(bufferStoreMock, Mockito.atLeastOnce()).write(Character.MAX_VALUE, 2, 1);
    }

    @Test
    public void paintShouldCallWriteForPoint() throws InvalidRangeException {
        Mockito.when(inkMock.get()).thenReturn(Character.MAX_VALUE);
        Mockito.when(paletteMock.getSelected()).thenReturn(inkMock);
        Mockito.when(bufferStoreMock.isValid(anyInt(), anyInt())).thenReturn(false);
        Mockito.when(rangeValidatorMock.validate(any())).thenReturn(true);

        // when
        rectangleBrushUnderTest.setRange(new Range(1,1, 1, 1));
        rectangleBrushUnderTest.paint(bufferStoreMock);

        // then
        verify(bufferStoreMock, Mockito.atLeastOnce()).write(Character.MAX_VALUE, 1, 1);
    }
}
