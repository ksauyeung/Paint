package paint.ink;

import java.util.Objects;

public class InkKey {

    private char k;

    public InkKey(char c) {
        this.k = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InkKey inkKey = (InkKey) o;
        return k == inkKey.k;
    }

    @Override
    public int hashCode() {
        return Objects.hash(k);
    }
}
