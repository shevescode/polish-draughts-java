package com.codecool.polishdraughts.model;

import com.codecool.polishdraughts.view.ConsoleView;
import com.codecool.polishdraughts.controller.ConsoleInput;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class Game {
    private Pawn pawn;
    private boolean gameIsRunning;
    private Board board;
    private Spot[][] boxes;
    private final ConsoleView consoleView = new ConsoleView();
    private int player;

    public Game() {
        menu();
    }

    private void menu() {
        consoleView.printMenu();
        Scanner scanner = new Scanner(System.in);

        switch (scanner.nextInt()) {
            case 1 -> start();
            case 2 -> System.out.println("Options");
            case 3 -> System.out.println("Credits");
            case 4 -> {
                System.out.println("Exit");
                exit(0);
            }
            default -> menu();
        }
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
        this.board = new Board();
        setBoxes(board);
        player = 1;
        while (gameIsRunning) {
            System.out.println(player);
            makeMove(player);
            player = player == 1 ? 2 : 1;

        }
    }


    public void makeMove(int player){
            consoleView.printBoard(board);
            int[] selectedPawn = ConsoleInput.getMove();

            setPossibleMoves(getPossibleMoves(player, selectedPawn[0], selectedPawn[1]), boxes);
            consoleView.printBoard(board);

            int[] selectedSpot = ConsoleInput.getMove();
            removePossibleMoves(getPossibleMoves(player, selectedPawn[0], selectedPawn[1]), boxes);
            Pawn pawn = boxes[selectedPawn[0]][selectedPawn[1]].getPawn();
            boxes[selectedPawn[0]][selectedPawn[1]].setPawn(null);
            boxes[selectedSpot[0]][selectedSpot[1]].setPawn(pawn);

            consoleView.printBoard(board);


    }

    public List<Point> getPossibleMoves(int player, int selectedX, int selectedY){
        List<Point> points = new ArrayList<>();
        switch (player) {
            case 1 -> {


                if (checkIfEmpty(selectedX - 1,selectedY - 1)) {
                    points.add(new Point(selectedX - 1, selectedY - 1));
                } else if (!boxes[selectedX - 1][selectedY - 1].getPawn().isWhite()) {
                    if (checkIfEmpty(selectedX - 2, selectedY - 2)) {
                        points.add(new Point(selectedX - 2, selectedY - 2));
                    }
                }

                if (checkIfEmpty(selectedX - 1, selectedY + 1)) {
                    points.add(new Point(selectedX - 1, selectedY + 1));
                } else if (!boxes[selectedX - 1][selectedY + 1].getPawn().isWhite()) {
                    if (checkIfEmpty(selectedX - 2, selectedY + 2)) {
                        points.add(new Point(selectedX - 2, selectedY + 2));
                    }
                }

            }
            case 2 -> {
                if (checkIfEmpty(selectedX + 1,selectedY + 1)) {
                    points.add(new Point(selectedX + 1, selectedY + 1));
                } else if (!boxes[selectedX + 1][selectedY + 1].getPawn().isWhite()) {
                    if (checkIfEmpty(selectedX + 2, selectedY + 2)) {
                        points.add(new Point(selectedX + 2, selectedY + 2));
                    }
                }
                if (checkIfEmpty(selectedX + 1,selectedY - 1)) {
                    points.add(new Point(selectedX + 1, selectedY - 1));
                } else if (!boxes[selectedX + 1][selectedY - 1].getPawn().isWhite()) {
                    if (checkIfEmpty(selectedX + 2, selectedY - 2)) {
                        points.add(new Point(selectedX + 2, selectedY - 2));
                    }
                }
            }
        }


        return points;
    }

    private boolean checkIfEmpty(int x, int y) {
        try {
            return boxes[x][y].getPawn() == null;
        } catch (Exception e) {
            return false;
        }
    }

    public void setPossibleMoves(List<Point> possibleMoves, Spot[][] boxes) {
        for (Point possibleMove : possibleMoves) {
            boxes[(int) possibleMove.getX()][(int) possibleMove.getY()].setPossible(true);
        }

    }

    public void removePossibleMoves(List<Point> possibleMoves, Spot[][] boxes) {
        for (Point possibleMove : possibleMoves) {
            boxes[(int) possibleMove.getX()][(int) possibleMove.getY()].setPossible(false);
        }
    }

    public void setBoxes(Board board) {
        this.board = board;
        this.boxes = board.getBoxes();
    }
}
