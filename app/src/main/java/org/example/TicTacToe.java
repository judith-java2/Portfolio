package org.example;

import java.util.Scanner;

public class TicTacToe {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GameLog gameLog = new GameLog();

    private char[] board;
    private char currentPlayer;

    public TicTacToe(char firstPlayer) {
        board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = (char) ('1' + i);
        }
        currentPlayer = firstPlayer;
    }

    public void play() {
        boolean gameEnded = false;

        while (!gameEnded) {
            printBoard();
            int move = getUserMove();
            board[move - 1] = currentPlayer;

            if (checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins! The current log is:");
                gameLog.recordWin(String.valueOf(currentPlayer));
                gameLog.printLog();
                gameEnded = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a tie! The current log is:");
                gameLog.recordWin("Tie");
                gameLog.printLog();
                gameEnded = true;
            } else {
                switchPlayer();
            }
        }
    }

    private void printBoard() {
        System.out.println();
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);
        System.out.println();
    }

    private int getUserMove() {
        int move;
        while (true) {
            System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");
            String input = scanner.nextLine();
            try {
                move = Integer.parseInt(input);
                if (move < 1 || move > 9) {
                    System.out.println("Invalid move. Please enter a number from 1 to 9.");
                    continue;
                }
                if (board[move - 1] == 'X' || board[move - 1] == 'O') {
                    System.out.println("That spot is already taken. Try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return move;
    }

    private boolean checkWin() {
        int[][] wins = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
        };
        for (int[] win : wins) {
            if (board[win[0]] == currentPlayer &&
                board[win[1]] == currentPlayer &&
                board[win[2]] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (char c : board) {
            if (c != 'X' && c != 'O') {
                return false;
            }
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!");

        char firstPlayer = 'X';
        boolean playAgain = true;
        String lastLoser = null;

        while (playAgain) {
            if (lastLoser != null && (lastLoser.equals("X") || lastLoser.equals("O"))) {
                firstPlayer = lastLoser.charAt(0);
                System.out.println("Great! This time " + firstPlayer + " will go first!");
            } else {
                firstPlayer = 'X';
            }

            TicTacToe game = new TicTacToe(firstPlayer);
            game.play();
            if (gameLog.getXWins() > gameLog.getOWins()) {
                lastLoser = "O";
            } else if (gameLog.getOWins() > gameLog.getXWins()) {
                lastLoser = "X";
            } else {
                lastLoser = "X";
            }

            System.out.print("Would you like to play again (yes/no)? ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                playAgain = false;
                System.out.println("Writing the game log to disk. Please see game.txt for the final statistics!");
                gameLog.saveToDisk("game.txt");
            }
        }

        scanner.close();
    }
}
