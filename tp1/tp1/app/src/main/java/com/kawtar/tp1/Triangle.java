package com.kawtar.tp1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Triangle extends Forme{

    Point sommet, sommet2, sommet3;
    int compteurSommet = 0;
    Path p ;

    public Triangle(int couleur, int largeur) {
        super(couleur, largeur);
        this.sommet = new Point();
        this.sommet2 = new Point();
        this.sommet3 = new Point();
        this.p = new Path();
    }

    @Override
    public void dessiner(Canvas canvas) {
        // verifie que le triangle a au moins un sommet
        if(compteurSommet>=1) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(getCouleur());
            paint.setStrokeWidth(getLargeur());
            paint.setStyle(Paint.Style.STROKE);
            Path path = new Path();
            path.moveTo(sommet.x, sommet.y); // premier sommet
            path.lineTo(sommet2.x, sommet2.y); // ligne vers le 2e sommet
            path.lineTo(sommet3.x, sommet3.y); // trace vers le 3eme sommet
            path.close(); // ferme le path
            canvas.drawPath(path, paint);
        }
    }
    // premier sommet du triangle
    @Override
    public void add(Point p1) {
        sommet.set(p1.x,p1.y);
    }
    @Override
    public void tracer(Point p2) { // triangle rectangle
        sommet2.set(p2.x, p2.y);
        // troisieme sommet du triangle rectangle
        sommet3.x = p2.x;
        sommet3.y = sommet.y;
        compteurSommet = 3; // mrque que maintenant les 3 sommets sont fini

    }

}
