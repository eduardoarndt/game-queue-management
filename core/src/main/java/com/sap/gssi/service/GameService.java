package com.sap.gssi.service;

import com.sap.gssi.domain.GameSession;
import com.sap.gssi.domain.Player;
import com.sap.gssi.domain.Turn;
import com.sap.gssi.repository.IGameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
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
        this.setTurn(gameSession, players.get(0), players.get(1));

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

    public Mono<Turn> getGameTurn(String gameName) {
        GameSession gameSession = this.retrieveGameSession(gameName);
        return Mono.just(gameSession.getTurn());
    }

    public Mono<Turn> changeGameTurn(String gameName) {
        GameSession gameSession = this.retrieveGameSession(gameName);
        List<Player> players = gameSession.getPlayers();

        players.add(players.get(0));
        players.remove(0);

        this.setTurn(gameSession, players.get(0), players.get(1));

        return Mono.just(gameSession.getTurn());
    }

    public Mono<Turn> reverseGameTurn(String gameName) {
        GameSession gameSession = this.retrieveGameSession(gameName);
        List<Player> players = gameSession.getPlayers();
        Collections.reverse(players);

        this.setTurn(gameSession, players.get(0), players.get(1));

        return Mono.just(gameSession.getTurn());
    }

    private void setTurn(GameSession gameSession, Player current, Player next){
        gameSession.setTurn(new Turn(current, next));
    }
}
