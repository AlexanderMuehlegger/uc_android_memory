package com.example.uc_aufgabe03_memory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class Start_Screen_Activity extends AppCompatActivity {

    private Playground playground;
    private Spinner sizeSpinner;

    LottieAnimationView startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        startButton = findViewById(R.id.startButton);

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                startButton.setFocusedByDefault(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        startButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startGame();

                return false;
            }
        });

    }



    private boolean changeActivity(){
        if(sizeSpinner != null){
            String spinnerContent = sizeSpinner.getSelectedItem().toString();

            if(spinnerContent.equals("Choose"))
                return false;

            Intent changeActivityIntent = new Intent(this, MemoryActivity.class);
            changeActivityIntent.putExtra("gameSize", sizeSpinner.getSelectedItem().toString());
            startActivity(changeActivityIntent);
            this.finish();
            return true;
        }else{
            System.out.println("ERROR: Spinner is NULL!");
            return false;
        }
    }


    private boolean firstStart = true;

    public void startGame(View view) {
        startGame();
    }

    public void startGame(){

            startButton.setMaxFrame(60);
            startButton.setVisibility(View.VISIBLE);

            startButton.playAnimation();

            startButton.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    changeActivity();
                    Timer timer = new Timer();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            startButton.setProgress(0f);
                        }
                    }, 1000);

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });



    }
}