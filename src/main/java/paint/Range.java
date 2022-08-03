package paint;

import java.util.Objects;

public final class Range {

    private int startX, startY;
    private int endX, endY;

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    /**
     * A Point
     * @param x
     * @param y
     */
    public Range(int x, int y) {
        this.startY = y;
        this.startX = x;
        this.endX = x;
        this.endY = y;
    }

    public Range(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        normalize();
    }

    private void normalize() {
        int temp;
        if(startX > endX) {
            temp = startX;
            startX = endX;
            endX = temp;
        }
        if(startY > endY) {
            temp = startY;
            startY = endY;
            endY = temp;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return startX == range.startX && startY == range.startY && endX == range.endX && endY == range.endY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startX, startY, endX, endY);
    }
}
