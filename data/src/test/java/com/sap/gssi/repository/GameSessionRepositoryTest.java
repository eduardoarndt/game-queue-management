package com.sap.gssi.repository;

import com.sap.gssi.domain.GameSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionRepositoryTest {

    GameSessionRepository gameSessionRepository;

    @BeforeEach
    void beforeEach(){
        gameSessionRepository = new GameSessionRepository();
    }

    @Test
    void addGameSession() {
        GameSession expectedGameSession = new GameSession("test");
        gameSessionRepository.addGameSession(expectedGameSession);

        GameSession actualGameSession = gameSessionRepository.gameSessions.get("test");

        assertEquals(expectedGameSession, actualGameSession);
    }

    @Test
    void updateGameSession() {
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
        GameSession expectedGameSession = new GameSession("test");
        gameSessionRepository.addGameSession(expectedGameSession);

        GameSession actualGameSession = gameSessionRepository.getGameSession("test");

        assertEquals(expectedGameSession, actualGameSession);
    }
}