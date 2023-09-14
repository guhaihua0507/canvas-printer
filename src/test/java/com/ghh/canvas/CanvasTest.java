package com.ghh.canvas;

import com.ghh.canvas.cmd.DrawLineCommand;
import com.ghh.canvas.cmd.DrawRectangleCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CanvasTest {

    @Test
    public void testNewCanvas() {
        Canvas canvas = Canvas.builder().size(20, 8).build();
        canvas.print();
    }

    @Test
    public void testDrawLine() {
        Canvas canvas = Canvas.builder().size(20, 8).build();
        canvas.drawLine(new DrawLineCommand(1, 2, 6, 2));
        canvas.print();

        canvas.drawLine(new DrawLineCommand(6, 3, 6, 4));
        canvas.print();

        //out of the border
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            canvas.drawLine(new DrawLineCommand(1, 5, 1, 10));
        });

        //only horizontal or vertical lines are supported
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            canvas.drawLine(new DrawLineCommand(1, 2, 4, 5));
        });
    }

    @Test
    public void testDrawRectangle() {
        Canvas canvas = Canvas.builder().size(20, 8).build();
        canvas.drawRectangle(new DrawRectangleCommand(16, 1, 20, 3));
        canvas.print();

        //(x1, y1) should be less than or equal to (x2,y2)
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            canvas.drawRectangle(new DrawRectangleCommand(4, 5, 1, 2));
        });

        //out of border
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            canvas.drawRectangle(new DrawRectangleCommand(2, 5, 10, 10));
        });
    }

    @Test
    public void testBuildCanvas() {
        Canvas canvas = Canvas.builder().size(20, 10).drawChar('x').vBorderChar('*').hBorderChar('*').space('.').build();
        canvas.drawRectangle(new DrawRectangleCommand(1, 1, 5, 5));
        canvas.print();
    }


    //------more test case---------------------------------------------------------

    @Test
    public void testCreateIllegalCanvas() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Canvas.builder().build();
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> Canvas.builder().size(-1, 10).build());
        Assertions.assertThrows(IllegalArgumentException.class, () -> Canvas.builder().size(10, -1).build());
        Assertions.assertThrows(IllegalArgumentException.class, () -> Canvas.builder().size(101, 10).build());
    }

    @Test
    public void testInvalidDraw() {
        Canvas canvas = Canvas.builder().size(10, 10).build();

        DrawRectangleCommand drc = new DrawRectangleCommand(1, 1, 101, 100);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            drc.draw(canvas);
        });

        DrawLineCommand dlc = new DrawLineCommand(1, 1, 11, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            dlc.draw(canvas);
        });

        DrawLineCommand dlc2 = new DrawLineCommand(-1, 1, 10, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            dlc2.draw(canvas);
        });

        DrawLineCommand dlc3 = new DrawLineCommand(1, 0, 10, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            dlc3.draw(canvas);
        });
    }

    @Test
    public void testPrintToFile() throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("d:/canvas.txt"));
        Canvas canvas = Canvas.builder().size(10, 10).printStream(out).build();
        DrawLineCommand cmd = new DrawLineCommand(5, 5, 5, 9);
        cmd.draw(canvas);
        canvas.print();
        out.close();
    }
}
