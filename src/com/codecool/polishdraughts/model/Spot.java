package com.codecool.polishdraughts.model;

public class Spot {
    private boolean active;
    private boolean possible;
    private Pawn pawn;
    private final Coordinates coordinates;

    public Spot(int x, int y, boolean active) {
        this.coordinates = new Coordinates(x, y);
        this.setPawn(null);
        this.active = active;
    }

    public Spot(int x, int y, Pawn pawn, boolean active) {
        this.coordinates = new Coordinates(x, y);
        this.setPawn(pawn);
        this.active = active;
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
        this.active = active;
    }

    public void setPossible(boolean possible) {
        this.possible = possible;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isPossible() {
        return possible;
    }

    @Override
    public String toString() {
        if (!active) {
            return "  ";
        } else if (pawn == null && !possible) {
            return "- ";
        } else if (pawn == null) {
            return "+ ";
        } else {
            return pawn.getLook();
        }
    }
}
