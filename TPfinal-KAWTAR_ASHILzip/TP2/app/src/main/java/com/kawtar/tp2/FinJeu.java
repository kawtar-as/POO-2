package com.kawtar.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class FinJeu extends AppCompatActivity {
    Button rejouer;
    ListView listview;
    TextView text;
    Ecouteur ec;
    Intent i;
    ArrayList<String> meilleures;
    GestionBD instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fin_jeu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rejouer = findViewById(R.id.button);
        listview = findViewById(R.id.listee);
        text = findViewById(R.id.textView3);
        instance = GestionBD.getInstance(getApplicationContext());

        try {
            meilleures = instance.meilleurePointage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, meilleures);
        listview.setAdapter(adapter);
        ec = new Ecouteur();

        rejouer.setOnClickListener(ec);
        listview.setOnItemClickListener(ec);
        text.setText("LA PARTIE EST FINI !");


    }
    private class Ecouteur implements View.OnClickListener, AdapterView.OnItemClickListener{


        @Override
        public void onClick(View source) {
            if (source == rejouer){
                i = new Intent(FinJeu.this, Interface.class);
            }
            startActivity(i);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            listview.setOnItemClickListener(null);
            finish();
        }
    }
}