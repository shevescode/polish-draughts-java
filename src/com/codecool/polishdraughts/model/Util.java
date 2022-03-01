package com.codecool.polishdraughts.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static final int WHITE = 1;
    public static final int BLACK = 2;

    private boolean isToKill;
    private Board board;

    public boolean isToKill() {
        return isToKill;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public java.util.List<Point> getPossibleMoves(int player, Coordinates coordinates) {
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

    public void findPawnCoordinatesToRemove(Coordinates selectedPawn, Coordinates selectedSpot) {
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