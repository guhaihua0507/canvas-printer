package com.ghh.canvas;

public class Canvas {
    private final int width;
    private final int height;
    /*
     * this array store the current state of this canvas
     */
    private final boolean[][] grid;

    public Canvas(int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("width must be larger than 0");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("height must be larger than 0");
        }
        this.width = width;
        this.height = height;
        grid = new boolean[width][height];
    }

    /**
     * add a line to this canvas.
     * mark every grid on this line to true
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    private void addLine(int x1, int y1, int x2, int y2) {
        if (x1 <= 0 || x2 <= 0) {
            throw new IllegalArgumentException("x must larger than 0");
        }
        if (x1 > width || x2 > width) {
            throw new IllegalArgumentException("x must not larger than " + width);
        }
        if (y1 <= 0 || y2 <= 0) {
            throw new IllegalArgumentException("y must larger than 0");
        }
        if (y1 > height || y2 > height) {
            throw new IllegalArgumentException("y must not larger than " + height);
        }

        if (x1 != x2 && y1 != y2) {
            throw new IllegalArgumentException("only horizontal or vertical lines are supported");
        }

        if (x1 == x2) {
            int d = y1 <= y2 ? 1 : -1;
            int y = y1;
            while (y != y2) {
                grid[x1 - 1][y - 1] = true;
                y += d;
            }
            grid[x2 - 1][y2 - 1] = true;
        } else {        //y1 == y2
            int d = x1 <= x2 ? 1 : -1;
            int x = x1;
            while (x != x2) {
                grid[x - 1][y1 - 1] = true;
                x += d;
            }
            grid[x2 - 1][y2 - 1] = true;
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        addLine(x1, y1, x2, y2);
        print();
    }

    public void drawRectangle(int x1, int y1, int x2, int y2) {
        addLine(x1, y1, x1, y2);
        addLine(x1, y1, x2, y1);
        addLine(x2, y2, x2, y1);
        addLine(x2, y2, x1, y2);
        print();
    }

    /**
     * print current state of this canvas
     */
    public void print() {
        String border = buildHorizontalBorder();
        System.out.println(border);
        for (int h = 0; h < height; h++) {
            System.out.println(buildHorizonLine(h));
        }
        System.out.println(border);
    }

    /**
     * build the specified horizon line string
     * @param h
     * @return
     */
    private String buildHorizonLine(int h) {
        StringBuilder line = new StringBuilder();
        line.append("|");
        for (int i = 0; i < width; i++) {
            if (grid[i][h]) {
                line.append("x");
            } else {
                line.append(" ");
            }
        }
        line.append("|");
        return line.toString();
    }

    private String buildHorizontalBorder() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < width + 2; i++) {
            line.append("-");
        }
        return line.toString();
    }
}
