package com.codecool.polishdraughts.model;


import com.codecool.polishdraughts.controller.ConsoleInput;
import com.codecool.polishdraughts.model.Spot;

public class Game {
    private Pawn pawn;
    private boolean isRunning;
    private Board board;

    public static void makeMove(Board board){
        Spot[][] boxes = board.getBoxes();
        int[] selectedPawn = ConsoleInput.getMove();
        int[] selectedSpot = ConsoleInput.getMove();
        Pawn pawn = boxes[selectedPawn[0]][selectedPawn[1]].getPawn();
        boxes[selectedPawn[0]][selectedPawn[1]].setPawn(null);
        boxes[selectedSpot[0]][selectedSpot[1]].setPawn(pawn);
    }
}
