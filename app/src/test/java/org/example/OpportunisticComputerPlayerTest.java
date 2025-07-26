package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OpportunisticComputerPlayerTest {
    private OpportunisticComputerPlayer computer;
    private Board board;

    @BeforeEach
    void setUp() {
        computer = new OpportunisticComputerPlayer("X");
        board = new Board();
    }

    @Test
    void testFirstMoveIsCorner() {
        int move = computer.chooseMove(board);
        List<Integer> corners = List.of(1, 3, 7, 9);
        assertTrue(corners.contains(move), "First move should be a corner.");
    }

    @Test
    void testSecondMoveCenterIfAvailable() {

        board.makeMove(1, "O");

        int move = computer.chooseMove(board);
        assertEquals(5, move, "Second move should be center if available.");
    }

    @Test
    void testWinningMoveTaken() {

        board.makeMove(1, "X");
        board.makeMove(2, "X");
        board.makeMove(5, "O");

        int move = computer.chooseMove(board);
        assertEquals(3, move, "Computer should take winning move.");
    }

    @Test
    void testBlockingMoveTaken() {

        board.makeMove(1, "O");
        board.makeMove(2, "O");
        board.makeMove(5, "X"); 

        int move = computer.chooseMove(board);
        assertEquals(3, move, "Computer should block opponent from winning.");
    }

    @RepeatedTest(5)
    void testRandomMoveWhenNoOtherRulesApply() {

        board.makeMove(3, "X");
        board.makeMove(4, "O");
        board.makeMove(5, "X");
        board.makeMove(6, "O");

        int move = computer.chooseMove(board);
        assertTrue(List.of(7, 8, 9).contains(move), "Should pick a random valid move.");
    }
}
