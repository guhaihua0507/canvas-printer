package com.ghh.canvas.cmd;

import com.ghh.canvas.Canvas;

public interface CanvasCommand extends Command {

    void draw(Canvas canvas);
}
