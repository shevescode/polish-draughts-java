package com.codecool.polishdraughts.model;

public class Pawn {

    private boolean killed;
    private boolean white;
    private String look;

    public Pawn(boolean white) {
        this.setWhite(white);
        this.setLook(white);
        this.killed = false;
    }

    public void setLook(boolean white) {
        if (white) {
            look = "W ";
        } else {
            look = "B ";
        }
    }

    public String getLook() {
        return look;
    }

    public boolean isWhite() {
        return this.white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isKilled() {
        return this.killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "killed=" + killed +
                ", white=" + white +
                ", look=" + look +
                '}';
    }

}
