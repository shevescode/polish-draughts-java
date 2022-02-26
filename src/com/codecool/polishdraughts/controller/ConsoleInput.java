package com.codecool.polishdraughts.controller;

import java.time.temporal.ValueRange;
import java.util.Scanner;

import static java.lang.Math.abs;

public class ConsoleInput {

    public static int[] getMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose coordinates (for example A1): ");
        String chosenCoordinates = scanner.next();

        if (validUserInput(chosenCoordinates)) {
            int col = Character.getNumericValue(chosenCoordinates.charAt(0))-10;
            int row = Character.getNumericValue(chosenCoordinates.charAt(1));
            System.out.println("row" + abs(row-8) + " "+ "col" +col);

            return new int[]{abs(row-8), col};

        }
        return new int[0];
    }

    private static boolean validUserInput(String chosenCoordinates) {

        if (chosenCoordinates.length() == 2) {
            char isCharValid = chosenCoordinates.charAt(0);
            char isDigitValid = chosenCoordinates.charAt(1);

            if (Character.toString(isCharValid).matches("^[a-hA-H]*$")) {
                if (Character.toString(isDigitValid).matches("^[1-8]*$")) {
                    return true;
                }
            } return false;

//
        }
        System.out.println("Wrong pawn coordinates, try again...");
        return false;
    }

}
