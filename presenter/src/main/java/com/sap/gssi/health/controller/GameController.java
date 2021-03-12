package com.sap.gssi.health.controller;

import com.sap.gssi.domain.GameSession;
import com.sap.gssi.domain.Player;
import com.sap.gssi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
        GameSession gameSession = this.gameService.retrieveGameSession(gameName);
        this.gameService.addPlayersToGameSession(gameSession, players);

        return Mono.just("Added players to game " + gameName);
    }
}
