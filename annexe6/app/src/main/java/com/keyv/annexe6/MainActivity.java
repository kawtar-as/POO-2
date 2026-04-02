package com.keyv.annexe6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SurfaceDessin surf;
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
        // etape 1
        surf = new SurfaceDessin(this);
        // etape 2
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(concertirDpEnPx(200), concertirDpEnPx(200))); //prends toute la place
        // ou bien on met : surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));
        // etape 3
        main.addView(surf);

    }

    // convertir les dp en public
    public int concertirDpEnPx (int dp){
        float densite = this.getResources().getDisplayMetrics().density;
        return Math.round(densite * dp);
    }


    private class SurfaceDessin extends View{

        Paint crayon,crayon1,crayon2,crayon3;

        public SurfaceDessin(Context context) {
            super(context);
            this.setBackgroundColor(Color.CYAN);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setColor(Color.BLUE);

            crayon1 = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon1.setStyle(Paint.Style.STROKE);
            crayon1.setStrokeWidth(10);
            crayon1.setColor(Color.YELLOW);

            crayon2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon2.setColor(Color.RED);
            crayon3 = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon3.setColor(Color.MAGENTA);
        }
        // la methode est rappelé automatiquement quand on instancie la surface de dessin
        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawCircle(100,100,80,crayon);
            canvas.drawCircle(280,100,80,crayon2);

            canvas.drawArc(260,300,400,400,240,120,true,crayon2);
            canvas.drawArc(260,300,400,400,0,120,true,crayon1);
            canvas.drawArc(260,300,400,400,-240,120,true,crayon3);
        }
    }
}