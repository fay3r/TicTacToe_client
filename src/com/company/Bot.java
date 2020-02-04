package com.company;

import java.io.Serializable;

public class Bot implements Serializable {

    private Character mark;

    public Bot(Character mark) {
        this.mark = mark;
    }

    public Character getMark() {
        return mark;
    }

    public void setMark(Character mark) {
        this.mark = mark;
    }
}
