package com.codecool.polishdraughts.view;

import java.time.temporal.ValueRange;
import java.util.Scanner;

public class ConsoleInput {

    public static void askForPawn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose pawn to move: ");
        String choosenCoordinates = scanner.next();
        validCoordinates(choosenCoordinates);
    }

    private static boolean validCoordinates(String choosenCoordinates) {
        boolean isCharValid = choosenCoordinates.matches("^[A-H].*$");
        char[] pawnCoordinates = choosenCoordinates.toCharArray();

        boolean isDigitValid = ValueRange.of(49, 57).isValidIntValue((int) pawnCoordinates[1]);

        if (choosenCoordinates.length() == 2 && isCharValid && isDigitValid) {
            System.out.println(true);
            return true;

        }
        System.out.println(false);
        return false;
    }


}
