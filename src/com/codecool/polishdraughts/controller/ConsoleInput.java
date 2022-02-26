package com.codecool.polishdraughts.controller;

import java.time.temporal.ValueRange;
import java.util.Scanner;

public class ConsoleInput {

    public static void askForPawnData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose pawn to move (for example A1): ");
        String chosenCoordinates = scanner.next();
        validUserInput(chosenCoordinates);
    }

    private static boolean validUserInput(String chosenCoordinates) {

        if (chosenCoordinates.length() == 2) {
            boolean isCharValid = chosenCoordinates.matches("^[A-H].*$");
            char[] pawnCoordinates = chosenCoordinates.toCharArray();
            boolean isDigitValid = ValueRange.of(49, 57).isValidIntValue((int) pawnCoordinates[1]);

            if(isCharValid && isDigitValid){
                return true;
            }

        }
        System.out.println("Wrong pawn coordinates, try again...");
        return false;
    }

}
