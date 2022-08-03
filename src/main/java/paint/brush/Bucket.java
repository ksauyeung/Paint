package paint.brush;

import paint.OutOfRangeException;
import paint.Range;
import paint.ink.Palette;
import paint.store.BufferStore;
import paint.brush.validator.RangeValidator;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Fills an area
 */
public class Bucket extends AbstractBrush implements Brush {

    public Bucket(RangeValidator rangeValidator, Palette palette) {
        super(rangeValidator, palette);
    }
int currSize = 0;
    private void checkNeighbor(BufferStore buffer, Range nextPos, LinkedList queue, Object markedColor) {
        //System.out.println("checkNeighbor " + nextPos.getStartX() + ", " + nextPos.getStartY());
        if(!buffer.isValid(nextPos.getStartX(), nextPos.getStartY())) {
            return;
        }
        try {
            Object read = buffer.read(nextPos.getStartX(), nextPos.getStartY());
            if(!Objects.equals(markedColor, read)) {
                // hit wall
                return;
            }

        } catch (OutOfRangeException e) {
            return;
        }

        if(queue.size() > 0 && queue.peekLast().equals(nextPos)) {
            //System.out.println("Enque " + nextPos.getStartX() + " " + nextPos.getStartY());
            return;
        }

        queue.offer(nextPos);
        if(queue.size() > currSize) {
            currSize = queue.size();
        }
        //System.out.println("size " + currSize);

    }
    @Override
    public void paint(BufferStore buffer) throws OutOfRangeException {
        LinkedList<Range> list = new LinkedList<>();
        if(!buffer.isValid(range.getStartX(), range.getStartY())) {
            return; // outside canvas, do nothing
        }
        Object markedColor = buffer.read(range.getStartX(), range.getStartY());
        if(Objects.equals(markedColor, palette.getSelected().get())) {
            return;
        }

        list.offer(range);
        while(!list.isEmpty()) {
            Range currPos = list.poll();
            //System.out.println("Deque " + currPos.getStartX() + " " + currPos.getStartY());
            buffer.write(palette.getSelected().get(), currPos.getStartX(), currPos.getStartY());

            checkNeighbor(buffer, new Range(currPos.getStartX(), currPos.getStartY() - 1), list, markedColor);
            checkNeighbor(buffer, new Range(currPos.getStartX() + 1 , currPos.getStartY()), list, markedColor);
            checkNeighbor(buffer, new Range(currPos .getStartX(), currPos.getStartY() + 1 ), list, markedColor);
            checkNeighbor(buffer, new Range(currPos.getStartX() - 1, currPos.getStartY()), list, markedColor);
        }

        //System.out.println("max size " + currSize);
        currSize = 0;
    }

}
