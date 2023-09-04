package com.ghh.canvas.cmd;

public class CreateCanvasCommand implements Command {
    private final int width;
    private final int height;

    public CreateCanvasCommand(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "C " + width + " " + height;
    }
}
