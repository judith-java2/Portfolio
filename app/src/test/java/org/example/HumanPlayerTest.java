package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class HumanPlayerTest {

    @Test
    public void testChooseMoveValidInput() {
        String input = "5\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Board board = new Board();
        HumanPlayer player = new HumanPlayer("X", scanner);

        int move = player.chooseMove(board);
        assertEquals(5, move);
    }

    @Test
    public void testChooseMoveInvalidThenValidInput() {
        String input = "abc\n10\n2\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Board board = new Board();
        HumanPlayer player = new HumanPlayer("O", scanner);

        int move = player.chooseMove(board);
        assertEquals(2, move);
    }
}
