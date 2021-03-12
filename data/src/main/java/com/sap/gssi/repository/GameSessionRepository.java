package com.sap.gssi.repository;

import com.sap.gssi.domain.GameSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class GameSessionRepository implements IGameSessionRepository {

    HashMap<String, GameSession> gameSessions = new HashMap<>();

    @Override
    public void addGameSession(GameSession gameSession) {
        gameSessions.put(gameSession.getGameName(), gameSession);
    }

    @Override
    public void updateGameSession(GameSession gameSession) {
        gameSessions.replace(gameSession.getGameName(), gameSession);
    }

    @Override
    public GameSession getGameSession(String gameName) {
        return gameSessions.get(gameName);
    }
}
