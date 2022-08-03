package paint.renderer;

import paint.Util;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Renders elements as character to stream
 */
public class CharacterRenderer implements Renderer {

    private OutputStream out;

    public CharacterRenderer(OutputStream outputStream) {
        out = outputStream;
    }

    @Override
    public void renderElement(Object element) {
        try {
            if(element == null) {
                out.write((int)' ');
            } else {
                Character c = (Character) element;
                out.write((int) c);
            }
            out.flush();
        } catch (ClassCastException ce) {
            System.out.print('\0');
        } catch (IOException e) {
            Util.log(e);
        }
    }
}
