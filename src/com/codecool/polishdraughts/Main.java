package com.codecool.polishdraughts;

import com.codecool.polishdraughts.model.Board;

public class Main {

    public static void main(String[] args) {
	System.out.println("Hello world");
        Board board = new Board(10);
        System.out.println(board.toString());
    }
}
