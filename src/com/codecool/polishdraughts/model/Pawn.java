package com.codecool.polishdraughts.model;

public class Pawn {

    private boolean killed = false;
    private boolean white;
    private String look;
    private Coordinates coordinates;

    @Override
    public String toString() {
        return "Pawn{" +
                "killed=" + killed +
                ", white=" + white +
                ", look=" + look +
                '}';
    }

    public Pawn(int x, int y, boolean white) {
        this.setWhite(white);
        this.setLook(white);
        coordinates = new Coordinates(x, y);
    }

    public void setLook(boolean white) {
        if(white) {
            look = "W ";
        } else {
            look = "B ";
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
    {this.white = white;
    }

    public boolean isKilled()
    {return this.killed;
    }

    public void setKilled(boolean killed)
    {this.killed = killed;
    }



}
