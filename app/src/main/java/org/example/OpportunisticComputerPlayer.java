package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OpportunisticComputerPlayer implements Player {
    private final String symbol;
    private final String opponentSymbol;

    public OpportunisticComputerPlayer(String symbol) {
        this.symbol = symbol;
        this.opponentSymbol = symbol.equals("X") ? "O" : "X";
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int chooseMove(Board board) {
        String[] cells = board.getCells();
        int moveCount = countMoves(cells);

        if (moveCount == 0) {
            List<Integer> corners = List.of(1, 3, 7, 9);
            for (int corner : corners) {
                if (board.isValidMove(corner)) {
                    return corner;
                }
            }
        }

        if (moveCount == 1 && board.isValidMove(5)) {
            return 5;
        }

        int winningMove = findWinningMove(board, symbol);
        if (winningMove != -1) {
            return winningMove;
        }

        int blockingMove = findWinningMove(board, opponentSymbol);
        if (blockingMove != -1) {
            return blockingMove;
        }

        List<Integer> validMoves = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (board.isValidMove(i)) {
                validMoves.add(i);
            }
        }
        Collections.shuffle(validMoves);
        return validMoves.get(0);
    }

    private int countMoves(String[] cells) {
        int count = 0;
        for (String cell : cells) {
            if (!cell.matches("\\d")) count++;
        }
        return count;
    }

    private int findWinningMove(Board board, String testSymbol) {
        String[] cells = board.getCells();
        int[][] WIN_CONDITIONS = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combo : WIN_CONDITIONS) {
            String a = cells[combo[0]];
            String b = cells[combo[1]];
            String c = cells[combo[2]];

            int[] positions = {combo[0], combo[1], combo[2]};
            String[] values = {a, b, c};

            int countSymbol = 0;
            int countEmpty = 0;
            int emptyIndex = -1;

            for (int i = 0; i < 3; i++) {
                if (values[i].equals(testSymbol)) {
                    countSymbol++;
                } else if (values[i].matches("\\d")) {
                    countEmpty++;
                    emptyIndex = positions[i];
                }
            }

            if (countSymbol == 2 && countEmpty == 1) {
                return emptyIndex + 1;
            }
        }
        return -1;
    }
}
