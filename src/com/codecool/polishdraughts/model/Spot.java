package com.codecool.polishdraughts.model;

public class Spot {
    private Pawn pawn;
    private final Coordinates coordinates;

    public Spot(int x, int y, Pawn pawn) {
        this.setPawn(pawn);

        coordinates = new Coordinates(x, y);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Pawn getPawn() {

        return this.pawn;
    }

    public void setPawn(Pawn pawn) {

        this.pawn = pawn;
    }

//    public int getX(int x) {
//
//        return this.x;
//    }
//
//    public void setX(int x) {
//
//        this.x = x;
//    }
//
//    public int getY(int y) {
//
//        return this.y;
//    }
//
//    public void setY(int y) {
//
//        this.y = y;
//    }

    @Override
    public String toString() {
        if (pawn == null) {
            return "- ";
        } else {
            return pawn.getLook();
        }
    }

//    public boolean isEmpty() {
//        if (pawn == null) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
