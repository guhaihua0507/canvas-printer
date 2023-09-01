package com.ghh.canvas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        Canvas canvas = null;
//        while(true) {
//            if (in.hasNextLine()) {
//                String line = in.nextLine();
//                System.out.println(line);
//                if ("Q".equals(line)) {
//                    System.out.println("Bye!!");
//                    return;
//                }
//            }
//        }
        Canvas canvas = new Canvas(20, 4);
        canvas.drawLine(1, 2, 6, 2);
        canvas.drawLine(6, 3, 6, 4);
        canvas.drawRectangle(16, 1, 18, 3);
    }
}
