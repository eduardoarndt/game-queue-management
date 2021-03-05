package com.sap.gssi.usecase;

import com.sap.gssi.domain.GameSession;
import com.sap.gssi.domain.Player;

import java.util.List;

public class GameUseCase {

    GameSession gameSession;

    public GameUseCase(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    void addPlayers(List<Player> playerList) {

    }

    void orderPlayers(List<Player> playerList) {

    }

    void keepRecord(List<Player> playerList) {

    }

    void skip(Player player) {

    }

    void playAgain(Player player) {

    }

    void reverseOrder(Player player) {

    }

}
