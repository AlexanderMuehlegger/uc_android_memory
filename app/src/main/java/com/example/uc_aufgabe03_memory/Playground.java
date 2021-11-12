package com.example.uc_aufgabe03_memory;

import java.lang.reflect.Array;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Playground {

    private Card[][] cards;
    private int whosOnTurn;
    private int[] score;
    private boolean initialized = false;



    public int x, y;

    public Playground(){

    }

    public Playground(int x, int y){
        this.x = x;
        this.y = y;

        cards = new Card[x][y];
        if(!initialized)
            init();
    }

    public void init(){
        if(initialized)
            return;

        Random r = new Random();
        List<Card> cardList = new ArrayList<Card>();
        int[] picsAr = MemoryActivity.getPicsArray();
        ArrayList<Integer> usedPics = new ArrayList<Integer>();

        for(int i = 0; i < x*y; i++){
            cardList.add(new Card());
        }

        int randomZahl = 0;

        System.out.println("Card List size: " + cardList.size());
        for(int i = 0; i < cardList.size(); i += 2){
            while(true){
                randomZahl = r.nextInt(picsAr.length-0)+0;
                if(!usedPics.contains(randomZahl)){
                    cardList.get(i).setValue(picsAr[randomZahl]);
                    cardList.get(i+1).setValue(picsAr[randomZahl]);
                    usedPics.add(randomZahl);
                    break;
                }
            }
            randomZahl = i;
        }

        System.out.println("Last index: " + randomZahl);

        Collections.shuffle(cardList);

        short temp = 0;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                cards[i][j] = cardList.get(temp);
                temp++;
            }
        }

            System.out.println(toString());

        initialized = true;
    }



    private Card play(Position pos){
        return cards[pos.x][pos.y];
    }

    public boolean finished(){
        for(int i = 0; i < cards.length; i++){
            for(int j = 0; j < cards[i].length; j++){
                if(!cards[i][j].isVisible())
                    return false;
            }
        }

        return true;
    }

    public boolean isPair(Position pos1, Position pos2){
        return (cards[pos1.x][pos1.y].getValue() == cards[pos2.x][pos2.y].getValue());
    }

    public Card getCard(Position pos){
        return cards[pos.x][pos.y];
    }

    private int getNrPairs(){
        return 0;
    }


    private Card getRandomCard(Random r){
        int randomRow = r.nextInt(x-1) +1;
        int randomColumn = r.nextInt(y-1) + 1;

        return cards[randomRow-1][randomColumn-1];
    }

    public String toString(){
        String casingPart = "-------+";

        for(int i = 0; i < cards.length; i++){
            System.out.print("+");
            for(int j = 0; j < cards[i].length; j++){
                System.out.print(casingPart);
            }
            System.out.print("\n|");



            for(int j = 0; j < cards[i].length; j++){
                System.out.printf("  %d  |", cards[i][j].getValue());
            }

            System.out.print("\n");
        }
        System.out.print("+");
        for(int j = 0; j < cards[0].length; j++){
            System.out.print(casingPart);
        }
        System.out.println();
        return null;
    }
}
