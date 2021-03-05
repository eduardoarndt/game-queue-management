package com.sap.gssi.service;

import com.sap.gssi.domain.GameSession;
import com.sap.gssi.repository.IGameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    IGameSessionRepository gameSessionRepository;

    @Autowired
    public GameService(IGameSessionRepository gameSessionRepository) {
        this.gameSessionRepository = gameSessionRepository;
    }

    public void createNewGame(String gameName) {
        GameSession gameSession = new GameSession(gameName);
        this.gameSessionRepository.addGameSession(gameSession);
    }

}
