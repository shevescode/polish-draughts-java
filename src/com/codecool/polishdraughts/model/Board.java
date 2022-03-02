package com.codecool.polishdraughts.model;

import java.util.Arrays;

public class Board {
    private final Spot[][] boxes;
    public int boardSize = 10;


    public Board() {
        this.boxes = new Spot[boardSize][boardSize];
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return boardSize;
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
        int pawns = boardSize * 2;
        int counter = 0;

        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                if (counter < 4 * getBoardSize() || counter >= getBoardSize() * getBoardSize() - 4 * getBoardSize()) {
                    if ((j % 2 == 0 && i % 2 == 0) || (j % 2 == 1 && i % 2 == 1)) {
                        boxes[i][j] = new Spot(i, j, new Pawn(isWhite(counter)), true);
                        pawns -= 1;
                    } else {
                        boxes[i][j] = new Spot(i, j, false);
                    }
                } else {
                    boxes[i][j] = new Spot(i, j, false);
                }
                counter += 1;
            }

        }
    }


    @Override
    public String toString() {
        return "Board{" + "boxes=" + Arrays.toString(boxes) + '}';
    }

    public boolean isWhite(int counter) {
        if (counter < 4 * getBoardSize()) {
            return true;
        } else {
            return false;
        }

    }
}


