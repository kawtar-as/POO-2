package com.kawtar.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Interface extends AppCompatActivity {
    ListView liste;
    Ecouteur ec;
    Intent i ;
    ArrayList<String> meilleures;
    Button jouer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.interface_debut);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        jouer = findViewById(R.id.start);
        liste = findViewById(R.id.list);

        meilleures = new ArrayList<>();

        ec = new Ecouteur();
        jouer.setOnClickListener(ec);

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, meilleures);
        liste.setAdapter(adapter);

        liste.setOnItemClickListener(ec);
    }
    private class Ecouteur implements View.OnClickListener,AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            liste.setOnItemClickListener(null);
            finish();
        }

        @Override
        public void onClick(View source) {
            if(source == jouer ){
                i = new Intent(Interface.this, Jeu.class);

            }
            startActivity(i);
        }
    }
}