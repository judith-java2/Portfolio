package org.example;

import java.util.Arrays;

public class Board {
    private final String[] cells = new String[9];

    public Board() {
        reset();
    }

    public void reset() {
        for (int i = 0; i < 9; i++) {
            cells[i] = String.valueOf(i + 1);
        }
    }

    public boolean isValidMove(int move) {
        return move >= 1 && move <= 9 && cells[move - 1].equals(String.valueOf(move));
    }

    public void makeMove(int move, String symbol) {
        cells[move - 1] = symbol;
    }

    public void print() {
        System.out.println();
        System.out.printf("  %s  |  %s  |  %s%n", cells[0], cells[1], cells[2]);
        System.out.println("-----+-----+-----");
        System.out.printf("  %s  |  %s  |  %s%n", cells[3], cells[4], cells[5]);
        System.out.println("-----+-----+-----");
        System.out.printf("  %s  |  %s  |  %s%n%n", cells[6], cells[7], cells[8]);
    }

    public String[] getCells() {
        return Arrays.copyOf(cells, 9);
    }

    public boolean isFull() {
        for (String cell : cells) {
            if (cell.matches("\\d")) return false;
        }
        return true;
    }
}
