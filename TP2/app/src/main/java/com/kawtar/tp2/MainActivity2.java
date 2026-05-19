package com.kawtar.tp2;

import android.os.Bundle;
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
        Grille g = new Grille();
        g.creerGrille();
        // faire le o touch ou le on click
        for(int i =0; i < grilleJeu.getChildCount();i++){

            TableRow child = (TableRow) grilleJeu.getChildAt(i); // dans la ligne
            for(int j = 0 ; j < child.getChildCount(); j++){
                Composante child2 = (Composante) child.getChildAt(j);
                child2.setOnDragListener(ec);
                Lettre l = g.lettres[i][j];
                child2.getLettre().setText(String.valueOf(l.getAlphabet()));

            }
        }
        grille = new Lettre[4][4];


    }
    private class Ecouteur implements View.OnTouchListener,View.OnDragListener{


        @Override
        public boolean onDrag(View v, DragEvent event) {
            return false;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    }
}