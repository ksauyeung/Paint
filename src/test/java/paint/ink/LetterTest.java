package paint.ink;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LetterTest {

    @Mock
    private Ink inkMock;
    @Mock
    private InkKey inkKeyMock;

    private Letter LetterUnderTest;

    public LetterTest() {
        MockitoAnnotations.openMocks(this);
        LetterUnderTest = new Letter('c');
    }

    @Test
    public void toStringShouldEqualLiteral() {
        Assert.assertTrue(LetterUnderTest.toString() == "c");
    }

    @Test
    public void lettersShouldEqual() {
        Letter letter1 = new Letter('c');
        Letter letter2 = new Letter(new Character('c'));
        Letter letter3 = new Letter(Character.valueOf('c'));
        Letter letter4 = new Letter('c');


        Assert.assertTrue(letter1.equals(letter2));
        Assert.assertTrue(letter2.equals(letter3));
        Assert.assertTrue(letter3.equals(letter4));
    }

    @Test
    public void hashCodeShouldEqual() {
        Letter letter1 = new Letter('c');
        Letter letter2 = new Letter(new Character('c'));
        Letter letter3 = new Letter(Character.valueOf('c'));
        Letter letter4 = new Letter('c');

        Assert.assertTrue(letter1.hashCode() == letter2.hashCode());
        Assert.assertTrue(letter2.hashCode() == letter3.hashCode());
        Assert.assertTrue(letter3.hashCode() == letter4.hashCode());
    }
}
