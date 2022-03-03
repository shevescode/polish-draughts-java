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
        this.board = new Board();
    }

    public void beginGame() {
        consoleView.printMenu();

        switch (consoleInput.getScanner().nextInt()) {
            case 1 -> start();
//            case 2 -> System.out.println("Options");
            case 2 -> selectSize();
            case 3 -> System.out.println("Credits");
            case 4 -> {
                System.out.println("Exit");
                exit(0);
            }
            default -> beginGame();
        }
    }

    public void selectSize() {
        System.out.println("Type in an integer between 10 & 20 to specify board size: ");

        int boardSize = consoleInput.getScanner().nextInt();
        this.board.setBoardSize(boardSize);
//        this.board = new Board(boardSize);

        start();

    }


    public void start() {
        gameIsRunning = true;
        board.createBoxes();
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

        move(coordinatesArrayList, validMoves, player);
        consoleView.printBoard(board);
    }

    public void move(ArrayList<Coordinates> coordinatesArrayList, ArrayList<Coordinates> validMoves, int player) {
        if (!util.isToKill()) {
            coordinatesArrayList = util.getPossibleMoveForSelectedPawn(player, false, -1, -1);
        }

        util.setPossibleMoves(coordinatesArrayList, board.getBoxes());
        consoleView.printBoard(board);
        do {
            consoleView.printMessage("Choose Pawn to move! (for example A1):");
            selectedPawn = ConsoleInput.getMove();
        }
        while (!util.checkIfMoveIsValid(selectedPawn, validMoves));
        util.removePossibleMoves(coordinatesArrayList, board.getBoxes());
        if (!util.isToKill()) {
            coordinatesArrayList = util.getPossibleMoveForSelectedPawn(player, true, selectedPawn.getX(), selectedPawn.getY());
        } else {
            coordinatesArrayList = util.checkIfHitPossible(player, true, selectedPawn.getX(), selectedPawn.getY());
        }

        util.setPossibleMoves(coordinatesArrayList, board.getBoxes());
        consoleView.printBoard(board);
        do {
            consoleView.printMessage("Choose Spot! (for example A1):");
            selectedSpot = ConsoleInput.getMove();
        }
        while (!board.getBoxes()[selectedSpot.getX()][selectedSpot.getY()].isActive());
        if (util.isToKill()) {
            util.findPawnCoordinatesToRemove(selectedPawn, selectedSpot);
        }
        util.removePossibleMoves(coordinatesArrayList, board.getBoxes());
        board.movePawn(selectedPawn, selectedSpot);
    }
}
