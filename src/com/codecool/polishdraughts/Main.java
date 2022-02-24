package com.codecool.polishdraughts;

import com.codecool.polishdraughts.model.Board;
import com.codecool.polishdraughts.view.ConsoleView;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	System.out.println("Hello world");
        Board board = new Board();
        ConsoleView.printBoard(board);
    }
}
