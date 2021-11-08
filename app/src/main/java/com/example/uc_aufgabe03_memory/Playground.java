package com.example.uc_aufgabe03_memory;

import java.util.ArrayList;
import java.util.Collections;
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

        if(!initialized)
            init();
    }

    public void init(){
        Random r = new Random();
        Card randomCard = null, randomCard2 = null;
        int randomIndex = 0, picNum = 0, trys = 0;

        ArrayList<Integer> chosenPics = new ArrayList<Integer>();

        for(int i = 110; i <= 141; i++){
            chosenPics.add(i);
        }


        cards = new Card[x][y];

        for (int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                cards[i][j] = new Card();
            }
        }

        for (int i = 0; i < (x*y); i++){
            randomIndex = r.nextInt(chosenPics.size() - 0)+ 0;


            while(true){
                randomCard = getRandomCard(r);
                randomCard2 = getRandomCard(r);

                if(((randomCard.value == -1) && (randomCard2.value == -1)) && (randomCard != randomCard2)){
                    randomCard.value = chosenPics.get(randomIndex);
                    randomCard2.value = chosenPics.get(randomIndex);
                    chosenPics.remove(randomIndex);
                    break;
                }else{
                    trys++;
                }


                if(trys >= 50){
                    break;
                }
            }

        }

        if(trys >= 50){
            int partnerlessCards = 1;

            while(partnerlessCards > 0){
                chosenPics = leftOverPartners(r, chosenPics);
                partnerlessCards = chosenPics.get(chosenPics.size()-1);
                chosenPics.remove(chosenPics.size()-1);
            }

        }


        for (int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                System.out.println("Reihe: " + (i+1) + " Spalte: " + (j+1) + " " + cards[i][j].value);
            }
        }

        initialized = true;
    }



    private Card play(Position pos){
        return cards[pos.x][pos.y];
    }

    public boolean finished(){
        for(int i = 0; i < cards.length; i++){
            for(int j = 0; j < cards[i].length; j++){
                if(!cards[i][j].visible)
                    return false;
            }
        }

        return true;
    }

    public boolean isPair(Position pos1, Position pos2){
        return (cards[pos1.x][pos1.y].value == cards[pos2.x][pos2.y].value);
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

    private ArrayList<Integer> leftOverPartners(Random r, ArrayList<Integer> chosenPics){
        int randomIndex;
        Card randomCard;
        Card randomCard2;

        int randomPartner, randomPartner2;
        ArrayList<Card> partnerLessCards = new ArrayList<Card>();
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(cards[i][j].value == -1){
                    partnerLessCards.add(cards[i][j]);
                }
            }
        }

        System.out.printf("Partnerless cards: %d\n", partnerLessCards.size());

        for (int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                System.out.println("Reihe: " + (i+1) + " Spalte: " + (j+1) + " " + cards[i][j].value);
            }
        }


        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");


        for(int i = 0; i <= (partnerLessCards.size()/2)+1; i++){
            randomIndex = r.nextInt(chosenPics.size() - 0)+ 0;

            if(partnerLessCards.size() == 0)
                break;

            while(true){
                randomPartner = r.nextInt(partnerLessCards.size()-0)+0;
                randomPartner2 = r.nextInt(partnerLessCards.size()-0)+0;

                if((randomPartner != randomPartner2)){
                    randomCard = partnerLessCards.get(randomPartner);
                    randomCard2 = partnerLessCards.get(randomPartner2);

                    partnerLessCards.remove(partnerLessCards.indexOf(randomCard));
                    partnerLessCards.remove(partnerLessCards.indexOf(randomCard2));
                    break;
                }
            }

            randomCard.value = chosenPics.get(randomIndex);
            randomCard2.value = chosenPics.get(randomIndex);


            System.out.println(chosenPics.get(randomIndex));
            chosenPics.remove(randomIndex);

        }
        System.out.printf("Left overs: %d\n",partnerLessCards.size());

        chosenPics.add(partnerLessCards.size());
        return chosenPics;
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
                System.out.printf("  %d  |", cards[i][j].value);
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
