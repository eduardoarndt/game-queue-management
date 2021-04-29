package com.sap.gssi.service;

import com.sap.gssi.domain.GameSession;
import com.sap.gssi.domain.Player;
import com.sap.gssi.domain.Turn;
import com.sap.gssi.repository.IGameSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    final String GAME_SESSION_NAME = "game_session_name";

    IGameSessionRepository gameSessionRepository = Mockito.mock(IGameSessionRepository.class);
    GameService gameService;

    @BeforeEach
    void beforeEach() {
        gameService = new GameService(this.gameSessionRepository);
    }

    @Test
    void createNewGame() {
        GameSession gameSession = new GameSession(GAME_SESSION_NAME);

        StepVerifier.create(gameService.createNewGame(GAME_SESSION_NAME)).consumeNextWith(actualGameSession -> {
            assertEquals(gameSession, actualGameSession);
        }).verifyComplete();

        Mockito.verify(gameSessionRepository, Mockito.times(1))
                .addGameSession(Mockito.any(GameSession.class));
    }

    @Test
    void addPlayersToGameSession() {
        GameSession gameSession = new GameSession(GAME_SESSION_NAME);
        mockRetrieve(gameSession);

        List<Player> players = Collections.singletonList(
                new Player("Player", new Date(), true, 2, false));

        StepVerifier.create(gameService.addPlayersToGameSession(GAME_SESSION_NAME, players))
                .consumeNextWith(actualGameSession -> {
                    assertSame(actualGameSession.getPlayers(), players);
                })
                .verifyComplete();

        Mockito.verify(gameSessionRepository, Mockito.times(1))
                .updateGameSession(Mockito.any(GameSession.class));
    }

    @Test
    void startGame() {
        GameSession gameSession = new GameSession(GAME_SESSION_NAME);
        Player playerOne = new Player("Player 1", new Date(), true, 1, false);
        Player playerTwo = new Player("Player 2", new Date(), true, 2, false);
        gameSession.setPlayers(Arrays.asList(playerOne, playerTwo));
        mockRetrieve(gameSession);

        StepVerifier.create(gameService.startGame(GAME_SESSION_NAME)).consumeNextWith(actualGameSession -> {
            assertTrue(actualGameSession.getStarted());
            assertEquals(playerOne, actualGameSession.getTurn().getCurrent());
            assertEquals(playerTwo, actualGameSession.getTurn().getNext());
        }).verifyComplete();

        Mockito.verify(gameSessionRepository, Mockito.times(1))
                .updateGameSession(Mockito.any(GameSession.class));
    }

    @Test
    void finishGame() {
        GameSession gameSession = new GameSession(GAME_SESSION_NAME);
        mockRetrieve(gameSession);

        StepVerifier.create(gameService.finishGame(GAME_SESSION_NAME)).consumeNextWith(actualGameSession -> {
            assertTrue(actualGameSession.getFinished());
        }).verifyComplete();

        Mockito.verify(gameSessionRepository, Mockito.times(1))
                .updateGameSession(Mockito.any(GameSession.class));
    }

    @Test
    void getPlayers() {
        GameSession gameSession = new GameSession(GAME_SESSION_NAME);
        Player playerOne = new Player("Player 1", new Date(), true, 1, false);
        Player playerTwo = new Player("Player 2", new Date(), true, 2, false);
        gameSession.setPlayers(Arrays.asList(playerOne, playerTwo));
        mockRetrieve(gameSession);

        StepVerifier.create(gameService.getPlayers(GAME_SESSION_NAME))
                .expectNext(playerOne, playerTwo)
                .expectComplete()
                .verify();
    }

    @Test
    void getGameTurn() {
        GameSession gameSession = new GameSession(GAME_SESSION_NAME);
        Player playerOne = new Player("Player 1", new Date(), true, 1, false);
        Player playerTwo = new Player("Player 2", new Date(), true, 2, false);
        Player playerThree = new Player("Player 3", new Date(), true, 3, false);
        gameSession.setPlayers(Arrays.asList(playerOne, playerTwo, playerThree));

        Turn expectedGameTurn = new Turn(playerOne, playerTwo);
        gameSession.setTurn(expectedGameTurn);
        mockRetrieve(gameSession);

        StepVerifier.create(gameService.getGameTurn(GAME_SESSION_NAME)).consumeNextWith(actualGameTurn -> {
            assertEquals(expectedGameTurn, actualGameTurn);
        }).verifyComplete();
    }

    @Test
    void changeGameTurn() {
        GameSession gameSession = new GameSession(GAME_SESSION_NAME);
        Player playerOne = new Player("Player 1", new Date(), true, 1, false);
        Player playerTwo = new Player("Player 2", new Date(), true, 2, false);
        Player playerThree = new Player("Player 3", new Date(), true, 3, false);
        gameSession.setPlayers(new ArrayList<>(Arrays.asList(playerOne, playerTwo, playerThree)));

        Turn turn = new Turn(playerOne, playerTwo);
        gameSession.setTurn(turn);

        mockRetrieve(gameSession);

        StepVerifier.create(gameService.changeGameTurn(GAME_SESSION_NAME)).consumeNextWith(actualGameTurn -> {
            assertEquals(playerTwo, actualGameTurn.getCurrent());
            assertEquals(playerThree, actualGameTurn.getNext());
        }).verifyComplete();

        Mockito.verify(gameSessionRepository, Mockito.times(1))
                .updateGameSession(Mockito.any(GameSession.class));
    }

    @Test
    void reverseGameTurn() {
        GameSession gameSession = new GameSession(GAME_SESSION_NAME);
        Player playerOne = new Player("Player 1", new Date(), true, 1, false);
        Player playerTwo = new Player("Player 2", new Date(), true, 2, false);
        Player playerThree = new Player("Player 3", new Date(), true, 3, false);
        gameSession.setPlayers(new ArrayList<>(Arrays.asList(playerOne, playerTwo, playerThree)));

        Turn turn = new Turn(playerOne, playerTwo);
        gameSession.setTurn(turn);

        mockRetrieve(gameSession);

        StepVerifier.create(gameService.reverseGameTurn(GAME_SESSION_NAME)).consumeNextWith(actualGameTurn -> {
            assertEquals(playerThree, actualGameTurn.getCurrent());
            assertEquals(playerTwo, actualGameTurn.getNext());
        }).verifyComplete();

        Mockito.verify(gameSessionRepository, Mockito.times(1))
                .updateGameSession(Mockito.any(GameSession.class));
    }

    @Test
    void skipGameTurn() {
        GameSession gameSession = new GameSession(GAME_SESSION_NAME);
        Player playerOne = new Player("Player 1", new Date(), true, 1, false);
        Player playerTwo = new Player("Player 2", new Date(), true, 2, false);
        Player playerThree = new Player("Player 3", new Date(), true, 3, false);
        gameSession.setPlayers(new ArrayList<>(Arrays.asList(playerOne, playerTwo, playerThree)));

        Turn turn = new Turn(playerOne, playerTwo);
        gameSession.setTurn(turn);

        mockRetrieve(gameSession);

        StepVerifier.create(gameService.skipGameTurn(GAME_SESSION_NAME)).consumeNextWith(actualGameTurn -> {
            assertEquals(playerThree, actualGameTurn.getCurrent());
            assertEquals(playerOne, actualGameTurn.getNext());
        }).verifyComplete();

        Mockito.verify(gameSessionRepository, Mockito.times(1))
                .updateGameSession(Mockito.any(GameSession.class));
    }

    private void mockRetrieve(GameSession gameSession) {
        Mockito.when(this.gameSessionRepository.getGameSession(gameSession.getGameName())).thenReturn(gameSession);
    }
}