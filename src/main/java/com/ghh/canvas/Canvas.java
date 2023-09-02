package com.ghh.canvas;

import com.ghh.canvas.cmd.CanvasCommand;
import com.ghh.canvas.cmd.DrawLineCommand;
import com.ghh.canvas.cmd.DrawRectangleCommand;

import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;

public class Canvas {
    private static final char DRAW_CHAR = 'x';
    private static final char V_BORDER_CHAR = '-';
    private static final char H_BORDER_CHAR = '|';
    private static final char SPACE = ' ';

    private final int width;
    private final int height;
    /*
     * this array store the current state of this canvas
     */
    private final int[][] grid;

    /*
     * the print stream to print this canvas
     */
    private PrintStream out;

    /*
     * commands stack.
     * can be used to rollback draw commands
     */
    private Deque<CanvasCommand> commands = new ArrayDeque<>();

    public Canvas(int width, int height, PrintStream out) {
        this(width, height);
        this.out = out;
    }

    public Canvas(int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("width must be larger than 0");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("height must be larger than 0");
        }
        if (width > 100 || height > 100) {
            throw new IllegalArgumentException("please input the width or height less than or equal to 100");
        }
        this.width = width;
        this.height = height;
        grid = new int[width][height];
        out = System.out;
    }

    /**
     * add a line to this canvas.
     * mark every grid on this line to true
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    private void addLine(int x1, int y1, int x2, int y2) {
        validateDrawPoint(x1, y1);
        validateDrawPoint(x2, y2);

        if (x1 != x2 && y1 != y2) {
            throw new IllegalArgumentException("only horizontal or vertical lines are supported");
        }

        if (x1 == x2) {
            int d = y1 <= y2 ? 1 : -1;
            int y = y1;
            while (y != y2) {
                grid[x1 - 1][y - 1]++;
                y += d;
            }
            grid[x2 - 1][y2 - 1]++;
        } else {        //y1 == y2
            int d = x1 <= x2 ? 1 : -1;
            int x = x1;
            while (x != x2) {
                grid[x - 1][y1 - 1]++;
                x += d;
            }
            grid[x2 - 1][y2 - 1]++;
        }
    }

    public void drawLine(DrawLineCommand command) {
        int[] p1 = command.getPoint1();
        int[] p2 = command.getPoint2();
        addLine(p1[0], p1[1], p2[0], p2[1]);
        commands.addLast(command);
    }

    public void drawRectangle(DrawRectangleCommand command) {
        int[] ul = command.getUpperLeftPoint();
        int[] lr = command.getLowerRightPoint();

        validateDrawPoint(ul[0], ul[1]);
        validateDrawPoint(lr[0], lr[1]);

        addLine(ul[0], ul[1], lr[0], ul[1]);
        addLine(ul[0], ul[1], ul[0], lr[1]);
        addLine(lr[0], lr[1], lr[0], ul[1]);
        addLine(lr[0], lr[1], ul[0], lr[1]);
    }

//    public void drawLine(int x1, int y1, int x2, int y2) {
//        addLine(x1, y1, x2, y2);
//    }
//
//    public void drawRectangle(int x1, int y1, int x2, int y2) {
//        addLine(x1, y1, x1, y2);
//        addLine(x1, y1, x2, y1);
//        addLine(x2, y2, x2, y1);
//        addLine(x2, y2, x1, y2);
//    }

    /**
     * print current state of this canvas
     */
    public void print() {
        String border = buildHorizontalBorder();
        out.println(border);
        for (int h = 0; h < height; h++) {
            out.println(buildHorizonLine(h));
        }
        out.println(border);
    }

    /**
     * build the specified horizon line string
     *
     * @param h
     * @return
     */
    private String buildHorizonLine(int h) {
        StringBuilder line = new StringBuilder();
        line.append(H_BORDER_CHAR);
        for (int i = 0; i < width; i++) {
            if (grid[i][h] > 0) {
                line.append(DRAW_CHAR);
            } else {
                line.append(SPACE);
            }
        }
        line.append(H_BORDER_CHAR);
        return line.toString();
    }

    private String buildHorizontalBorder() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < width + 2; i++) {
            line.append(V_BORDER_CHAR);
        }
        return line.toString();
    }

    private void validateDrawPoint(int x, int y) {
        if (x <= 0) {
            throw new IllegalArgumentException("x must larger than 0");
        }
        if (x > width) {
            throw new IllegalArgumentException("x must not larger than " + width);
        }
        if (y <= 0) {
            throw new IllegalArgumentException("y must larger than 0");
        }
        if (y > height) {
            throw new IllegalArgumentException("y must not larger than " + height);
        }
    }
}
