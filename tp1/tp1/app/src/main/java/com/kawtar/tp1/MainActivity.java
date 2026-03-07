package com.kawtar.tp1;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SurfaceDessin surface;
    Ecouteur ec;
    LinearLayout main;
    ConstraintLayout dessin;
//    Button vert,rouge,rose,jaune,orange,blanc,noir,bleu;
    LinearLayout palette,outils;
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
        main = findViewById(R.id.main);
        dessin = findViewById(R.id.dessin);
        palette = findViewById(R.id.palette);
        outils=findViewById(R.id.outils);

        // 1 ere etape
        ec = new Ecouteur();
        surface= new SurfaceDessin(this);
        //2 eme étape: parcourir chaque boutton pour mettre un écouteur
            // pour les bouttons
        for(int i= 0; i <palette.getChildCount();i++){
            if(palette.getChildAt(i) instanceof Button)
                palette.getChildAt(i).setOnClickListener(ec);
        }
        // pour les imagebutton
        for(int i= 0; i <outils.getChildCount();i++){
            if(outils.getChildAt(i) instanceof ImageButton)
                outils.getChildAt(i).setOnClickListener(ec);
        }

        surface.setOnTouchListener(ec);
        dessin.addView(surface);
    }

    private class Ecouteur implements View.OnClickListener, View.OnTouchListener{
        // appuie sur la surface
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
        @Override
        public void onClick(View v) {

        }

    }
    private class SurfaceDessin extends View {
        public SurfaceDessin(Context context){
            super(context);
            // ici on change background/ les trucs de crayon

        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
        }
    }

}