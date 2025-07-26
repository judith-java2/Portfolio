package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class GameLogTest {

    @Test
    public void testInitialCounts() {
        GameLog log = new GameLog();
        assertEquals(0, log.getXWins());
        assertEquals(0, log.getOWins());
        assertEquals(0, log.getTies());
    }

    @Test
    public void testIncrementWinsAndTies() {
        GameLog log = new GameLog();
        log.recordWin("X");
        log.recordWin("O");
        log.recordTie();

        assertEquals(1, log.getXWins());
        assertEquals(1, log.getOWins());
        assertEquals(1, log.getTies());
    }

    @Test
    public void testPrintLog() {
        GameLog log = new GameLog();
        log.recordWin("X");
        log.recordTie();

        // Capture system out could be done with a ByteArrayOutputStream but
        // for simplicity we just ensure no exceptions thrown.
        assertDoesNotThrow(log::printLog);
    }

    @Test
    public void testSaveToDisk() throws Exception {
        GameLog log = new GameLog();
        log.recordWin("X");
        log.recordWin("O");
        log.recordTie();

        String filename = "test_game_log.txt";
        log.saveToDisk(filename);

        File file = new File(filename);
        assertTrue(file.exists());
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        assertTrue(content.contains("Player X Wins"));
        assertTrue(content.contains("Player O Wins"));
        assertTrue(content.contains("Ties"));

        file.delete(); 
    }
}
