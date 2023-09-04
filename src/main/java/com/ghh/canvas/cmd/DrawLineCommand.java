package com.ghh.canvas.cmd;

import com.ghh.canvas.Canvas;

public class DrawLineCommand implements CanvasCommand {
    private final int[] point1;
    private final int[] point2;

    public DrawLineCommand(int x1, int y1, int x2, int y2) {
        point1 = new int[]{x1, y1};
        point2 = new int[]{x2, y2};
    }

    public int[] getPoint1() {
        return point1;
    }

    public int[] getPoint2() {
        return point2;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(this);
    }

    @Override
    public String toString() {
        return "L " + point1[0] + " " + point1[1] + " " + point2[0] + " " + point2[1];
    }
}
