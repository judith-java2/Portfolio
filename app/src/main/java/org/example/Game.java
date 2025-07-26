package org.example;

public class Game {
    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private final GameResultChecker resultChecker;

    public Game(Player playerX, Player playerO) {
        this.board = new Board();
        this.playerX = playerX;
        this.playerO = playerO;
        this.resultChecker = new GameResultChecker();
    }

    public String play() {
        Player currentPlayer = playerX;
        board.reset();

        while (true) {
            board.print();
            int move = currentPlayer.chooseMove(board);
            board.makeMove(move, currentPlayer.getSymbol());

            if (resultChecker.hasWinner(board, currentPlayer.getSymbol())) {
                board.print();
                System.out.println("Player " + currentPlayer.getSymbol() + " wins!");
                return currentPlayer.getSymbol();
            } else if (board.isFull()) {
                board.print();
                System.out.println("It's a draw!");
                return "TIE";
            } else {
                currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
            }
        }
    }
}
