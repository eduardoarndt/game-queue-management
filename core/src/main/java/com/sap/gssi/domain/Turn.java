package com.sap.gssi.domain;

public class Turn {
    Player current;
    Player next;

    public Turn(Player current, Player next) {
        this.current = current;
        this.next = next;
    }

    public Player getCurrent() {
        return current;
    }

    public void setCurrent(Player current) {
        this.current = current;
    }

    public Player getNext() {
        return next;
    }

    public void setNext(Player next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Turn{" +
                "current=" + current +
                ", next=" + next +
                '}';
    }
}
