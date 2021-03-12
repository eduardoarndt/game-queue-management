package com.sap.gssi.service;

import com.sap.gssi.domain.GameSession;
import com.sap.gssi.domain.Player;
import com.sap.gssi.domain.Turn;
import com.sap.gssi.repository.IGameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

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

    private GameSession retrieveGameSession(String gameName) {
        return this.gameSessionRepository.getGameSession(gameName);
    }

    public void addPlayersToGameSession(String gameName, List<Player> players) {
        GameSession gameSession = this.retrieveGameSession(gameName);
        gameSession.setPlayers(players);
        this.gameSessionRepository.updateGameSession(gameSession);
    }

    public void startGame(String gameName) {
        GameSession gameSession = this.retrieveGameSession(gameName);
        gameSession.setStarted(true);

        List<Player> players = gameSession.getPlayers();
        gameSession.setTurn(new Turn(players.get(0), players.get(1)));

        this.gameSessionRepository.updateGameSession(gameSession);
    }

    public void finishGame(String gameName) {
        GameSession gameSession = this.retrieveGameSession(gameName);
        gameSession.setEnded(true);
        this.gameSessionRepository.updateGameSession(gameSession);
    }

    public Flux<Player> getPlayers(String gameName) {
        GameSession gameSession = this.retrieveGameSession(gameName);
        return Flux.fromIterable(gameSession.getPlayers());
    }
}
