package com.kawtar.exemple3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class mainActivityVoyage extends AppCompatActivity {


    Ecouteur ec;

    ImageButton avion, hotel;
    TextView txt1,txt2 , total;
    int compteur1 =0, compteur2=0;
    Button boutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_voyage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        avion = findViewById(R.id.avion);
        hotel = findViewById(R.id.hotel);
        txt1 = findViewById(R.id.text1);
        txt2 = findViewById(R.id.textt2);
        boutton = findViewById(R.id.b);
        total = findViewById(R.id.total);

        ec = new Ecouteur();
        avion.setOnClickListener(ec);
        hotel.setOnClickListener(ec);
        boutton.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View source) {
            Commande c = new Commande();
            if(source == avion){
                compteur1+=1;
                txt1.setText(compteur1 +"");
            }
            else{
                compteur2+=1;
                txt2.setText(compteur2+"");
            }

            if(source == boutton){
                double t = c.grandTotal();
                DecimalFormat format = new DecimalFormat("0.00");
                total.setText(format.format(t));
            }
        }
    }
}