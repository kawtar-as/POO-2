package com.kawtar.exemple3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    ImageButton onBouteille, onGourde, onVerre;
    int compteur;
    TextView quantite;
    ProgressBar bar ;
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

        onBouteille = findViewById(R.id.imageButton2);
        onGourde = findViewById(R.id.imageButton);
        onVerre = findViewById(R.id.imageButton3);
        quantite = findViewById(R.id.textView3);
        bar = findViewById(R.id.progressBar);

        ec = new Ecouteur();
        onBouteille.setOnClickListener(ec);
        onGourde.setOnClickListener(ec);
        onVerre.setOnClickListener(ec);

    }

    private class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View source) {
            if(source == onVerre){
                compteur += 150;
                quantite.setText(compteur + " ml");
                bar.setProgress(compteur);

            }else if(source == onGourde){
                compteur+=1500;
                quantite.setText(compteur+ " ml");
                bar.setProgress(compteur);
            }
            else{
                compteur+=330;
                quantite.setText(compteur+ " ml");
                bar.setProgress(compteur);
            }
        }
    }



}