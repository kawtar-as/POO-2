package com.lyne.annexe13;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    ListView liste;
    Ecouteur ec;
    GestionBD instance;
    ArrayList<String> meilleures;
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
        liste = findViewById(R.id.liste);
        instance = GestionBD.getInstance(getApplicationContext());

        try {
            meilleures = instance.retournerMeilleure();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, meilleures);
        liste.setAdapter(adapter);
        ec = new Ecouteur();

        liste.setOnItemClickListener(ec);
    }
    private class Ecouteur implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            liste.setOnItemClickListener(null);
            finish();
        }
    }
}