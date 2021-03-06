package com.example.uc_aufgabe03_memory;

import java.util.Objects;

public class Position {
    public int x;
    public int y;

    public Position(){}

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

}
