package com.example.uc_aufgabe03_memory;

public class Card {
    public boolean visible = false;
    public int value = -1;

    public Card(){

    }

    public Card(int value, boolean visible){
        this.visible = visible;
        this.value = value;
    }

    public String toString(){
        return visible + " " + value;
    }
}
