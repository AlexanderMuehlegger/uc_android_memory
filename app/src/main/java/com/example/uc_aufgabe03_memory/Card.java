package com.example.uc_aufgabe03_memory;

public class Card {
    private boolean visible = false;
    private int value = -1;

    public Card(){}

    public Card(int value, boolean visible){
        this.visible = visible;
        this.value = value;
    }

    public String toString(){
        return visible + " " + value;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
