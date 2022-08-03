package paint.brush;

import paint.ink.Palette;

public interface BrushFactory {

    void initialize(Palette palette);

    Brush get(char brushType);
}
