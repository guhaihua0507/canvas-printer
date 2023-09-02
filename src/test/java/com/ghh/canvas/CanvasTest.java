package com.ghh.canvas;

import com.ghh.canvas.cmd.DrawLineCommand;
import com.ghh.canvas.cmd.DrawRectangleCommand;
import org.junit.Test;

public class CanvasTest {

    @Test
    public void testNewCanvas() {
        Canvas canvas = new Canvas(20, 8);
        canvas.print();
    }

    @Test
    public void testDrawLine() {
        Canvas canvas = new Canvas(20, 8);
        canvas.drawLine(new DrawLineCommand(1, 2, 6, 2));
        canvas.print();

        canvas.drawLine(new DrawLineCommand(6, 3, 6, 4));
        canvas.print();

        //out of the border
        try {
            canvas.drawLine(new DrawLineCommand(1, 5, 1, 10));
            canvas.print();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //only horizontal or vertical lines are supported
        try {
            canvas.drawLine(new DrawLineCommand(1, 2, 4, 5));
            canvas.print();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testDrawRectangle() {
        Canvas canvas = new Canvas(20, 8, System.out);
        canvas.drawRectangle(new DrawRectangleCommand(16, 1, 20, 3));
        canvas.print();

        //(x1, y1) should be less than or equal to (x2,y2)
        try {
            canvas.drawRectangle(new DrawRectangleCommand(4, 5, 1, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //out of border
        try {
            canvas.drawRectangle(new DrawRectangleCommand(2, 5, 10, 10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
