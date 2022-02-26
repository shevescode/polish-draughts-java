package com.codecool.polishdraughts;

import com.codecool.polishdraughts.model.Board;
import com.codecool.polishdraughts.controller.ConsoleInput;
import com.codecool.polishdraughts.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        ConsoleInput.askForPawnData();
        Board board = new Board();
        ConsoleView.printBoard(board);
    }
}
