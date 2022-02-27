package com.codecool.polishdraughts.view;

import com.codecool.polishdraughts.model.Board;
import com.codecool.polishdraughts.model.Spot;

import java.awt.*;
import java.util.List;

import static java.lang.Math.abs;

public class ConsoleView {

    public ConsoleView() {

    }





    public void printMenu() {
        System.out.println("1. Start Game");
        System.out.println("2. Options");
        System.out.println("3. Credits");
        System.out.println("4. Exit");
    }

    public void printBoard(Board board) {
        printBoardSigns();
        printLine();
        for (int i = 8; i > 0; i--) {
            System.out.print((i) + " | ");

            for (int j = 0; j < 8; j++) {
                System.out.print(board.getBoxes()[abs(i-8)][j]);
            }

            System.out.print("| "+(i));
            System.out.println();
        }
        printLine();
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
    private static void printLine() {
        System.out.print("    ");
        for (int i = 0; i < 8; i++) {
            System.out.print("-" + " ");
        }
        System.out.println();
    }
}

