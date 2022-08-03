package paint;

/**
 * A worker that should acquire and consume instruction to facilitate interactions between Canvas, Palettes and Brushes
 */
public interface Painter {

    void start();

    void stop();
}
