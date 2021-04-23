package com.sap.gssi.health.controller;

import com.sap.gssi.domain.GameSession;
import com.sap.gssi.domain.Player;
import com.sap.gssi.domain.Turn;
import com.sap.gssi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class GameController {

    GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(path = "/new/game/{gameName}")
    public Mono<GameSession> addNewGame(@PathVariable String gameName) {
        return this.gameService.createNewGame(gameName);
    }

    @PostMapping(path = "/{gameName}/players/add", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<GameSession> addPlayers(@PathVariable String gameName, @RequestBody List<Player> players) {
        return this.gameService.addPlayersToGameSession(gameName, players);
    }

    @PostMapping(path = "/{gameName}/start")
    public Mono<GameSession> startGame(@PathVariable String gameName) {
        return this.gameService.startGame(gameName);
    }

    @PostMapping(path = "/{gameName}/finish")
    public Mono<GameSession> finishGame(@PathVariable String gameName) {
        return this.gameService.finishGame(gameName);
    }

    @PostMapping(path = "/{gameName}/turn/change")
    public Mono<Turn> changeGameTurn(@PathVariable String gameName) {
        return this.gameService.changeGameTurn(gameName);
    }

    @PostMapping(path = "/{gameName}/turn/reverse")
    public Mono<Turn> reverseGameTurn(@PathVariable String gameName) {
        return this.gameService.reverseGameTurn(gameName);
    }

    @PostMapping(path = "/{gameName}/turn/skip")
    public Mono<Turn> skipGameTurn(@PathVariable String gameName) {
        return this.gameService.skipGameTurn(gameName);
    }

    @GetMapping(path = "/{gameName}/players")
    public Flux<Player> getPlayers(@PathVariable String gameName) {
        return this.gameService.getPlayers(gameName);
    }

    @GetMapping(path = "/{gameName}/turn")
    public Mono<Turn> getGameTurn(@PathVariable String gameName) {
        return this.gameService.getGameTurn(gameName);
    }
}
