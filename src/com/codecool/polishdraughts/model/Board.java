package com.codecool.polishdraughts.model;

public class Board {
    private Field[][] board;

    public Board(int size) {
        board = new Field[size][size];
    }
}
