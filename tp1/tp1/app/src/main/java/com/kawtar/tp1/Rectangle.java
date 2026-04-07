package com.kawtar.tp1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Rectangle extends  Forme {
    private Point depart,arrivee;


    public Rectangle(int couleur, int largeur) {
        super(couleur, largeur);
        this.depart = new Point();
        this.arrivee = new Point();
    }

    // methode de tracer
    @Override
    public void dessiner(Canvas canvas){
        Paint paint = new Paint( Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getCouleur());
        paint.setStrokeWidth(getLargeur());
        paint.setStyle(Paint.Style.STROKE);
        float x1 = Math.min(depart.x,arrivee.x); // cote gauche : plus petite x
        float y1 = Math.max(depart.x,arrivee.x); // cote droit : plus grande x
        float x2 = Math.min(depart.y,arrivee.y); // cote haut: plus petite y
        float y2 = Math.max(depart.y,arrivee.y); // cote bas :plus grande y
        canvas.drawRect(x1,x2,y1,y2,paint);

    }
    @Override
    public void add(Point p1){
        depart.x = p1.x;
        depart.y = p1.y;
        // Initialise aussi le point d'arrivée au même endroit
        arrivee.x = p1.x;
        arrivee.y = p1.y;
    }
    @Override
    public void tracer(Point p2){
      arrivee.x = p2.x;// Met à jour la coordonnée X d'arrivée
      arrivee.y = p2.y;// Met à jour la coordonnée Y d'arrivée
    }

    public Point getDepart() {
        return depart;
    }

    public Point getArrivee() {
        return arrivee;
    }
}
