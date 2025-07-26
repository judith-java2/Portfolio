package org.example;

public class GameResultChecker {

    private static final int[][] WIN_CONDITIONS = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, 
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
        {0, 4, 8}, {2, 4, 6}            
    };

    public boolean hasWinner(Board board, String symbol) {
        String[] cells = board.getCells();
        for (int[] condition : WIN_CONDITIONS) {
            if (cells[condition[0]].equals(symbol) &&
                cells[condition[1]].equals(symbol) &&
                cells[condition[2]].equals(symbol)) {
                return true;
            }
        }
        return false;
    }
}
