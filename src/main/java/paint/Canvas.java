package paint;

import paint.brush.Brush;

/**
 * A declared space that can render contents that are drawn by a Brush
 */
public interface Canvas {

    int getHorizontal();

    int getVertical();

    boolean isValidCoordinate(int x, int y);

    Boolean resize(int newHorizontal, int newVertical);

    void draw(Brush brush) throws OutOfRangeException;

    void render();

    void clear();
}
