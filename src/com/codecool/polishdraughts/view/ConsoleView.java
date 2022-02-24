package com.codecool.polishdraughts.view;

import com.codecool.polishdraughts.model.Board;

public class ConsoleView {


    public static void printBoard(Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                System.out.print(board.getBoxes()[i][j]);

            }
            System.out.println();
        }


    }
}
