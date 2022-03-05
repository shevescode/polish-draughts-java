package com.codecool.polishdraughts.controller;

import com.codecool.polishdraughts.model.Board;
import com.codecool.polishdraughts.model.Coordinates;

import java.util.Scanner;

import static java.lang.Math.abs;

public class ConsoleInput {
    private final Scanner scanner;

    public ConsoleInput() {
        this.scanner = new Scanner(System.in);
    }


    public Scanner getScanner() {
        return scanner;
    }

    public static Coordinates getMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        String chosenCoordinates = scanner.next();

        if (validUserInput(chosenCoordinates, board)) {
            int col = Character.getNumericValue(chosenCoordinates.charAt(0)) - 10;
            int rowFirstDigit = Character.getNumericValue(chosenCoordinates.charAt(1));

            switch (chosenCoordinates.length()) {
                case 2 -> {
                    return new Coordinates(abs(rowFirstDigit - board.getBoardSize()), col);
                }
                case 3 -> {
                    int rowSecondDigit = Character.getNumericValue(chosenCoordinates.charAt(2));
                    String addedDigits = String.valueOf(rowFirstDigit) + String.valueOf(rowSecondDigit);
                    int row = Integer.parseInt(addedDigits);
                    return new Coordinates(abs(row - board.getBoardSize()), col);
                }   
            }
        }
        return new Coordinates(0, 0);
    }

    private static boolean validUserInput(String chosenCoordinates, Board board) {
        char isCharValid = chosenCoordinates.charAt(0);
        char isFirstDigitValid = chosenCoordinates.charAt(1);

        switch (chosenCoordinates.length()) {
            case 2 -> {
                if (Character.toString(isCharValid).matches("^[a-uA-U]*$")) {
                    return Character.toString(isFirstDigitValid).matches("^[1-9]*$");
                }
                return false;
            }
            case 3 -> {
                char isSecondDigitValid = chosenCoordinates.charAt(2);
                String addedDigits = String.valueOf(isFirstDigitValid) + String.valueOf(isSecondDigitValid);
                int row = Integer.parseInt(addedDigits);
                if (Character.toString(isCharValid).matches("^[a-uA-U]*$")) {
                    return row > 0 && row <= board.getBoardSize();

                }
                return false;
            }
            default -> {
                System.out.println("Something wrong, try again...");
                return false;
            }
        }
    }
}
