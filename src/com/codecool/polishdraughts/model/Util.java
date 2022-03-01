package com.codecool.polishdraughts.model;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static final int WHITE = 1;
    public static final int BLACK = 2;
    public static final String NORTH_WEST = "north west";
    public static final String NORTH_EAST = "north east";
    public static final String SOUTH_WEST = "south west";
    public static final String SOUTH_EAST = "south east";

    private boolean isToKill;
    private Board board;


    public boolean isToKill() {
        return isToKill;
    }

    public void setToKill(boolean toKill) {
        isToKill = toKill;
    }

    public void setBoard(Board board) {
        this.board = board;
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
            return board.getBoxes()[x][y].getPawn().isWhite();

        } catch (
                Exception e) {
            return false;
        }

    }


    public void findPawnCoordinatesToRemove(Coordinates selectedPawn, Coordinates selectedSpot) {
        int x = (selectedPawn.getX() + selectedSpot.getX()) / 2;
        int y = (selectedPawn.getY() + selectedSpot.getY()) / 2;

        board.getBoxes()[x][y].getPawn().setKilled(true);
        board.getBoxes()[x][y].setPawn(null);

    }


    public void setPossibleMoves(List<Coordinates> possibleMoves, Spot[][] boxes) {
        for (Coordinates possibleMove : possibleMoves) {
            if (possibleMove != null) {
                boxes[possibleMove.getX()][possibleMove.getY()].setPossible(true);
            }

        }

    }

    public void removePossibleMoves(List<Coordinates> possibleMoves, Spot[][] boxes) {
        for (Coordinates possibleMove : possibleMoves) {
            if (possibleMove != null) {
                boxes[possibleMove.getX()][possibleMove.getY()].setPossible(false);
            }
        }
    }

    public ArrayList<Coordinates> checkIfHitPossible(int player) {
        ArrayList<Coordinates> coordinatesArrayList = new ArrayList<>();
        isToKill = false;
        for (int i = 0; i < board.getBoxes().length; i++) {
            for (int j = 0; j < board.getBoxes()[i].length; j++) {
                if (player == 1) {
                    try {
                        if (board.getBoxes()[i][j].getPawn().getLook().equals("W ")) {
                            coordinatesArrayList.add(checkDirection(NORTH_EAST, player, i, j));
                            coordinatesArrayList.add(checkDirection(NORTH_WEST, player, i, j));
                            coordinatesArrayList.add(checkDirection(SOUTH_EAST, player, i, j));
                            coordinatesArrayList.add(checkDirection(SOUTH_WEST, player, i, j));
                        }
                    } catch (Exception e) {
                        continue;
                    }

                }
                if (player == 2) {
                    try {
                        if (board.getBoxes()[i][j].getPawn().getLook().equals("B ")) {
                            coordinatesArrayList.add(checkDirection(NORTH_EAST, player, i, j));
                            coordinatesArrayList.add(checkDirection(NORTH_WEST, player, i, j));
                            coordinatesArrayList.add(checkDirection(SOUTH_EAST, player, i, j));
                            coordinatesArrayList.add(checkDirection(SOUTH_WEST, player, i, j));
                        }
                    } catch (Exception ignored) {
                    }

                }
            }
        }
        return coordinatesArrayList;
    }


    public Coordinates checkDirection(String direction, int player, int boardX, int boardY) {
        int directionalX;
        int directionalY;
        switch (direction) {
            case NORTH_WEST -> {
                directionalX = -1;
                directionalY = -1;
            }
            case NORTH_EAST -> {
                directionalX = -1;
                directionalY = 1;
            }
            case SOUTH_WEST -> {
                directionalX = 1;
                directionalY = -1;
            }
            case SOUTH_EAST -> {
                directionalX = 1;
                directionalY = 1;
            }
            default -> {
                directionalX = 0;
                directionalY = 0;
            }
        }
        switch (player) {
            case WHITE -> {
                if (!checkIfEmpty(boardX + directionalX, boardY + directionalY)) {
                    if (!isWhite(boardX + directionalX, boardY + directionalY)) {
                        if (checkIfEmpty(boardX + (directionalX * 2), boardY + (directionalY * 2))) {
                            isToKill = true;
                            return new Coordinates(boardX + (directionalX * 2), boardY + (directionalY * 2));


                        }
                    }
                }
            }
            case BLACK -> {
                if (!checkIfEmpty(boardX + directionalX, boardY + directionalY)) {
                    if (isWhite(boardX + directionalX, boardY + directionalY)) {
                        if (checkIfEmpty(boardX + (directionalX * 2), boardY + (directionalY * 2))) {
                            isToKill = true;
                            return new Coordinates(boardX + (directionalX * 2), boardY + (directionalY * 2));


                        }
                    }
                }
            }

        }
        return null;

    }

    public ArrayList<Coordinates> getPossibleMoveForSelectedPawn(int player, int selectedX, int selectedY) {
        ArrayList<Coordinates> coordinatesArrayList = new ArrayList<>();
        switch (player) {
            case WHITE -> {
                if (checkIfEmpty(selectedX - 1, selectedY - 1)) {
                    coordinatesArrayList.add(new Coordinates(selectedX - 1, selectedY - 1));
                }

                if (checkIfEmpty(selectedX - 1, selectedY + 1)) {
                    coordinatesArrayList.add(new Coordinates(selectedX - 1, selectedY + 1));
                }
            }
            case BLACK -> {
                if (checkIfEmpty(selectedX + 1, selectedY + 1)) {
                    coordinatesArrayList.add(new Coordinates(selectedX + 1, selectedY + 1));
                }


                if (checkIfEmpty(selectedX + 1, selectedY - 1)) {
                    coordinatesArrayList.add(new Coordinates(selectedX + 1, selectedY - 1));
                }
            }

        }
        return coordinatesArrayList;
    }


}