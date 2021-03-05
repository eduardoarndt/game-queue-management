package com.sap.gssi.domain;

import java.util.Date;

public class Player extends Person {
    Integer position;
    Boolean isHost;

    public Player(String name, Date birthDate, Boolean isAssociate, Integer position, Boolean isHost) {
        super(name, birthDate, isAssociate);
        this.position = position;
        this.isHost = isHost;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getHost() {
        return isHost;
    }

    public void setHost(Boolean host) {
        isHost = host;
    }

    @Override
    public String toString() {
        return "Player{" +
                "position=" + position +
                ", isHost=" + isHost +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", isAssociate=" + isAssociate +
                '}';
    }
}
