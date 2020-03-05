package com.company;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable {

    private Character[][] gameBoard = new Character[3][3];

    public Board() {

    }

    public Character getField(int x, int y) {
        return gameBoard[x][y];
    }

    public void setField(int x, int y, Character sym) {
        gameBoard[x][y] = sym;
    }

    public boolean check(Character val) {
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == val && gameBoard[i][1] == val && gameBoard[i][2] == val) {
                return true;
            }

            if (gameBoard[0][i] == val && gameBoard[1][i] == val && gameBoard[2][i] == val) {
                return true;
            }
        }

        if (gameBoard[0][0] == val && gameBoard[1][1] == val && gameBoard[2][2] == val) {
            return true;
        }

        if (gameBoard[2][0] == val && gameBoard[1][1] == val && gameBoard[0][2] == val) {
            return true;
        }

        return false;

    }


    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == null) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "map{" +
                "plansza=" + Arrays.toString(gameBoard) +
                '}';
    }
}
