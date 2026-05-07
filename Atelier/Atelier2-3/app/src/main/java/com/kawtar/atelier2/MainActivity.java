package com.kawtar.atelier2;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    View view ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        view = findViewById(R.id.view2);
        MonTimer m = new MonTimer();
        m.start();

    }
    //CountDownTimer est une classe abstraite
    private class MonTimer extends CountDownTimer{
        int rouge,vert,bleu;
        public MonTimer() {
            super(2000, 200);
        }

        @Override
        public void onTick(long millisUntilFinished) { //achaque2000
            rouge+=20;
            vert+=8;
            bleu+=14;
            view.setBackgroundColor(Color.rgb(rouge,vert,bleu));
        }

        @Override
        public void onFinish() {

        }
    }













}