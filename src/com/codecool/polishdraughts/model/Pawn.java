package com.codecool.polishdraughts.model;

public class Pawn {

    private boolean killed = false;
    private boolean white = false;
    private String look;

    @Override
    public String toString() {
        return "Pawn{" +
                "killed=" + killed +
                ", white=" + white +
                ", look=" + look +
                '}';
    }

    public Pawn(boolean white) {
        this.setWhite(white);
        this.setLook(white);
    }

    public void setLook(boolean white) {
        if(white) {
            look = "X ";
        } else {
            look = "O ";
        }
    }

    public String getLook() {
        return look;
    }

    public boolean isWhite()
    {
        return this.white;
    }

    public void setWhite(boolean white)
    {
        this.white = white;
    }

    public boolean isKilled()
    {
        return this.killed;
    }

    public void setKilled(boolean killed)
    {
        this.killed = killed;
    }



}
