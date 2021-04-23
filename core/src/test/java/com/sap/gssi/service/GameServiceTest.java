package com.sap.gssi.service;

import com.sap.gssi.domain.GameSession;
import com.sap.gssi.domain.Player;
import com.sap.gssi.repository.IGameSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    IGameSessionRepository gameSessionRepository = Mockito.mock(IGameSessionRepository.class);
    GameService gameService;

    @BeforeEach
    void beforeEach(){
        gameService = new GameService(this.gameSessionRepository);
    }

    @Test
    void createNewGame() {
        StepVerifier.create(gameService.createNewGame("test")).verifyComplete();
        Mockito.verify(gameSessionRepository, Mockito.times(1))
                .addGameSession(Mockito.any(GameSession.class));
    }

    @Test
    void addPlayersToGameSession() {
        GameSession gameSession = new GameSession("test");
        mockRetrieve(gameSession);

        List<Player> players = Collections.singletonList(
                new Player("Player", new Date(), true, 2, false));

        StepVerifier.create(gameService.addPlayersToGameSession("test", players))
                .verifyComplete();

        Mockito.verify(gameSessionRepository, Mockito.times(1))
                .addGameSession(Mockito.any(GameSession.class));
    }

    @Test
    void startGame() {
    }

    @Test
    void finishGame() {
    }

    @Test
    void getPlayers() {
    }

    @Test
    void getGameTurn() {
    }

    @Test
    void changeGameTurn() {
    }

    @Test
    void reverseGameTurn() {
    }

    @Test
    void skipGameTurn() {
    }

    private void mockRetrieve(GameSession gameSession){
        Mockito.when(this.gameSessionRepository.getGameSession(gameSession.getGameName())).thenReturn(gameSession);
    }
}