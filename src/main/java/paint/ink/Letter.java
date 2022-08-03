package paint.ink;

import java.util.Objects;

public class Letter implements Ink<Character> {
    Character c;

    public Letter(char c) {
        this.c = c;
    }
    @Override
    public Character get() {
        return c;
    }

    @Override
    public InkKey getKey() {
        return new InkKey(c.charValue());
    }

    @Override
    public String toString() {
        String s = c.toString().intern();
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        char co = ((Letter) o).c.charValue();
        return this.c.charValue() == co;
    }

    @Override
    public int hashCode() {
        return Objects.hash(c);
    }
}
