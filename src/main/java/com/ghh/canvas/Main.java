package com.ghh.canvas;

import com.ghh.canvas.cmd.CanvasCommand;
import com.ghh.canvas.cmd.Command;
import com.ghh.canvas.cmd.CreateCanvasCommand;
import com.ghh.canvas.cmd.QuitCommand;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CommandParser commandParser = new CommandParser();
        Canvas canvas = null;

        printHelp();

        while (true) {
            System.out.print("enter command: ");
            if (in.hasNextLine()) {
                String line = in.nextLine();
                if (line == null || line.trim().length() == 0) {
                    continue;
                }
                line = line.trim();

                //print help message
                if ("h".equalsIgnoreCase(line) || "help".equalsIgnoreCase(line)) {
                    printHelp();
                    continue;
                }

                if ("Q".equalsIgnoreCase(line)) {
                    System.out.println("Bye!!");
                    return;
                }

                try {
                    Command command = commandParser.parseCommand(line);

                    if (command instanceof CreateCanvasCommand) {
                        CreateCanvasCommand ccc = (CreateCanvasCommand) command;
                        canvas = Canvas.builder().size(ccc.getWidth(), ccc.getHeight()).build();
                        canvas.print();
                    } else if (command instanceof CanvasCommand) {
                        if (canvas == null) {
                            System.out.println("canvas should be created first");
                        } else {
                            CanvasCommand cc = (CanvasCommand) command;
                            cc.draw(canvas);
                            canvas.print();
                        }
                    }
                } catch (IllegalCommandException e) {
                    System.out.println(e.getMessage());
                    printHelp();
                } catch (Exception ex) {
                    ex.printStackTrace(System.out);
                }
            }
        }
    }

    private static void printHelp() {
        System.out.println("C w h: \tCreate new canvas");
        System.out.println("L x1 x2 y1 y2: \t Create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported.");
        System.out.println("R x1 y1 x2 y2: \t Create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). ");
        System.out.println("Q: \tQuit the program.");
    }
}
