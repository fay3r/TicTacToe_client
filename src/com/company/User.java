package com.company;

import java.io.Serializable;

public class User implements Serializable {

    private int fieldX;
    private int fieldY;
    private char mark;

    public User(int fieldX, int fieldY, char mark) {
        this.fieldX = fieldX;
        this.fieldY = fieldY;
        this.mark = mark;
    }

    public int getFieldX() {
        return fieldX;
    }

    public void setFieldX(int fieldX) {
        this.fieldX = fieldX;
    }

    public int getFieldY() {
        return fieldY;
    }

    public void setFieldY(int fieldY) {
        this.fieldY = fieldY;
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "UserData2{" +
                "fieldX=" + fieldX +
                ", fieldY=" + fieldY +
                ", mark=" + mark +
                '}';
    }
}
