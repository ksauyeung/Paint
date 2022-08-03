package paint.renderer;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.OutputStream;
import static org.mockito.Mockito.verify;

public class CharacterRendererTest {

    private CharacterRenderer characterRendererUnderTest;
    private OutputStream outputStreamUnderTest = Mockito.mock(OutputStream.class, Mockito.CALLS_REAL_METHODS);

    public CharacterRendererTest() {
        MockitoAnnotations.openMocks(this);

        characterRendererUnderTest = new CharacterRenderer(outputStreamUnderTest);
    }

    @Test
    public void renderElementShouldCallWrite() throws IOException {

        characterRendererUnderTest.renderElement('c');

        // then
        verify(outputStreamUnderTest, Mockito.times(1)).write('c');
    }

    @Test
    public void renderElementShouldCallFlush() throws IOException {

        characterRendererUnderTest.renderElement('c');

        // then
        verify(outputStreamUnderTest, Mockito.times(1)).flush();
    }

}
