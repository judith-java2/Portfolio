package org.example;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final String symbol;
    private final Scanner scanner;

    public HumanPlayer(String symbol, Scanner scanner) {
        this.symbol = symbol;
        this.scanner = scanner;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int chooseMove(Board board) {
        while (true) {
            System.out.print("Player " + symbol + ", enter your move (1-9): ");
            String input = scanner.nextLine();
            try {
                int move = Integer.parseInt(input);
                if (board.isValidMove(move)) {
                    return move;
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid move. Please try again.");
        }
    }
}
