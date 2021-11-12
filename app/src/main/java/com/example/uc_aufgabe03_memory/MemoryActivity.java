package com.example.uc_aufgabe03_memory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryActivity extends AppCompatActivity {

    private int[] pics;
    private Playground field;
    private Position previousCard;
    private ImageButton[][] buttons;
    private LinearLayout playingField;
    private int pairsFound = 0;
    private boolean blockSelection = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);




        playingField = (LinearLayout) findViewById(R.id.playingField);

        pics = getPicsArray();

        receiveMessage();
    }

    @Override
    public void onBackPressed(){
        Intent homeScreen = new Intent(this, Start_Screen_Activity.class);

        this.finish();
        startActivity(homeScreen);

    }

    private void generateGrid(int nrCol, int nrRows){

        buttons = new ImageButton[nrRows][nrCol];
        ArrayList<LinearLayout> playingRows = new ArrayList<LinearLayout>();

        for(int i = 0; i < nrRows; i++){
            playingRows.add(new LinearLayout(this));
            playingRows.get(playingRows.size()-1).setOrientation(LinearLayout.HORIZONTAL);
            playingRows.get(playingRows.size()-1).setLayoutParams(new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            playingRows.get(playingRows.size()-1).setGravity(Gravity.CENTER);

            playingField.addView(playingRows.get(playingRows.size()-1));
            for(int j = 0; j < nrCol; j++){
                buttons[i][j] = generateButton(new Position());

                playingRows.get(playingRows.size()-1).addView(buttons[i][j]);
            }
        }
    }

    private void generateAndAddRows(int row, int nrCols){

    }

    private ImageButton generateButton(Position pos){
        ImageButton button = new ImageButton(this);
        button.setImageResource(R.drawable.back2);
        button.setLayoutParams(new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setForegroundGravity(Gravity.CENTER);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                onButtonClick(view);
            }
        });

        ViewGroup.LayoutParams params = button.getLayoutParams();

        params.height = 150;
        params.width = 100;


        return button;
    }

    public static int[] getPicsArray(){
        int[] c = new int[32];

        c[0] = R.drawable.i110;
        c[1] = R.drawable.i111;
        c[2] = R.drawable.i112;
        c[3] = R.drawable.i113;
        c[4] = R.drawable.i114;
        c[5] = R.drawable.i115;
        c[6] = R.drawable.i116;
        c[7] = R.drawable.i117;
        c[8] = R.drawable.i118;
        c[9] = R.drawable.i119;
        c[10] = R.drawable.i120;
        c[11] = R.drawable.i121;
        c[12] = R.drawable.i122;
        c[13] = R.drawable.i123;
        c[14] = R.drawable.i124;
        c[15] = R.drawable.i125;
        c[16] = R.drawable.i126;
        c[17] = R.drawable.i127;
        c[18] = R.drawable.i128;
        c[19] = R.drawable.i129;
        c[20] = R.drawable.i130;
        c[21] = R.drawable.i131;
        c[22] = R.drawable.i132;
        c[23] = R.drawable.i133;
        c[24] = R.drawable.i134;
        c[25] = R.drawable.i135;
        c[26] = R.drawable.i136;
        c[27] = R.drawable.i137;
        c[28] = R.drawable.i138;
        c[29] = R.drawable.i139;
        c[30] = R.drawable.i140;
        c[31] = R.drawable.i141;

        return c;
    }

    public void onButtonClick(View view){

        if(blockSelection)
            return;

        ImageButton button = (ImageButton) view;
        Position pos = null;
        for(int i = 0; i < buttons.length; i++){
            for(int j = 0; j < buttons[i].length; j++){
                if(buttons[i][j] == button){
                    pos = new Position(i, j);
                    break;
                }
            }
        }

        if(pos == null)
            return;

        if(field.getCard(pos).isVisible())
            return;

        int value = field.getCard(pos).getValue();


        button.setImageResource(pics[value-110]);



        if(previousCard == null){
            previousCard = pos;
        }else{
            blockSelection = true;
            if(!field.isPair(previousCard, pos)){
                closeCards(previousCard, pos);
            }else{
                blockSelection = false;
                pairsFound++;
                previousCard = null;
                field.getCard(pos).setVisible(true);
                if(pairsFound >= (field.x*field.y)/4){
                    if(field.finished())
                        showVictory();
                }
            }
        }

    }

    public void closeCards(Position pos1, Position pos2){
        class CloseTask extends TimerTask
        {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    buttons[pos1.x][pos1.y].setImageResource(R.drawable.back2);
                    buttons[pos2.x][pos2.y].setImageResource(R.drawable.back2);
                    previousCard = null;
                    blockSelection = false;
                });
            }
        }

        Timer timer = new Timer();
        timer.schedule(new CloseTask(),1000);
    }

    private void receiveMessage(){
        String fieldSize = getIntent().getStringExtra("gameSize");

        String[] sizes = fieldSize.split("x");

        int x = Integer.parseInt(sizes[0]);
        int y = Integer.parseInt(sizes[1]);


        field = new Playground(x, y);
        field.init();

        generateGrid(y, x);
    }


    private void showVictory(){
    }


}