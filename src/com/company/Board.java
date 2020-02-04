package com.company;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable {

    private Character[][] plansza = new Character[3][3];

    public Board() {

    }

    public Character getField(int x, int y) {
        return plansza[x][y];
    }

    public void setField(int x, int y, Character sym) {
        plansza[x][y] = sym;
    }

    public boolean check(Character val) {
        for (int i = 0; i < 3; i++) {
            if (plansza[i][0] == val && plansza[i][1] == val && plansza[i][2] == val) {
                return true;
            }

            if (plansza[0][i] == val && plansza[1][i] == val && plansza[2][i] == val) {
                return true;
            }
        }

        if (plansza[0][0] == val && plansza[1][1] == val && plansza[2][2] == val) {
            return true;
        }

        if (plansza[2][0] == val && plansza[1][1] == val && plansza[0][2] == val) {
            return true;
        }

        return false;

    }


    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (plansza[i][j] == null) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "map{" +
                "plansza=" + Arrays.toString(plansza) +
                '}';
    }
}
