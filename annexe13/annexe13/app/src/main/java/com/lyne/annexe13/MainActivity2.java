package com.lyne.annexe13;

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

public class MainActivity2 extends AppCompatActivity {

    EditText nom, microbrasserie;
    Button onEnregistrer;
    RatingBar ratingBar;
    Ecouteur ec;
    GestionBD instance;
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

        nom = findViewById(R.id.nomBiere);
        microbrasserie = findViewById(R.id.microbrasserie);
        ratingBar = findViewById(R.id.ratingBar);
        onEnregistrer = findViewById(R.id.enregistrer);
        instance = GestionBD.getInstance(getApplicationContext());

        ec = new Ecouteur();
        onEnregistrer.setOnClickListener(ec);
    }
    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            instance.ajouterEvaluation(new Evaluation(nom.getText().toString(), microbrasserie.getText().toString(), ratingBar.getRating()));
            finish();
        }

    }
}