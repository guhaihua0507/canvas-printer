package com.ghh.canvas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Canvas canvas = null;
        while (true) {
            if (in.hasNextLine()) {
                String line = in.nextLine();
                if (line != null && "Q".equals(line.trim())) {
                    System.out.println("Bye!!");
                    return;
                }
                if (line != null && ("help".equalsIgnoreCase(line.trim()) || "h".equalsIgnoreCase(line.trim()))) {
                    printHelp();
                    continue;
                }

                int[] cmd = parseCommand(line);
                if (cmd == null) {
                    continue;
                }

                try {
                    if (cmd[0] == 0) {
                        canvas = new Canvas(cmd[1], cmd[2]);
                        canvas.print();
                    } else if (cmd[0] == 1) {
                        if (canvas == null) {
                            System.out.println("canvas should be created first");
                        } else {
                            canvas.drawLine(cmd[1], cmd[2], cmd[3], cmd[4]);
                            canvas.print();
                        }
                    } else if (cmd[0] == 2) {
                        if (canvas == null) {
                            System.out.println("canvas should be created first");
                        } else {
                            canvas.drawRectangle(cmd[1], cmd[2], cmd[3], cmd[4]);
                            canvas.print();
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
//        Canvas canvas = new Canvas(20, 4);
//        canvas.drawLine(1, 2, 6, 2);
//        canvas.drawLine(6, 3, 6, 4);
//        canvas.drawRectangle(16, 1, 18, 3);
    }

    /**
     * parse the command
     * res[0] = 0 : create canvas
     * res[0] = 1 : draw line
     * res[0] = 2 : draw rectangle
     * @param s
     * @return null if command is invalid
     */
    private static int[] parseCommand(String s) {
        if (s == null || s.trim().length() == 0) {
            return null;
        }

        String[] arr = s.trim().split("[ ]+");

        try {
            if ("C".equals(arr[0])) {    //create new canvas
                if (arr.length != 3) {
                    System.out.println("invalid command");
                    return null;
                }
                int[] res = new int[3];
                res[0] = 0;
                res[1] = Integer.parseInt(arr[1]);
                res[2] = Integer.parseInt(arr[2]);
                return res;
            } else if ("L".equals(arr[0])) {       //draw line
                if (arr.length != 5) {
                    System.out.println("invalid command");
                    return null;
                }
                int[] res = new int[5];
                res[0] = 1;
                res[1] = Integer.parseInt(arr[1]);
                res[2] = Integer.parseInt(arr[2]);
                res[3] = Integer.parseInt(arr[3]);
                res[4] = Integer.parseInt(arr[4]);
                return res;
            } else if ("R".equals(arr[0])) {    //draw rectangle
                if (arr.length != 5) {
                    System.out.println("invalid command");
                    return null;
                }
                int[] res = new int[5];
                res[0] = 2;
                res[1] = Integer.parseInt(arr[1]);
                res[2] = Integer.parseInt(arr[2]);
                res[3] = Integer.parseInt(arr[3]);
                res[4] = Integer.parseInt(arr[4]);
                return res;
            } else {
                System.out.println("invalid command");
                printHelp();
                return null;
            }
        } catch (Exception ex) {
            System.out.println("invalid command");
            printHelp();
            return null;
        }
    }

    private static void printHelp() {
        System.out.println("C w h \t\t\t\tCreate new canvas");
        System.out.println("L x1 x2 y1 y2 \t\t Create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported.");
        System.out.println("R x1 y1 x2 y2 \t\t Create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). ");
        System.out.println("Q \t\t\t\t\tQuit the program.");
    }
}
