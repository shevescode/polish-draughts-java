package com.codecool.polishdraughts.view;

import com.codecool.polishdraughts.model.Board;

import static java.lang.Math.abs;

public class ConsoleView {
    private Board board;

    public ConsoleView() {

    }

    public void printMenu() {
        System.out.println("1. Start Game");
        System.out.println("2. Options");
        System.out.println("3. Credits");
        System.out.println("4. Exit");
    }

    public void printBoard(Board board) {

        printBoardSigns(board);
        printLine(board);

        for (int i = board.getBoardSize(); i > 0; i--) {
            if (i >= 10) {
                System.out.print((i) + " | ");
            } else {
                System.out.print((i) + "  | ");
            }
            for (int j = 0; j < board.getBoardSize(); j++) {
                System.out.print(board.getBoxes()[abs(i - board.getBoardSize())][j]);
            }
            System.out.print("| " + (i));
            System.out.println();
        }
        printLine(board);
        printBoardSigns(board);
    }


    private void printBoardSigns(Board board) {
        System.out.print("     ");
        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        for (int j = 0; j < board.getBoardSize(); j++){
            System.out.print(abc[j]+" ");

        }System.out.println();}

    private void printLine(Board board) {
        System.out.print("     ");
        for (int i = 0; i < board.getBoardSize(); i++) {
            System.out.print("-" + " ");
        }
        System.out.println();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}

