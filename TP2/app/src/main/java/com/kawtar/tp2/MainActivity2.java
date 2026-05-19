package com.kawtar.tp2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    SeekBar seekBar;
    Ecouteur ec;
    Lettre [][] grille;
    TableLayout grilleJeu;
    Intent i ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        seekBar= findViewById(R.id.seekBar);
        grilleJeu = findViewById(R.id.grilleJeu);
        ec = new Ecouteur();

        seekBar.setOnSeekBarChangeListener(ec);
        seekBar.setMax(75000);
        seekBar.setProgress(75000); // on le set au maximum

        MonTimer m = new MonTimer();
        m.start();
        Grille g = new Grille();
        g.creerGrille();
        grille = g.lettres; // lier
        g.genererMultiplicateur();

        for(int i =0; i < grilleJeu.getChildCount();i++){
            // faire
            TableRow child = (TableRow) grilleJeu.getChildAt(i); // dans la ligne
            for(int j = 0 ; j < child.getChildCount(); j++){
                Composante child2 = (Composante) child.getChildAt(j);
                child2.setOnDragListener(ec);
                Lettre l = grille[i][j];
                child2.getLettre().setText(String.valueOf(l.getAlphabet()));
                child2.getPoint().setText(String.valueOf(l.getValeur())); //  les points
                if(l.getMultiplicateur()>1){
                    child2.getMultiplicateur().setText("x"+ l.getMultiplicateur());
                }
                else if(l.getMultiplicateurMot()>1){
                    child2.getMultiplicateur().setText("X"+ l.getMultiplicateurMot());
                }else{
                    child2.getMultiplicateur().setText("x1");
                }
            }
        }


    }
    private class Ecouteur implements View.OnTouchListener,View.OnDragListener,SeekBar.OnSeekBarChangeListener{


        @Override
        public boolean onDrag(View v, DragEvent event) {
            return false;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private class MonTimer extends CountDownTimer {

        public MonTimer() {
            super(75000, 100);
        } // achaque seconde on update

        @Override
        public void onTick(long millisUntilFinished) {
            seekBar.setProgress((int)millisUntilFinished);

        }

        @Override
        public void onFinish() {
            seekBar.setProgress(0);
            i = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(i);
        }

    }
}