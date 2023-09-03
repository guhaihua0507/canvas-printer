package com.ghh.canvas;

import com.ghh.canvas.cmd.DrawLineCommand;
import com.ghh.canvas.cmd.DrawRectangleCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        canvas.drawRectangle(new DrawRectangleCommand(1, 1, 5,5));
        canvas.print();
    }
}
