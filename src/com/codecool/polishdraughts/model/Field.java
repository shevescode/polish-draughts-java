package com.codecool.polishdraughts.model;

public class Field {
    private Pawn pawn;
    private int x;

    public Field(){
        Pawn pawn = new Pawn();
    }

    public boolean isEmpty() {
        if (pawn == null) {
            return true;
        } else {
            return false;
        }
    }
}
