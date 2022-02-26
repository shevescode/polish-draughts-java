package com.codecool.polishdraughts.model;

import java.util.Arrays;

public class Board {
    private final Spot[][] boxes;


    @Override
    public String toString() {
        return "Board{" + "boxes=" + Arrays.toString(boxes) + '}';
    }

    public Board() {
        boxes = new Spot[8][8];
        createBoard();
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
                createBoxes(i, true, 0);
            } else if (i == 6) {
                createBoxes(i, false, 0);
            } else if (i == 5 || i == 7) {
                createBoxes(i, false, 1);
            } else if (i == 1) {
                createBoxes(i, true, 1);
            } else {
                for (int j = 0; j < 8; j++) {
                    boxes[i][j] = new Spot(i, j, null);
                }
            }
        }

    }

    private void createBoxes(int i, boolean white, int oodOrEven) {
        for (int j = 0; j < 8; j++) {
            if (j % 2 == oodOrEven) {
                boxes[i][j] = new Spot(i, j, null);
            } else {
                boxes[i][j] = new Spot(i, j, new Pawn(i, j, white));
            }
        }
    }

}


/*
    public void resetBoard() {
        // initialize white pieces
        boxes[0][0] = new Spot(0, 0, null);
        boxes[0][1] = new Spot(0, 1, new Pawn(true));
        boxes[0][2] = new Spot(0, 2, null);
        boxes[0][3] = new Spot(0, 3, new Pawn(true));
        boxes[0][4] = new Spot(0, 4, null);
        boxes[0][5] = new Spot(0, 5, new Pawn(true));
        boxes[0][6] = new Spot(0, 6, null);
        boxes[0][7] = new Spot(0, 7, new Pawn(true));


        boxes[1][0] = new Spot(1, 0, new Pawn(true));
        boxes[1][1] = new Spot(1, 1, null);
        boxes[1][2] = new Spot(1, 2, new Pawn(true));
        boxes[1][3] = new Spot(1, 3, null);
        boxes[1][4] = new Spot(1, 4, new Pawn(true));
        boxes[1][5] = new Spot(1, 5, null);
        boxes[1][6] = new Spot(1, 6, new Pawn(true));
        boxes[1][7] = new Spot(1, 7, null);


        boxes[2][0] = new Spot(2, 0, null);
        boxes[2][1] = new Spot(2, 1, new Pawn(true));
        boxes[2][2] = new Spot(2, 2, null);
        boxes[2][3] = new Spot(2, 3, new Pawn(true));
        boxes[2][4] = new Spot(2, 4, null);
        boxes[2][5] = new Spot(2, 5, new Pawn(true));
        boxes[2][6] = new Spot(2, 6, null);
        boxes[2][7] = new Spot(2, 7, new Pawn(true));

        //...

        // initialize black pieces
        boxes[7][0] = new Spot(7, 0, new Pawn(false));
        boxes[7][1] = new Spot(7, 1, null);
        boxes[7][2] = new Spot(7, 2, new Pawn(false));
        boxes[7][3] = new Spot(7, 3, null);
        boxes[7][4] = new Spot(7, 4, new Pawn(false));
        boxes[7][5] = new Spot(7, 5, null);
        boxes[7][6] = new Spot(7, 6, new Pawn(false));
        boxes[7][7] = new Spot(7, 7, null);
        //...
        boxes[6][0] = new Spot(6, 0, null);
        boxes[6][1] = new Spot(6, 1, new Pawn(false));
        boxes[6][2] = new Spot(6, 2, null);
        boxes[6][3] = new Spot(6, 3, new Pawn(false));
        boxes[6][4] = new Spot(6, 4, null);
        boxes[6][5] = new Spot(6, 5, new Pawn(false));
        boxes[6][6] = new Spot(6, 6, null);
        boxes[6][7] = new Spot(6, 7, new Pawn(false));
        //...
        boxes[5][0] = new Spot(5, 0, new Pawn(false));
        boxes[5][1] = new Spot(5, 1, null);
        boxes[5][2] = new Spot(5, 2, new Pawn(false));
        boxes[5][3] = new Spot(5, 3, null);
        boxes[5][4] = new Spot(5, 4, new Pawn(false));
        boxes[5][5] = new Spot(5, 5, null);
        boxes[5][6] = new Spot(5, 6, new Pawn(false));
        boxes[5][7] = new Spot(5, 7, null);

        // initialize remaining boxes without any piece
        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Spot(i, j, null);
            }


        }


    }
*/
