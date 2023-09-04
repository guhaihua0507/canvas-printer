package com.ghh.canvas;

import com.ghh.canvas.cmd.CanvasCommand;
import com.ghh.canvas.cmd.DrawLineCommand;
import com.ghh.canvas.cmd.DrawRectangleCommand;

import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;

public class Canvas {
    private static final char DEFAULT_DRAW_CHAR = 'x';
    private static final char DEFAULT_V_BORDER_CHAR = '-';
    private static final char DEFAULT_H_BORDER_CHAR = '|';
    private static final char DEFAULT_SPACE = ' ';

    private char drawChar = DEFAULT_DRAW_CHAR;
    private char vBorderChar = DEFAULT_V_BORDER_CHAR;
    private char hBorderChar = DEFAULT_H_BORDER_CHAR;
    private char space = DEFAULT_SPACE;

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
    private final Deque<CanvasCommand> commands = new ArrayDeque<>();

    private Canvas(int width, int height, PrintStream out) {
        this(width, height);
        if (out != null) {
            this.out = out;
        }
    }

    private Canvas(int width, int height) {
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
     * @param x1 x of point 1
     * @param y1 y of point 1
     * @param x2 x of point 2
     * @param y2 y of point 2
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
     * @param h the index of horizontal line (start with 1)
     * @return
     */
    private String buildHorizonLine(int h) {
        StringBuilder line = new StringBuilder();
        line.append(hBorderChar);
        for (int i = 0; i < width; i++) {
            if (grid[i][h] > 0) {
                line.append(drawChar);
            } else {
                line.append(space);
            }
        }
        line.append(hBorderChar);
        return line.toString();
    }

    private String buildHorizontalBorder() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < width + 2; i++) {
            line.append(vBorderChar);
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

    private void setDrawChar(char drawChar) {
        this.drawChar = drawChar;
    }

    private void setVBorderChar(char vBorderChar) {
        this.vBorderChar = vBorderChar;
    }

    private void setHBorderChar(char hBorderChar) {
        this.hBorderChar = hBorderChar;
    }

    private void setSpace(char space) {
        this.space = space;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer width = null;
        private Integer height = null;
        private Character drawChar = null;
        private Character vBorderChar = null;
        private Character hBorderChar = null;
        private Character space = null;
        private PrintStream out = null;

        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder drawChar(char drawChar) {
            this.drawChar = drawChar;
            return this;
        }

        public Builder vBorderChar(char vBorderChar) {
            this.vBorderChar = vBorderChar;
            return this;
        }

        public Builder hBorderChar(char hBorderChar) {
            this.hBorderChar = hBorderChar;
            return this;
        }

        public Builder space(char space) {
            this.space = space;
            return this;
        }

        public Builder printStream(PrintStream out) {
            this.out = out;
            return this;
        }

        public Canvas build() {
            if (width == null || height == null) {
                throw new IllegalStateException("canvas size not specified");
            }
            Canvas canvas = new Canvas(width, height, out);
            if (drawChar != null) {
                canvas.setDrawChar(drawChar);
            }
            if (vBorderChar != null) {
                canvas.setVBorderChar(vBorderChar);
            }
            if (hBorderChar != null) {
                canvas.setHBorderChar(hBorderChar);
            }
            if (space != null) {
                canvas.setSpace(space);
            }
            return canvas;
        }
    }
}
