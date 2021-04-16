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

    public Mono<Void> createNewGame(String gameName) {
        GameSession gameSession = new GameSession(gameName);
        this.gameSessionRepository.addGameSession(gameSession);
        return Mono.empty();
    }

    private Mono<GameSession> retrieveGameSession(String gameName) {
        return Mono.just(this.gameSessionRepository.getGameSession(gameName));
    }

    public Mono<Void> addPlayersToGameSession(String gameName, List<Player> players) {
        return this.retrieveGameSession(gameName).map(gameSession -> {
            gameSession.setPlayers(players);
            this.gameSessionRepository.updateGameSession(gameSession);
            return gameSession;
        }).then();
    }

    public Mono<Void> startGame(String gameName) {
        return this.retrieveGameSession(gameName).map(gameSession -> {
            gameSession.setStarted(true);

            List<Player> players = gameSession.getPlayers();
            this.setTurn(gameSession, players.get(0), players.get(1));

            this.gameSessionRepository.updateGameSession(gameSession);

            return gameSession;
        }).then();
    }

    public Mono<Void> finishGame(String gameName) {
        return this.retrieveGameSession(gameName).map(gameSession -> {
            gameSession.setEnded(true);
            this.gameSessionRepository.updateGameSession(gameSession);
            return gameSession;
        }).then();
    }

    public Flux<Player> getPlayers(String gameName) {
        Mono<GameSession> gameSession = this.retrieveGameSession(gameName);
        return gameSession.map(GameSession::getPlayers).flatMapMany(Flux::fromIterable);
    }

    public Mono<Turn> getGameTurn(String gameName) {
        return this.retrieveGameSession(gameName).map(GameSession::getTurn);
    }

    public Mono<Turn> changeGameTurn(String gameName) {
        return this.retrieveGameSession(gameName).map(gameSession -> {
            List<Player> players = gameSession.getPlayers();
            players.add(players.get(0));
            players.remove(0);

            this.setTurn(gameSession, players.get(0), players.get(1));
            return gameSession.getTurn();
        });
    }

    public Mono<Turn> reverseGameTurn(String gameName) {
        return this.retrieveGameSession(gameName).map(gameSession -> {
            List<Player> players = gameSession.getPlayers();
            Collections.reverse(players);

            this.setTurn(gameSession, players.get(0), players.get(1));
            return gameSession.getTurn();
        });
    }


    public Mono<Turn> skipGameTurn(String gameName) {
        return this.retrieveGameSession(gameName).map(gameSession -> {
            List<Player> players = gameSession.getPlayers();
            for (int i = 0; i < 2; i++) {
                players.add(players.get(0));
                players.remove(0);
            }

            this.setTurn(gameSession, players.get(0), players.get(1));
            return gameSession.getTurn();
        });
    }

    private Mono<Void> setTurn(GameSession gameSession, Player current, Player next) {
        gameSession.setTurn(new Turn(current, next));
        return Mono.empty();
    }
}
