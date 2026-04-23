package com.kawtar.annexe13;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    RatingBar rating;
    Ecouteur ec;
    Button enregistrer;
    GestionBD instance ;
    EditText t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        instance = GestionBD.getInstance(getApplicationContext());
        rating= findViewById(R.id.ratingBar);
        enregistrer=findViewById(R.id.button5);
        t1=findViewById(R.id.editTextText);
        t2=findViewById(R.id.editTextText2);
        ec = new Ecouteur();
        enregistrer.setOnClickListener(ec);
    }
    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
        if( v == enregistrer){
          Evaluation e = new Evaluation(t1.getText().toString(),t2.getText().toString(),rating.getRating());
           instance.ajouterBiere(e);//ouvrir bd
           finish();// fermer l'actovite
        }
        }


    }
}