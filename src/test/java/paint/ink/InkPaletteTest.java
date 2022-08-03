package paint.ink;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class InkPaletteTest {

    @Mock
    private Ink inkMock;
    @Mock
    private InkKey inkKeyMock;

    private InkPalette inkPaletteUnderTest;

    public InkPaletteTest() {
        MockitoAnnotations.openMocks(this);
        inkPaletteUnderTest = new InkPalette(null);
    }

    @Test
    public void PutAndSelectShouldSelect() {

        Mockito.when(inkMock.getKey()).thenReturn(inkKeyMock);

        inkPaletteUnderTest.PutAndSelect(inkMock);

        Assert.assertTrue(inkPaletteUnderTest.getSelected().equals(inkMock));
    }

    @Test
    public void SelectShouldNotSelectWhenKeyIsNull() {

        Mockito.when(inkMock.getKey()).thenReturn(inkKeyMock);

        inkPaletteUnderTest.PutAndSelect(inkMock);
        inkPaletteUnderTest.select(null);

        Assert.assertTrue(inkPaletteUnderTest.getSelected().equals(inkMock));

    }

    @Test
    public void SelectShouldNotSelectWhenKeyNotFound() {

        Mockito.when(inkMock.getKey()).thenReturn(inkKeyMock);

        inkPaletteUnderTest.PutAndSelect(inkMock);
        inkPaletteUnderTest.select(Mockito.mock(InkKey.class));

        Assert.assertTrue(inkPaletteUnderTest.getSelected().equals(inkMock));
    }
}
