package paint.ink;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class InkKeyTest {

    public InkKeyTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void inkKeysShouldEqual() {

        InkKey inkKey1 = new InkKey('c');
        InkKey inkKey2 = new InkKey(new Character('c'));
        InkKey inkKey3 = new InkKey((char)99);
        InkKey inkKey4 = new InkKey(Character.valueOf('c'));

        Assert.assertTrue(inkKey1.equals(inkKey2));
        Assert.assertTrue(inkKey2.equals(inkKey3));
        Assert.assertTrue(inkKey3.equals(inkKey4));
    }

    @Test
    public void inkKeysShouldNotEqual() {

        InkKey inkKey1 = new InkKey('c');
        InkKey inkKey2 = new InkKey(new Character('C'));

        Assert.assertFalse(inkKey1.equals(inkKey2));
    }

    @Test
    public void hashCodeShouldEqual() {

        InkKey inkKey1 = new InkKey('c');
        InkKey inkKey2 = new InkKey(new Character('c'));
        InkKey inkKey3 = new InkKey((char)99);
        InkKey inkKey4 = new InkKey(Character.valueOf('c'));

        Assert.assertTrue(inkKey1.hashCode() == inkKey2.hashCode());
        Assert.assertTrue(inkKey2.hashCode() == inkKey3.hashCode());
        Assert.assertTrue(inkKey3.hashCode() == inkKey4.hashCode());
    }
}
