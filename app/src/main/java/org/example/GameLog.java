package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameLog {
    private int xWins;
    private int oWins;
    private int ties;

    public GameLog() {
        this.xWins = 0;
        this.oWins = 0;
        this.ties = 0;
    }

    public void recordWin(String player) {
        if ("X".equalsIgnoreCase(player)) {
            xWins++;
        } else if ("O".equalsIgnoreCase(player)) {
            oWins++;
        }
    }

    public void recordTie() {
        ties++;
    }

    public int getXWins() {
        return xWins;
    }

    public int getOWins() {
        return oWins;
    }

    public int getTies() {
        return ties;
    }

    public void printLog() {
        System.out.println("Game Log:");
        System.out.println("Player X Wins: " + xWins);
        System.out.println("Player O Wins: " + oWins);
        System.out.println("Ties: " + ties);
    }

    public void saveToDisk(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Player X Wins: " + xWins);
            writer.newLine();
            writer.write("Player O Wins: " + oWins);
            writer.newLine();
            writer.write("Ties: " + ties);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving game log to disk: " + e.getMessage());
        }
    }
}
