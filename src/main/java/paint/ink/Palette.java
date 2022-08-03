package paint.ink;

/**
 * A collection of Ink
 */
public interface Palette {

     void PutAndSelect(Ink ink);

    void select(InkKey key);

    Ink getSelected();
}
