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
        Paint paint = new Paint( Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getCouleur());
        paint.setStrokeWidth(getLargeur());
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(p,paint);
    }
    // premier sommet du triangle
    @Override
    public void add(Point p1) {
        if(compteurSommet == 0) {
            sommet.x = p1.x;
            sommet.y = p1.y;
            p.moveTo(sommet.x, sommet.y);
            compteurSommet++;
        }
    }
    @Override
    public void tracer(Point p2) {
        if(compteurSommet == 1) {
            sommet2.x = p2.x;
            sommet2.y = p2.y;
            p.lineTo(sommet2.x, sommet2.y);
            compteurSommet++;
        }
        else if(compteurSommet == 2){
        sommet3.x = p2.x;
        sommet3.y = p2.y;
        p.lineTo(sommet3.x, sommet3.y);
        p.close(); // fermer le triangle
        }

    }
//    @Override
//    public void tracer2(Point p3){
//        sommet3.x = p3.x;
//        sommet3.y = p3.y;
//        p.lineTo(sommet3.x, sommet3.y);
//        p.close(); // fermer le triangle
//    }
}
