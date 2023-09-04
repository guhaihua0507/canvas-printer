package com.ghh.canvas;

import com.ghh.canvas.cmd.Command;
import com.ghh.canvas.cmd.CreateCanvasCommand;
import com.ghh.canvas.cmd.DrawLineCommand;
import com.ghh.canvas.cmd.DrawRectangleCommand;

public class CommandParser {
    public Command parseCommand(String s) throws IllegalCommandException {
        if (s == null || s.trim().length() == 0) {
            return null;
        }

        String[] arr = s.trim().split("[ ]+");

        if ("C".equals(arr[0])) {    //create new canvas
            if (arr.length != 3) {
                throw new IllegalCommandException("not a valid Create canvas command");
            }
            return new CreateCanvasCommand(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
        } else if ("L".equals(arr[0])) {       //draw line
            if (arr.length != 5) {
                throw new IllegalCommandException("not a valid Draw Line command");
            }
            return new DrawLineCommand(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
        } else if ("R".equals(arr[0])) {    //draw rectangle
            if (arr.length != 5) {
                throw new IllegalCommandException("not a valid draw rectangle command");
            }
            return new DrawRectangleCommand(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
        } else {
            throw new IllegalCommandException("can not resolve this command: " + s);
        }
    }
}
