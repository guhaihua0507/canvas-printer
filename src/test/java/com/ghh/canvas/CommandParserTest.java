package com.ghh.canvas;

import com.ghh.canvas.cmd.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandParserTest {

    @Test
    public void testParseCreateCanvasCmd() throws IllegalCommandException {
        CommandParser parser = new CommandParser();
        Command command = parser.parseCommand("C 10 10");
        System.out.println(command);
    }

    @Test
    public void testParseDrawLineCmd() throws IllegalCommandException {
        CommandParser parser = new CommandParser();
        Command command = parser.parseCommand("L 1 10 2 10");
        System.out.println(command);
    }

    @Test
    public void testParseDrawRectCmd() throws IllegalCommandException {
        CommandParser parser = new CommandParser();
        Command command = parser.parseCommand("R 1 1 10 10");
        System.out.println(command);
    }

    @Test
    public void testUnresolvedCmd() {
        CommandParser parser = new CommandParser();
        Assertions.assertThrows(IllegalCommandException.class, () -> {
            parser.parseCommand("A 1 2 3 4");
        });
    }
}
