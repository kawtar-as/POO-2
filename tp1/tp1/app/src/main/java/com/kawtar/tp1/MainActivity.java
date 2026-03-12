package com.kawtar.tp1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SurfaceDessin surface;
    Ecouteur ec;
    LinearLayout main;
    ConstraintLayout dessin;
//    Button vert,rouge,rose,jaune,orange,blanc,noir,bleu;
    LinearLayout palette,outils;
    Paint crayon,crayonActuel;
    Path path;
    Canvas canvas;
    ArrayList<Path>paths;
    ArrayList<Paint>crayons;
    Point depart = new Point();
    Point arrivee = new Point();
    String color;

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
            // si on dessine libre


            if( event.getAction() == event.ACTION_DOWN){
                path= new Path();

                //garder en mémoire le départ
                depart.x = (int) event.getX();
                depart.y = (int) event.getY();
                path.moveTo(depart.x,depart.y);
                // je cree un nv crayon
                // pour que les couleurs des traits qu on a fait avant ne changent pas tous
                Paint c = new Paint(crayon);
                crayons.add(c);
                paths.add(path);
            }
            else if(event.getAction() == event.ACTION_MOVE){
                arrivee.x = (int) event.getX();
                arrivee.y = (int) event.getY();
                path.lineTo(arrivee.x, arrivee.y);
                v.invalidate();

            } else if (event.getAction() == event.ACTION_UP) {

                depart.x = (int) event.getX();
                depart.y = (int) event.getY();

            }
            return true;
        }
        @Override
        public void onClick(View v) {
                System.out.println("allo");
                // on get le tag ou on a mis la valeur de la couleur en hexa
               color = v.getTag().toString();
               // on converti en couleur de android
               crayon.setColor(Color.parseColor(color)) ;


        }

    }
    private class SurfaceDessin extends View {

        public SurfaceDessin(Context context){
            super(context);
            // ici on change background/ les trucs de crayon
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            path = new Path();
            paths = new ArrayList<>();
            crayons = new ArrayList<>();
            crayon.setColor(Color.BLACK);// par défaut
            crayon.setStyle(Paint.Style.STROKE);
            crayon.setStrokeWidth(15);


        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            for (int i = 0 ; i < paths.size() ; i++){
                // on dessine chaque path dans la liste  avec un crayon choisi
                canvas.drawPath(paths.get(i),crayons.get(i));
            }
            canvas.drawPath(path,crayon);
        }
    }

}