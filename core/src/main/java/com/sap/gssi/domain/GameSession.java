package com.sap.gssi.domain;

import java.util.List;

public class GameSession {
    String gameName;
    Boolean started;
    Boolean finished;
    Turn turn;
    List<Player> players;

    public GameSession(String gameName) {
        this.gameName = gameName;
    }

    public GameSession(String gameName, Boolean started, Boolean finished, Turn turn) {
        this.gameName = gameName;
        this.started = started;
        this.finished = finished;
        this.turn = turn;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "GameSession{" +
                "gameName='" + gameName + '\'' +
                ", started=" + started +
                ", finished=" + finished +
                ", turn=" + turn +
                ", players=" + players +
                '}';
    }
}
