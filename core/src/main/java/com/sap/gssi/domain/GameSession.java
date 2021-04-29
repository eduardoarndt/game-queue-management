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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameSession)) return false;

        GameSession that = (GameSession) o;

        if (getGameName() != null ? !getGameName().equals(that.getGameName()) : that.getGameName() != null)
            return false;
        if (getStarted() != null ? !getStarted().equals(that.getStarted()) : that.getStarted() != null) return false;
        if (getFinished() != null ? !getFinished().equals(that.getFinished()) : that.getFinished() != null)
            return false;
        if (getTurn() != null ? !getTurn().equals(that.getTurn()) : that.getTurn() != null) return false;
        return getPlayers() != null ? getPlayers().equals(that.getPlayers()) : that.getPlayers() == null;
    }

    @Override
    public int hashCode() {
        int result = getGameName() != null ? getGameName().hashCode() : 0;
        result = 31 * result + (getStarted() != null ? getStarted().hashCode() : 0);
        result = 31 * result + (getFinished() != null ? getFinished().hashCode() : 0);
        result = 31 * result + (getTurn() != null ? getTurn().hashCode() : 0);
        result = 31 * result + (getPlayers() != null ? getPlayers().hashCode() : 0);
        return result;
    }
}
