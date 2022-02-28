package com.codecool.polishdraughts.model;

import com.codecool.polishdraughts.view.ConsoleView;
import com.codecool.polishdraughts.controller.ConsoleInput;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Game {
    private Pawn pawn;
    private boolean gameIsRunning;
    private Board board;
    private Spot[][] boxes;
    private final ConsoleView consoleView = new ConsoleView();
    private int player;
    private boolean isToKill;

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
            System.out.println("PLAYER: "+ player);
            makeMove(player);
            if(!isToKill) {
                player = player == 1 ? 2 : 1;
            }
        }
    }


    public void makeMove(int player) {
        consoleView.printBoard(board);
        int[] selectedPawn = ConsoleInput.getMove();
        List<Point> points = getPossibleMoves(player, selectedPawn[0], selectedPawn[1]);
        setPossibleMoves(points, boxes);
        consoleView.printBoard(board);

        int[] selectedSpot = ConsoleInput.getMove();
        if (isToKill) {
            findPawnCoordinatesToRemove(selectedPawn, selectedSpot, boxes);
        }

        removePossibleMoves(points, boxes);
        Pawn pawn = boxes[selectedPawn[0]][selectedPawn[1]].getPawn();
        boxes[selectedPawn[0]][selectedPawn[1]].setPawn(null);
        boxes[selectedSpot[0]][selectedSpot[1]].setPawn(pawn);

        consoleView.printBoard(board);


    }

    public List<Point> getPossibleMoves(int player, int selectedX, int selectedY) {
        List<Point> points = new ArrayList<>();
        isToKill = false;
        switch (player) {
            case 1 -> {
                if (!checkIfEmpty(selectedX - 1, selectedY - 1)) {
                    System.out.println("IF 1 WaruneK1-case-1");
                    if (!isWhite(selectedX - 1, selectedY - 1)) {
                        System.out.println("IF 1 WaruneK2-case-1-przeszło isWhite");
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

            case 2 -> {
                if (!checkIfEmpty(selectedX + 1, selectedY + 1)) {
                    System.out.println("IF 1 Warunek 1 case 2");
                    if (isWhite(selectedX + 1, selectedY + 1)) {
                        System.out.println("IF 1Warunek 1-case-2-przeszło isWhite");
                        if (checkIfEmpty(selectedX + 2, selectedY + 2)) {
                            System.out.println("IF 1 Warunek 1-case-2-przeszło isWhite i checkIfEmpty");
                            isToKill = true;
                            points.add(new Point(selectedX + 2, selectedY + 2));
                        }
                    }
                }
                if (!checkIfEmpty(selectedX + 1, selectedY - 1)) {
                    System.out.println("IF 2 Warunek 1 case 2");
                    if (isWhite(selectedX + 1, selectedY - 1)) {
                        System.out.println("IF 2 WaruneK1-case-2-przeszło isWhite");
                        if (checkIfEmpty(selectedX + 2, selectedY - 2)) {
                            isToKill = true;
                            points.add(new Point(selectedX + 2, selectedY - 2));
                        }
                    }
                }
                if (!checkIfEmpty(selectedX - 1, selectedY - 1)) {
                    System.out.println("IF 3 Warunek 1 case 2");
                    if (isWhite(selectedX - 1, selectedY - 1)) {
                        System.out.println("IF 3 WaruneK1-case-2-przeszło isWhite");
                        if (checkIfEmpty(selectedX - 2, selectedY - 2)) {
                            isToKill = true;
                            points.add(new Point(selectedX - 2, selectedY - 2));
                        }
                    }
                }
                if (!checkIfEmpty(selectedX - 1, selectedY + 1)) {
                    System.out.println("IF 4 Warunek 1 case 2");
                    if (isWhite(selectedX - 1, selectedY + 1)) {
                        System.out.println("IF 4 WaruneK1-case-2-przeszło isWhite");
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
            return boxes[x][y].getPawn() == null;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isWhite(int x, int y) {
        try {
            if (boxes[x][y].getPawn().isWhite()) {
                return true;
            } else {
                return false;

            }


        } catch (
                Exception e) {
            return false;
        }

    }

    private void findPawnCoordinatesToRemove(int[] selectedPawn, int[] selectedSpot, Spot[][] boxes) {
        int x = (selectedPawn[0] + selectedSpot[0]) / 2;
        int y = (selectedPawn[1] + selectedSpot[1]) / 2;

        boxes[x][y].getPawn().setKilled(true);
        boxes[x][y].setPawn(null);

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
