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
        if(compteurSommet>=1) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(getCouleur());
            paint.setStrokeWidth(getLargeur());
            paint.setStyle(Paint.Style.STROKE);
            Path path = new Path();
            path.moveTo(sommet.x, sommet.y);
            path.lineTo(sommet2.x, sommet2.y);
            path.lineTo(sommet3.x, sommet3.y);
            path.close();
            canvas.drawPath(path, paint);
        }
    }
    // premier sommet du triangle
    @Override
    public void add(Point p1) {
        sommet.set(p1.x,p1.y);
    }
    @Override
    public void tracer(Point p2) {
            sommet2.set(p2.x, p2.y);
          sommet3.x = sommet.x;
          sommet3.y = p2.y;
        compteurSommet = 3;

    }
//    public void tracer2(Point p3){
//
//
//            sommet3.set(p3.x, p3.y);
//
//            p.lineTo(sommet3.x, sommet3.y);
//            p.close(); // ferme le triangle
//
//            compteurSommet = 3;
//
//    }
}
