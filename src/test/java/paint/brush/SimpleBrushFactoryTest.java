package paint.brush;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import paint.ink.Palette;

public class SimpleBrushFactoryTest {

    @Mock
    private Palette paletteMock;

    private SimpleBrushFactory simpleBrushFactoryUnderTest;

    public SimpleBrushFactoryTest() {
        MockitoAnnotations.openMocks(this);
        simpleBrushFactoryUnderTest = SimpleBrushFactory.getInstance();
    }

    @Test(expected=RuntimeException.class)
    public void createShouldThrowRuntimeException() throws NoSuchFieldException, IllegalAccessException {

        java.lang.reflect.Field prop = simpleBrushFactoryUnderTest.getClass().getDeclaredField("palette");
        prop.setAccessible(true);
        prop.set(simpleBrushFactoryUnderTest, null);

        // when
        simpleBrushFactoryUnderTest.create('A');
    }

    @Test
    public void createShouldNotThrowRuntimeException() {
        simpleBrushFactoryUnderTest.initialize(paletteMock);
        // when
        simpleBrushFactoryUnderTest.create('A');
    }
}
