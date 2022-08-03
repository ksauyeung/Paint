This is a paint program, not unlike Microsoft Paint.
Pure Java, no fancy frameworks, except...
Tests are in JUnit and Mockito

The Main.class is in /build/classes/java/main

Run by going to this folder and doing: java Main
If you don't want to display outputs, you can run with a null renderer by doing: java Main norender

Command 		Description
C w h           Should create a new canvas of width w and height h.
L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only
                horizontal or vertical lines are supported. Horizontal and vertical lines
                will be drawn using the 'x' character.
R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and
                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn
                using the 'x' character.
B x y c         Should fill the entire area connected to (x,y) with "colour" c. The
                behavior of this is the same as that of the "bucket fill" tool in paint
                programs.
Q               Should quit the program.

Commands are case-sensitive.
Coordinates are 1 based, i.e., top left corner is (1,1)
End coordinates are allowed to be outside of canvas, i.e., MSPaint behavior. Of course only elements within the canvas will be rendered.


----- Sample I/O ------

Below is a sample run of the program. User input is prefixed with enter command:

enter command: C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------

enter command: L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------

enter command: L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------

enter command: R 14 1 18 3
----------------------
|             xxxxx  |
|xxxxxx       x   x  |
|     x       xxxxx  |
|     x              |
----------------------

enter command: B 10 3 o
----------------------
|oooooooooooooxxxxxoo|
|xxxxxxooooooox   xoo|
|     xoooooooxxxxxoo|
|     xoooooooooooooo|
----------------------

enter command: Q



------- Interfaces ---------

Brush - these are the same thing as the "tools" in paint or photoshop. To add a new tool, implement this interface, add it to the SimpleBrushFactory
Ink - These are like the "colors" in paint or photoshop, in theory it can hold whatever data you want, it's up to the renderer to interpret. To add a new ink (or color), implement th is interface and add it to the _palette_
Palette - These are like the color palette as in paint or photoshop, nothing special, just a collection of _Inks_. You can implement and define extra palettes.
Canvas - same as paint or photoshop, defines the area you are allowed to draw on. Current 
Render - Interprets data on canvas and renders the canvas and content to whatever
Painter - Performs action using the above things

------- Classes --------------

Line - Brush, Draws a line.
Rectangle - Brush, Draws a rectangle
Bucket - Brush, Bucket fills an enclosed area
Letter - Ink, holds one character data
SimpleCanvas - Canvas, a rectangular canvas
InkPalette - Palette, holds any Inks
SamplePainter - Painter, a simple painter that asks users for what to do via on-screen command prompt, draws on the canvas

------- The Idea ----------

The idea is that Painter....
1. defines a canvas.
2. Selects a brush from the palette
3. Draws on the canvas with the brush
4. Renderer renders the canvas 

-------- TODO -------------

Rewrite Bucket using threads to be more performant.
Support diagonal lines
