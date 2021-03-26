package com.sap.gssi.health.controller;

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
    public Mono<String> addNewGame(@PathVariable String gameName) {
        this.gameService.createNewGame(gameName);
        return Mono.just("Game with name " + gameName + " created");
    }

    @PostMapping(path = "/{gameName}/players/add", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> addPlayers(@PathVariable String gameName, @RequestBody List<Player> players) {
        this.gameService.addPlayersToGameSession(gameName, players);
        return Mono.just("Added players to game " + gameName);
    }

    @PostMapping(path = "/{gameName}/start")
    public Mono<String> startGame(@PathVariable String gameName) {
        this.gameService.startGame(gameName);
        return Mono.just("Game with name " + gameName + " started");
    }

    @PostMapping(path = "/{gameName}/finish")
    public Mono<String> finishGame(@PathVariable String gameName) {
        this.gameService.finishGame(gameName);
        return Mono.just("Game with name " + gameName + " finished");
    }

    @PostMapping(path = "/{gameName}/turn/change")
    public Mono<Turn> changeGameTurn(@PathVariable String gameName) {
        return this.gameService.changeGameTurn(gameName);
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
