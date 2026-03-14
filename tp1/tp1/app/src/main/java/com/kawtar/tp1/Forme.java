package com.kawtar.tp1;

import android.graphics.Canvas;
import android.graphics.Path;

import java.util.ArrayList;

public abstract class Forme {

//    enum forme{
//        triangle,
//        cercle,
//        efface,
//        libre,
//        rectangle,
//    }
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

    public abstract void dessiner(Canvas canvas);


}
