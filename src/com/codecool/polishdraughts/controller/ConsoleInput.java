package com.codecool.polishdraughts.controller;

import com.codecool.polishdraughts.model.Board;
import com.codecool.polishdraughts.model.Coordinates;

import java.util.Scanner;

import static java.lang.Math.abs;

public class ConsoleInput {
    private final Scanner scanner;
    private static final Board board = new Board();

    public ConsoleInput(){
        this.scanner = new Scanner(System.in);
    }


    public Scanner getScanner() {
        return scanner;
    }

    public static Coordinates getMove() {
        Scanner scanner = new Scanner(System.in);
        String chosenCoordinates = scanner.next();

        if (validUserInput(chosenCoordinates)) {
            int col = Character.getNumericValue(chosenCoordinates.charAt(0)) - 10;
            int row = Character.getNumericValue(chosenCoordinates.charAt(1));
            System.out.println("row" + abs(row - board.getBoardSize()) + " " + "col" + col);

            return new Coordinates(abs(row - board.getBoardSize()), col);

        }

        return new Coordinates(0, 0);
    }

    private static boolean validUserInput(String chosenCoordinates) {

        switch (chosenCoordinates.length()) {
            case 2:
                char isCharValid = chosenCoordinates.charAt(0);
                char isDigitValid = chosenCoordinates.charAt(1);

                if (Character.toString(isCharValid).matches("^[a-uA-U]*$")) {
                    return Character.toString(isDigitValid).matches("^[1-9]*$");
                }
                return false;
            case 3:

            default:
                System.out.println("Something wrong, try again...");
                return false;

        }


    }

}
