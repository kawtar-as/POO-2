package com.keyv.annexe7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
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
   // Ecouteur ec;
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
        main.addView(surface);
       // ec = new Ecouteur();
    }

    private class SurfaceDessin extends View {
        Paint crayon;
        public SurfaceDessin(Context context) {
            super(context);
            this.setBackgroundResource(R.drawable.carte);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setColor(Color.RED);


        }
        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawRect(100,100,200,200,crayon);

        }

    }

}