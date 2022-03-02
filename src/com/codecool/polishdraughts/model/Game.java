package com.codecool.polishdraughts.model;

import com.codecool.polishdraughts.controller.ConsoleInput;
import com.codecool.polishdraughts.view.ConsoleView;

import java.util.ArrayList;

import static java.lang.System.exit;

public class Game {
    private boolean gameIsRunning;
    private Board board;
    private final ConsoleView consoleView;
    private final ConsoleInput consoleInput;
    private final Util util;
    private Coordinates selectedPawn;
    private Coordinates selectedSpot;


    public Game() {
        consoleView = new ConsoleView();
        consoleInput = new ConsoleInput();
        util = new Util();
    }

    public void beginGame() {
        consoleView.printMenu();

        switch (consoleInput.getScanner().nextInt()) {
            case 1 -> start();
            case 2 -> System.out.println("Options");
            case 3 -> System.out.println("Credits");
            case 4 -> {
                System.out.println("Exit");
                exit(0);
            }
            default -> beginGame();
        }
    }


    public void start() {
        gameIsRunning = true;
        this.board = new Board();
        board.createBoard();
        util.setBoard(board);
        int player = 1;
        while (gameIsRunning) {
            consoleView.printMessage("PLAYER: " + player);
            playRound(player);
            if (!util.isToKill()) {
                player = player == 1 ? 2 : 1;
            }
        }
    }


    public void playRound(int player) {
        consoleView.printMessage("PLAYER " + player + " your turn!");
        util.setToKill(false);
        ArrayList<Coordinates> coordinatesArrayList;
        ArrayList<Coordinates> validMoves;

        coordinatesArrayList = util.checkIfHitPossible(player, false, -1, -1);
        validMoves = util.getValidCoordinates();

        if (!util.isToKill()) {
            moveWhenKillImpossible(validMoves, player);
        }

        if (util.isToKill()) {
            moveWhenKillPossible(coordinatesArrayList, validMoves, player);
        }
        consoleView.printBoard(board);
    }

    public void moveWhenKillPossible(ArrayList<Coordinates> coordinatesArrayList, ArrayList<Coordinates> validMoves, int player) {
        util.setPossibleMoves(coordinatesArrayList, board.getBoxes());
        consoleView.printBoard(board);
        do {
            consoleView.printMessage("Choose Pawn to move! (for example A1):");
            selectedPawn = ConsoleInput.getMove();
        }
        while (!util.checkIfMoveIsValid(selectedPawn, validMoves));
        util.removePossibleMoves(coordinatesArrayList, board.getBoxes());
        coordinatesArrayList = util.checkIfHitPossible(player, true, selectedPawn.getX(), selectedPawn.getY());
        util.setPossibleMoves(coordinatesArrayList, board.getBoxes());
        consoleView.printBoard(board);
        do {
            consoleView.printMessage("Choose Spot! (for example A1):");
            selectedSpot = ConsoleInput.getMove();
        }
        while (!board.getBoxes()[selectedSpot.getX()][selectedSpot.getY()].isActive());

        util.findPawnCoordinatesToRemove(selectedPawn, selectedSpot);
        util.removePossibleMoves(coordinatesArrayList, board.getBoxes());
        board.movePawn(selectedPawn, selectedSpot);
    }

    public void moveWhenKillImpossible(ArrayList<Coordinates> validMoves, int player) {
        ArrayList<Coordinates> coordinatesArrayList = util.getPossibleMoveForSelectedPawn(player, false, -1, -1);
        util.setPossibleMoves(coordinatesArrayList, board.getBoxes());
        consoleView.printBoard(board);
        do {
            consoleView.printMessage("Choose Pawn to move! (for example A1):");
            selectedPawn = ConsoleInput.getMove();
        }
        while (!util.checkIfMoveIsValid(selectedPawn, validMoves));
        util.removePossibleMoves(coordinatesArrayList, board.getBoxes());
        coordinatesArrayList = util.getPossibleMoveForSelectedPawn(player, true, selectedPawn.getX(), selectedPawn.getY());
        util.setPossibleMoves(coordinatesArrayList, board.getBoxes());
        consoleView.printBoard(board);
        do {
            consoleView.printMessage("Choose Spot! (for example A1):");
            selectedSpot = ConsoleInput.getMove();
        }
        while (!board.getBoxes()[selectedSpot.getX()][selectedSpot.getY()].isActive());
        util.removePossibleMoves(coordinatesArrayList, board.getBoxes());
        board.movePawn(selectedPawn, selectedSpot);
    }
}
