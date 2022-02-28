package com.codecool.polishdraughts.model;

import com.codecool.polishdraughts.view.ConsoleView;
import com.codecool.polishdraughts.controller.ConsoleInput;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Game {
    public static final int WHITE = 1;
    public static final int BLACK = 2;

    private boolean gameIsRunning;
    private Board board;
    private final ConsoleView consoleView;
    private int player;
    private boolean isToKill;

    public Game() {
        consoleView = new ConsoleView();
    }

    public void beginGame() {
        consoleView.printMenu();
        Scanner scanner = new Scanner(System.in); //TODO move to console input

        switch (scanner.nextInt()) {
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
        board.createBoard();
        player = 1;
        while (gameIsRunning) {
            System.out.println("PLAYER: " + player);
            makeMove(player);
            if (!isToKill) {
                player = player == 1 ? 2 : 1;
            }
        }
    }


    public void makeMove(int player) {
        consoleView.printBoard(board);
        Coordinates selectedPawn = ConsoleInput.getMove();
        List<Point> points = getPossibleMoves(player, selectedPawn);
        setPossibleMoves(points, board.getBoxes());
        consoleView.printBoard(board);

        Coordinates selectedSpot = ConsoleInput.getMove();
        if (isToKill) {
            findPawnCoordinatesToRemove(selectedPawn, selectedSpot);
        }

        removePossibleMoves(points, board.getBoxes());
        board.movePawn(selectedPawn, selectedSpot);


        consoleView.printBoard(board);


    }

    public List<Point> getPossibleMoves(int player, Coordinates coordinates) {
        List<Point> points = new ArrayList<>();
        int selectedX = coordinates.getX();
        int selectedY = coordinates.getY();
        isToKill = false;
        switch (player) {
            case WHITE -> {
                if (!checkIfEmpty(selectedX - 1, selectedY - 1)) {
                    if (!isWhite(selectedX - 1, selectedY - 1)) {
                        if (checkIfEmpty(selectedX - 2, selectedY - 2)) {
                            isToKill = true;
                            points.add(new Point(selectedX - 2, selectedY - 2));
                        }
                    }
                }
                if (!checkIfEmpty(selectedX - 1, selectedY + 1)) {
                    if (!isWhite(selectedX - 1, selectedY + 1)) {
                        if (checkIfEmpty(selectedX - 2, selectedY + 2)) {
                            isToKill = true;
                            points.add(new Point(selectedX - 2, selectedY + 2));
                        }
                    }
                }
                if (!checkIfEmpty(selectedX + 1, selectedY + 1)) {
                    if (!isWhite(selectedX + 1, selectedY + 1)) {
                        if (checkIfEmpty(selectedX + 2, selectedY + 2)) {
                            isToKill = true;
                            points.add(new Point(selectedX + 2, selectedY + 2));
                        }
                    }
                }
                if (!checkIfEmpty(selectedX + 1, selectedY - 1)) {
                    if (!isWhite(selectedX + 1, selectedY - 1)) {
                        if (checkIfEmpty(selectedX + 2, selectedY - 2)) {
                            isToKill = true;
                            points.add(new Point(selectedX + 2, selectedY - 2));
                        }
                    }
                }

                if (!isToKill) {
                    if (checkIfEmpty(selectedX - 1, selectedY - 1)) {
                        points.add(new Point(selectedX - 1, selectedY - 1));
                    }

                    if (checkIfEmpty(selectedX - 1, selectedY + 1)) {
                        points.add(new Point(selectedX - 1, selectedY + 1));
                    }

                }


            }

            case BLACK -> {
                if (!checkIfEmpty(selectedX + 1, selectedY + 1)) {
                    if (isWhite(selectedX + 1, selectedY + 1)) {
                        if (checkIfEmpty(selectedX + 2, selectedY + 2)) {
                            isToKill = true;
                            points.add(new Point(selectedX + 2, selectedY + 2));
                        }
                    }
                }
                if (!checkIfEmpty(selectedX + 1, selectedY - 1)) {
                    if (isWhite(selectedX + 1, selectedY - 1)) {
                        if (checkIfEmpty(selectedX + 2, selectedY - 2)) {
                            isToKill = true;
                            points.add(new Point(selectedX + 2, selectedY - 2));
                        }
                    }
                }
                if (!checkIfEmpty(selectedX - 1, selectedY - 1)) {
                    if (isWhite(selectedX - 1, selectedY - 1)) {
                        if (checkIfEmpty(selectedX - 2, selectedY - 2)) {
                            isToKill = true;
                            points.add(new Point(selectedX - 2, selectedY - 2));
                        }
                    }
                }
                if (!checkIfEmpty(selectedX - 1, selectedY + 1)) {
                    if (isWhite(selectedX - 1, selectedY + 1)) {
                        if (checkIfEmpty(selectedX - 2, selectedY + 2)) {
                            isToKill = true;
                            points.add(new Point(selectedX - 2, selectedY + 2));
                        }
                    }
                }

                if (!isToKill) {
                    if (checkIfEmpty(selectedX + 1, selectedY + 1)) {
                        points.add(new Point(selectedX + 1, selectedY + 1));
                    }


                    if (checkIfEmpty(selectedX + 1, selectedY - 1)) {
                        points.add(new Point(selectedX + 1, selectedY - 1));
                    }

                }


            }
        }


        return points;
    }

    private boolean checkIfEmpty(int x, int y) {
        try {
            return board.getBoxes()[x][y].getPawn() == null;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isWhite(int x, int y) {
        try {
            if (board.getBoxes()[x][y].getPawn().isWhite()) {
                return true;
            } else {
                return false;

            }

        } catch (
                Exception e) {
            return false;
        }

    }

    private void findPawnCoordinatesToRemove(Coordinates selectedPawn, Coordinates selectedSpot) {
        int x = (selectedPawn.getX() + selectedSpot.getX()) / 2;
        int y = (selectedPawn.getY() + selectedSpot.getY()) / 2;

        board.getBoxes()[x][y].getPawn().setKilled(true);
        board.getBoxes()[x][y].setPawn(null);

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

}
