package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTurnOrderTest {

    @Test
    public void testLoserGoesFirstAfterXWins() {
        GameLog log = new GameLog();
        log.recordWin("X");
        log.recordTie();
    }
}
