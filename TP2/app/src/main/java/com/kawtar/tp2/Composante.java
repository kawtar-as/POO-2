package com.kawtar.tp2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Composante extends LinearLayout {
    TextView multiplicateur,point,lettre;
    private Lettre lettreObjet;
    public Composante(Context context) {
        super(context);
        init(context);
    }

    public Composante(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Composante(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init (Context context ){
        LayoutInflater.from(context).inflate(R.layout.composante, this, true);
        multiplicateur = findViewById(R.id.multiplicateur);
        point = findViewById(R.id.point);
        lettre = findViewById(R.id.lettre);

    }

    public TextView getMultiplicateur() {
        return multiplicateur;
    }

    public void setMultiplicateur(TextView multiplicateur) {
        this.multiplicateur = multiplicateur;
    }

    public TextView getPoint() {
        return point;
    }

    public void setPoint(TextView point) {
        this.point = point;
    }

    public TextView getLettre() {
        return lettre;
    }

    public void setLettre(TextView lettre) {
        this.lettre = lettre;
    }

    public Lettre getLettreObjet() {
        return lettreObjet;
    }

    public void setLettreObjet(Lettre lettreObjet) {
        this.lettreObjet = lettreObjet;
    }
}
