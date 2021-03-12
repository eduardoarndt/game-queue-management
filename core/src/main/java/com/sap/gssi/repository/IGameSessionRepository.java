package com.sap.gssi.repository;

import com.sap.gssi.domain.GameSession;

public interface IGameSessionRepository {

    public void addGameSession(GameSession gameSession);
    public void updateGameSession(GameSession gameSession);
    public GameSession getGameSession(String gameName);
}
