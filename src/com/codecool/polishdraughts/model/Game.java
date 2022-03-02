package com.codecool.polishdraughts.model;

import com.codecool.polishdraughts.controller.ConsoleInput;
import com.codecool.polishdraughts.view.ConsoleView;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Game {
    private boolean gameIsRunning;
    private Board board;
    private final ConsoleView consoleView;
    private final Util util;
    private int player;


    public Game() {
        consoleView = new ConsoleView();
        util = new Util();
    }

    public void beginGame() {
        consoleView.printMenu();
        Scanner scanner = new Scanner(System.in); //TODO move to console input

        switch (scanner.nextInt()) {
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
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int boardSize = Integer.parseInt(input);
        this.board = new Board(boardSize);

        start();

    }


    public void promptEnterKey() {
        System.out.println("Hit \"ENTER\" to Start");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        clearScreen();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void start() {
        gameIsRunning = true;
        board.createBoard();
        util.setBoard(board);
        player = 1;
        while (gameIsRunning) {
            System.out.println("PLAYER: " + player);
            playRound(player);
            if (!util.isToKill()) {
                player = player == 1 ? 2 : 1;
            }
        }
    }


    public void playRound(int player) {
        consoleView.printBoard(board);
        Coordinates selectedPawn = ConsoleInput.getMove();
        List<Point> points = util.getPossibleMoves(player, selectedPawn);
        util.setPossibleMoves(points, board.getBoxes());
        consoleView.printBoard(board);

        Coordinates selectedSpot = ConsoleInput.getMove();
        if (util.isToKill()) {
            util.findPawnCoordinatesToRemove(selectedPawn, selectedSpot);
        }

        util.removePossibleMoves(points, board.getBoxes());
        board.movePawn(selectedPawn, selectedSpot);


        consoleView.printBoard(board);


    }





}
