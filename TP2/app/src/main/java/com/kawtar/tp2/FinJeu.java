package com.kawtar.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FinJeu extends AppCompatActivity {
    Button rejouer;
    ListView listview;
    TextView text;
    Ecouteur ec;
    Intent i;
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

        rejouer = findViewById(R.id.button);
        listview = findViewById(R.id.listee);
        text = findViewById(R.id.textView3);
        ec = new Ecouteur();
        rejouer.setOnClickListener(ec);

        text.setText("LA PARTIE EST FINI !");
    }
    private class Ecouteur implements View.OnClickListener{


        @Override
        public void onClick(View source) {
            if (source == rejouer){
                i = new Intent(FinJeu.this, Interface.class);
            }
            startActivity(i);
        }
    }
}