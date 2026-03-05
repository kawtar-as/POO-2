package com.kawtar.annexe1tp1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout parent;
    Ecouteur ec;
    Button boutton;
    EditText line1, line2;
    Surface s ;

    int l1,l2;
    Path p;

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

        line1 = findViewById(R.id.editText);
        line2 = findViewById(R.id.editText2);
        boutton = findViewById(R.id.button);
        parent = findViewById(R.id.parent);
        ec = new Ecouteur();
        s = new Surface(this);
        s.setLayoutParams(new ViewGroup.LayoutParams(-1,-1)); // match parent et on l'ajoute a match parent
        parent.addView(s);
        boutton.setOnClickListener(ec);
        p = new Path();
    }

    private class Ecouteur implements View.OnClickListener {
    public void onClick(View source) {
        l1 = Integer.parseInt(line1.getText().toString()) ;
        l2 = Integer.parseInt(line2.getText().toString()) ;

        if(p.isEmpty()) {
            p.moveTo(l1, l2);
        }else{
            p.lineTo(l1, l2);
        }
        s.invalidate(); // effacer et redessiner
    }
    }
    private class Surface extends View {
        Paint crayon;
        public Surface(Context context) {
            super(context);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            setBackgroundColor(Color.GRAY);
            crayon.setStyle(Paint.Style.STROKE); // important
            crayon.setStrokeWidth(15);
            crayon.setColor(Color.BLACK);

        }
        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawPath(p,crayon);

        }
    }
}