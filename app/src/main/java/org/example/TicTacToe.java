package org.example;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameLog gameLog = new GameLog();

        System.out.println("Welcome to Tic-Tac-Toe!");
        boolean playAgain = true;

        while (playAgain) {
            int mode = promptGameMode(scanner);

            Player playerX;
            Player playerO;

            switch (mode) {
                case 1: 
                    playerX = new HumanPlayer("X", scanner);
                    playerO = new HumanPlayer("O", scanner);
                    break;
                case 2: 
                    playerX = new HumanPlayer("X", scanner);
                    playerO = new OpportunisticComputerPlayer("O");
                    break;
                case 3: 
                    playerX = new OpportunisticComputerPlayer("X");
                    playerO = new HumanPlayer("O", scanner);
                    break;
                default:
                    System.out.println("Invalid selection, defaulting to Human vs Human.");
                    playerX = new HumanPlayer("X", scanner);
                    playerO = new HumanPlayer("O", scanner);
                    break;
            }

            Game game = new Game(playerX, playerO);
            String winner = game.play();

            if ("X".equalsIgnoreCase(winner)) {
                gameLog.recordWin("X");
            } else if ("O".equalsIgnoreCase(winner)) {
                gameLog.recordWin("O");
            } else {
                gameLog.recordTie();
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

    private static int promptGameMode(Scanner scanner) {
        System.out.println("What kind of game would you like to play?");
        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. Computer");
        System.out.println("3. Computer vs. Human");
        System.out.print("What is your selection? ");
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int selection = Integer.parseInt(input);
                if (selection >= 1 && selection <= 3) {
                    return selection;
                }
            } catch (NumberFormatException ignored) {}
            System.out.print("Invalid selection. Please enter 1, 2, or 3: ");
        }
    }
}
