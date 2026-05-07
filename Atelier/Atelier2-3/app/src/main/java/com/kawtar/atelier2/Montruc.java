package com.kawtar.atelier2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Montruc extends ConstraintLayout {
    ///  on met plusieurs constructeur dans le but de drag and dop et depeandament de la situation des drag and dro
    TextView t1, t2, t3;

    public Montruc(@NonNull Context context) {
        super(context);
        init(context);
    }

    public Montruc(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Montruc(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        // instancier un élément de cette classe à partir de son fichier .xml créé au #1
        LayoutInflater.from(context).inflate(R.layout.letruc, this, true);
        //initialise les composant internes avec find view ,autres
        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView3);

    }
    // des guetters
    public TextView getT1() {
        return t1;
    }

    public TextView getT2() {
        return t2;
    }

    public TextView getT3() {
        return t3;
    }

}