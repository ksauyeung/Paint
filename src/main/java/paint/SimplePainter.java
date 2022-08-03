package paint;

import paint.brush.Brush;
import paint.brush.BrushFactory;
import paint.ink.InkKey;
import paint.ink.Letter;
import paint.ink.Palette;
import paint.renderer.Renderer;
import paint.store.StoreFactory;

import java.util.Scanner;

/**
 * Simple painter that asks for input from stdin and paints a canvas with selected brushes and colors, finally outputs the canvas to stdout
 */
public class SimplePainter implements Painter {

    private boolean isStarted;

    private final Scanner scanner = new Scanner(System.in);
    private final StoreFactory storeFactory;
    private final BrushFactory brushFactory;
    private final Renderer renderer;
    private final Palette palette;
    private Canvas canvas;

    public SimplePainter(StoreFactory storeFactory,
                         BrushFactory brushFactory,
                         Palette palette,
                         Renderer renderer) {
        this.storeFactory = storeFactory;
        this.brushFactory = brushFactory;
        this.renderer = renderer;
        this.palette = palette;
    }

    public void stop() {
        synchronized (this) {
            isStarted = false;
        }
    }

    public void start() {
        synchronized (this) {
            if (isStarted) {
                return;
            }
        }
        paint();
    }

    private void paint() {

        isStarted = true;

        while(isStarted) {
            try {
                Command cmd = getInput();
                if (cmd == null) {
                    continue;
                }
                switch(cmd.command) {
                    case 'C':
                        CreateCanvas(cmd.startPosX, cmd.startPosY);
                        break;

                    case 'B':
                        palette.PutAndSelect(new Letter(cmd.color));
                    case 'R':
                    case 'L':
                        if(canvas == null) { System.out.println("Create canvas first!"); break;}
                        Brush rlBrush = brushFactory.get(cmd.command);
                        palette.select(new InkKey(cmd.color));
                        rlBrush.setRange(new Range(cmd.startPosX, cmd.startPosY, cmd.endPosX, cmd.endPosY));
                        canvas.draw(rlBrush);
                        break;
                    case 'Q':
                        stop();
                        break;

                    default:
                        throw new InvalidCommandException();
                }
                if(canvas != null) {
                    canvas.render();
                }

            } catch (InvalidCommandException e) {
                Util.log("Bad command");
            } catch (InvalidRangeException r) {
                Util.log("Invalid coordinates");
            } catch(OutOfMemoryError oom) {
                Util.log("Out of memory");
            } catch (Exception e) {
                Util.log(e);
            }

        }

    }

    private synchronized void CreateCanvas(int horizontal, int height) throws InvalidRangeException {
        canvas = new SimpleCanvas(horizontal, height, storeFactory, palette, renderer);
    }

    private Command getInput() throws InvalidCommandException {
        System.out.print("enter command: ");
        String line = scanner.nextLine().trim();

        if(line.length() == 0) {
            return null;
        }

        String[] tokens = line.split(" ");
        try {
            if (tokens[0].length() != 1) {
                throw new InvalidCommandException();
            }
            char command = tokens[0].charAt(0);
            switch (command) {
                case 'C':
                    return new Command(command,
                            Integer.parseInt(tokens[1]),
                            Integer.parseInt(tokens[2]),
                            0,
                            0,
                            '\0');

                case 'L':
                case 'R':
                    return new Command(command,
                            Integer.parseInt(tokens[1]),
                            Integer.parseInt(tokens[2]),
                            Integer.parseInt(tokens[3]),
                            Integer.parseInt(tokens[4]),
                            'x'
                            );
                case 'B':
                    if (tokens[3].length() != 1) {
                        throw new InvalidCommandException();
                    }
                    return new Command(command,
                            Integer.parseInt(tokens[1]),
                            Integer.parseInt(tokens[2]),
                            Integer.parseInt(tokens[1]),
                            Integer.parseInt(tokens[2]),
                            tokens[3].charAt(0)
                    );
                case 'Q':
                    return new Command(command, 0, 0, 0, 0, '\0');
                default:
                    throw new InvalidCommandException();

            }
        } catch(Exception e) {
            Util.log(String.format("Bad input %s", line));
            throw new InvalidCommandException();
        }

    }

    private class Command {
        char command;
        char color;
        int  startPosX;
        int  startPosY;
        int  endPosX;
        int  endPosY;
        Command(char command, int x1, int y1, int x2, int y2, char color) {
            this.color = color;
            this.command = command;
            this.startPosX = x1;
            this.startPosY = y1;
            this.endPosX = x2;
            this.endPosY = y2;
        }
    }
}
