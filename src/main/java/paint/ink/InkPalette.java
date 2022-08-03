package paint.ink;

import java.util.*;

public final class InkPalette implements Palette {

    private HashMap<InkKey, Ink> allInks = new HashMap<>(2);

    private Ink selected;

    public InkPalette(Ink[] inks) {
        if(inks != null) {
            Arrays.stream(inks).forEach(i -> allInks.put(i.getKey(), i));
        }
    }

    public void PutAndSelect(Ink ink) {
        if(ink == null) {
            return;
        }
        InkKey key = ink.getKey();
        allInks.put(ink.getKey(), ink);
        select(key);
    }

    @Override
    public void select(InkKey key) {
        if(key == null) {
            return;
        }
        Ink ink = allInks.get(key);
        if(ink != null) {
            selected = ink;
        }
    }

    @Override
    public Ink getSelected() {
        return selected;
    }


}
