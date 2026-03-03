package com.keyv.annexe7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SurfaceDessin surface;
    Point depart = new Point();
    Point arrivee = new Point();
    Ecouteur ec;
    ConstraintLayout main;
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
        surface = new SurfaceDessin(this);
       // surface.setLayoutParams(new ConstraintLayout.LayoutParams(-1,1));
        main.addView(surface);
        ec = new Ecouteur();
        surface.setOnTouchListener(ec); // pcq j'ai pas de boutton
    }
    Paint crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
    private class Ecouteur implements  View.OnTouchListener{
        @Override
        public boolean onTouch (View v, MotionEvent motionEvent){
            // appuie sur la surface
            if( motionEvent.getAction() == motionEvent.ACTION_DOWN){
                //garder en mémoire le départ
                depart.x = (int) motionEvent.getX();
                depart.y = (int) motionEvent.getY();
                v.invalidate();// redessiner -->efface et appeler la methode onDraw
            }
            else if(motionEvent.getAction() == motionEvent.ACTION_MOVE){
                arrivee.x = (int) motionEvent.getX();
                arrivee.y = (int) motionEvent.getY();
                v.invalidate();

            } else if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                depart.x = (int) motionEvent.getX();
                depart.y = (int) motionEvent.getY();
            }
            return true;
        }
    }
    private class SurfaceDessin extends View {

        public SurfaceDessin(Context context) {
            super(context);
            this.setBackgroundResource(R.drawable.carte);
            crayon.setColor(Color.RED);
        }
        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            if(depart!=null){
                canvas.drawRect(depart.x-20,depart.y-20,depart.x+20,depart.y+20,crayon);
            }
            if(arrivee !=null){
                canvas.drawRect(arrivee.x-20,arrivee.y-20,arrivee.x+20,arrivee.y+20,crayon);
                canvas.drawLine(depart.x,depart.y,arrivee.x,arrivee.y,crayon);
            }

        }

    }

}