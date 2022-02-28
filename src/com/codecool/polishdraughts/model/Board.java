package com.codecool.polishdraughts.model;

import java.util.Arrays;

public class Board {
    private final Spot[][] boxes;

    public Board() {
        this.boxes = new Spot[8][8];
    }

    public void movePawn(Coordinates selectedPawn, Coordinates selectedSpot) {
        Pawn pawn = boxes[selectedPawn.getX()][selectedPawn.getY()].getPawn();
        boxes[selectedPawn.getX()][selectedPawn.getY()].setPawn(null);
        boxes[selectedSpot.getX()][selectedSpot.getY()].setPawn(pawn);
    }

    public Spot[][] getBoxes() {

        return boxes;
    }
//    0, 2, 6 -> zaczynasz od null na zmiane
//    1, 5, 7 -> zaczynasz od pawn na zmiane
//    3 , 4 -> wszedzie null

    public void createBoard() {
        for (int i = 0; i <= 7; i++) {
            if (i == 0 || i == 2) {
                createBoxes(i, false, 0);
            } else if (i == 6) {
                createBoxes(i, true, 0);
            } else if (i == 5 || i == 7) {
                createBoxes(i, true, 1);
            } else if (i == 1) {
                createBoxes(i, false, 1);
            } else {
                for (int j = 0; j < 8; j++) {
                    if ((i % 2 == 1 && j % 2 == 0) || (i % 2 == 0 && j % 2 == 1)) {
                        boxes[i][j] = new Spot(i, j, true);
                    } else {
                        boxes[i][j] = new Spot(i, j, false);
                    }

                }
            }
        }

    }

    private void createBoxes(int i, boolean white, int oodOrEven) {
        for (int j = 0; j < 8; j++) {
            if (j % 2 == oodOrEven) {
                boxes[i][j] = new Spot(i, j, false);
            } else {
                boxes[i][j] = new Spot(i, j, new Pawn(white), true);
            }
        }
    }

    @Override
    public String toString() {
        return "Board{" + "boxes=" + Arrays.toString(boxes) + '}';
    }
}

