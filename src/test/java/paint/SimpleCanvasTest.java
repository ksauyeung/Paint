package paint;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import paint.brush.Brush;
import paint.ink.Palette;
import paint.renderer.Renderer;
import paint.store.BufferStore;
import paint.store.StoreFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

public class SimpleCanvasTest {

    @Mock
    private Palette paletteMock;
    @Mock
    private BufferStore bufferStoreMock;

    @Mock
    private StoreFactory storeFactoryMock;
    @Mock
    private Renderer rendererMock;

    private SimpleCanvas canvasUnderTest;

    public SimpleCanvasTest() throws InvalidRangeException {
        MockitoAnnotations.openMocks(this);
        Mockito.when(storeFactoryMock.create(anyInt(), anyInt())).thenReturn(bufferStoreMock);
        canvasUnderTest = new SimpleCanvas(5,4, storeFactoryMock, paletteMock, rendererMock);
    }

    @Test(expected=InvalidRangeException.class)
    public void shouldThrowInvalidRangeException() throws InvalidRangeException {
        new SimpleCanvas(0,0, storeFactoryMock, paletteMock, rendererMock);
    }


    @Test
    public void drawShouldCallBrushPaint() throws OutOfRangeException {

        Brush brush = Mockito.mock(Brush.class);

        canvasUnderTest.draw(brush);

        verify(brush, Mockito.times(1)).paint(any());

    }

    @Test
    public void clearShouldCallBufferClear() {

        canvasUnderTest.clear();

        verify(bufferStoreMock, Mockito.times(1)).clear();

    }

    @Test
    public void isValidCoordinateShouldCheckBuffer() {
        canvasUnderTest.isValidCoordinate(5,7);
        verify(bufferStoreMock, Mockito.times(1)).isValid(5,7);

    }

}
