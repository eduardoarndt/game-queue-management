package com.sap.gssi.repository;

import com.sap.gssi.domain.GameSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionRepositoryTest {

    @Test
    void addGameSession() {
        GameSessionRepository gameSessionRepository = new GameSessionRepository();

        GameSession expectedGameSession = new GameSession("test");
        gameSessionRepository.addGameSession(expectedGameSession);

        GameSession actualGameSession = gameSessionRepository.gameSessions.get("test");

        assertEquals(expectedGameSession, actualGameSession);
    }

    @Test
    void updateGameSession() {
        GameSessionRepository gameSessionRepository = new GameSessionRepository();

        GameSession firstGameSession = new GameSession("test");
        gameSessionRepository.addGameSession(firstGameSession);

        GameSession expectedGameSession = new GameSession("test");
        expectedGameSession.setStarted(true);
        gameSessionRepository.updateGameSession(expectedGameSession);

        GameSession actualGameSession = gameSessionRepository.gameSessions.get("test");

        assertEquals(expectedGameSession, actualGameSession);
    }

    @Test
    void getGameSession() {
        GameSessionRepository gameSessionRepository = new GameSessionRepository();

        GameSession expectedGameSession = new GameSession("test");
        gameSessionRepository.addGameSession(expectedGameSession);

        GameSession actualGameSession = gameSessionRepository.getGameSession("test");

        assertEquals(expectedGameSession, actualGameSession);
    }
}