package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameResultCheckerTest {
    private Board board;
    private GameResultChecker checker;

    @BeforeEach
    public void setUp() {
        board = new Board();
        checker = new GameResultChecker();
    }

    @Test
    public void testIsWinnerHorizontal() {
        board.makeMove(1, "X");
        board.makeMove(2, "X");
        board.makeMove(3, "X");
        assertTrue(checker.hasWinner(board, "X"));
    }

    @Test
    public void testIsWinnerVertical() {
        board.makeMove(2, "O");
        board.makeMove(5, "O");
        board.makeMove(8, "O");
        assertTrue(checker.hasWinner(board, "O"));
    }

    @Test
    public void testIsWinnerDiagonal() {
        board.makeMove(1, "X");
        board.makeMove(5, "X");
        board.makeMove(9, "X");
        assertTrue(checker.hasWinner(board, "X"));
    }

    @Test
    public void testNoWinner() {
        board.makeMove(1, "X");
        board.makeMove(2, "O");
        board.makeMove(3, "X");
        board.makeMove(4, "X");
        board.makeMove(5, "O");
        board.makeMove(6, "X");
        board.makeMove(7, "O");
        board.makeMove(8, "X");
        board.makeMove(9, "O");
        assertFalse(checker.hasWinner(board, "X"));
        assertFalse(checker.hasWinner(board, "O"));
    }
}
