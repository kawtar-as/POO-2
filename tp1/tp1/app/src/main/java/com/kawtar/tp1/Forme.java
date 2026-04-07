package com.kawtar.tp1;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;

import java.util.ArrayList;

public abstract class Forme {


    private Path p ;
    private int couleur;
    private int largeur;


    public Forme( int couleur, int largeur) {

        this.couleur = couleur;
        this.largeur = largeur;
    }


    public int getCouleur() {
        return couleur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public abstract void dessiner(Canvas canvas);
    public abstract void tracer(Point point);
    public abstract void add(Point p);

    public void tracer2(Point p) {
    }


}
