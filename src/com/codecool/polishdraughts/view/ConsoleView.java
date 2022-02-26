package com.codecool.polishdraughts.view;

import com.codecool.polishdraughts.model.Board;

public class ConsoleView {


    public static void printBoard(Board board) {
        printBoardSigns();

        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " | ");

            for (int j = 0; j < 8; j++) {
                System.out.print(board.getBoxes()[i][j]);
            }

            System.out.print("| "+(i + 1));
            System.out.println();
        }
        printBoardSigns();
    }


    private static void printBoardSigns() {
        char letter;
        System.out.print("    ");
        for (letter = 'A'; letter < 'I'; letter++) {
            System.out.print(letter + " ");

        }
        System.out.println();

    }

//
//    private static void printLine() {
//        System.out.print("   ");
//        for (int i = 0; i < 8; i++) {
//            System.out.print("_" + " ");
//        }
//        System.out.println();
//    }
}

