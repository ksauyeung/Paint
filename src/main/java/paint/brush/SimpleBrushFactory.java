package paint.brush;

import paint.ink.Palette;
import paint.brush.validator.OneDimensionRangeValidator;
import paint.brush.validator.TwoDimensionRangeValidator;
import paint.brush.validator.ZeroDimensionRangeValidator;

import java.util.HashMap;

public class SimpleBrushFactory implements BrushFactory {

    private static SimpleBrushFactory instance;

    private HashMap<Class, Brush> brushMap = new HashMap<>(3);
    private Palette palette;

    private SimpleBrushFactory() {

    }

    public static SimpleBrushFactory getInstance() {
        if(instance == null) {
            synchronized(SimpleBrushFactory.class) {
                if(instance == null) {
                    instance = new SimpleBrushFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public void initialize(Palette palette) {
        this.palette = palette;
    }

    @Override
    public Brush get(char brushType) {
        Brush brush = null;
        switch(brushType) {
            case 'L':
                brush = brushMap.get(Line.class);
                break;
            case 'R':
                brush = brushMap.get(Rectangle.class);
                break;
            case 'B':
                brush = brushMap.get(Bucket.class);
                break;
            default:
                break;
        }
        if(brush == null) {
            brush = create(brushType);
            brushMap.put(brush.getClass(), brush);
        }
        return brush;
    }

    /**
     * Create a new brush instance
     * @param brushType 'L' = Line, 'R' = Rectangle, 'B' = Bucket
     * @return
     */
    public Brush create(char brushType) {
        if(palette == null) {
            throw new RuntimeException("Factory not initialized.");
        }
        switch(brushType) {
            case 'L':
                return new Line(new OneDimensionRangeValidator(), palette);
            case 'R':
                return new Rectangle(new TwoDimensionRangeValidator(), palette);
            case 'B':
                return new Bucket(new ZeroDimensionRangeValidator(), palette);
            default:
                return null;
        }
    }

}
