package com.codecool.polishdraughts.model;

public class Spot {
    private boolean isActive;
    private boolean isPossible;
    private Pawn pawn;
    private final Coordinates coordinates;

    public Spot(int x, int y, Pawn pawn, boolean isActive) {
        this.setPawn(pawn);
        coordinates = new Coordinates(x, y);
        this.isActive = isActive;
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

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setPossible(boolean possible) {
        isPossible = possible;
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
        if (!isActive) {
            return "  ";
        } else if (pawn == null && !isPossible) {
            return "- ";
        } else if (pawn == null) {
            return "+ ";
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
