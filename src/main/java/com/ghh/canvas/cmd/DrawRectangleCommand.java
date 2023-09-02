package com.ghh.canvas.cmd;

import com.ghh.canvas.Canvas;

public class DrawRectangleCommand implements CanvasCommand {
    private int[] upperLeftPoint;
    private int[] lowerRightPoint;

    public DrawRectangleCommand(int x1, int y1, int x2, int y2) {
        if (x1 > x2 || y1 > y2) {
            throw new IllegalArgumentException("not valid rectangle");
        }
        upperLeftPoint = new int[]{x1, y1};
        lowerRightPoint = new int[]{x2, y2};
    }

    public int[] getUpperLeftPoint() {
        return upperLeftPoint;
    }

    public int[] getLowerRightPoint() {
        return lowerRightPoint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this);
    }

    @Override
    public String toString() {
        return "R " + upperLeftPoint[0] + " " + upperLeftPoint[1] + " " + lowerRightPoint[0] + " " + lowerRightPoint[1];
    }
}
