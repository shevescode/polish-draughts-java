package com.codecool.polishdraughts;

import com.codecool.polishdraughts.model.Board;
import com.codecool.polishdraughts.view.ConsoleInput;
import com.codecool.polishdraughts.view.ConsoleView;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ConsoleInput.askForPawn();
        Board board = new Board();
        ConsoleView.printBoard(board);
    }
}
