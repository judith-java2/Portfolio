package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testInitialBoardSetup() {
        String[] cells = board.getCells();
        for (int i = 0; i < 9; i++) {
            assertEquals(String.valueOf(i + 1), cells[i]);
        }
    }

    @Test
    public void testMakeMove() {
        board.makeMove(1, "X");
        assertEquals("X", board.getCells()[0]);
    }

    @Test
    public void testValidMove() {
        assertTrue(board.isValidMove(1));
        board.makeMove(1, "X");
        assertFalse(board.isValidMove(1));
    }
}
