package com.kawtar.tp1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Cercle extends  Forme {
    private float rayon;
    private Point centre;

    public Cercle(int couleur, int largeur) {
        super(couleur, largeur);
        this.rayon = rayon;
        this.centre = new Point();
    }

    // methode de tracer
    @Override
    public void dessiner(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getCouleur());
        paint.setStrokeWidth(getLargeur());
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centre.x,centre.y,rayon, paint);
    }

    public void add(Point p1) {
        centre.x = p1.x;
        centre.y = p1.y;
        this.rayon = 0;
    }

    public void tracer(Point p2) {
        this.rayon = Math.abs(p2.x-centre.x);
    }

    public float getRayon() {
        return rayon;
    }

    public Point getCentre() {
        return centre;
    }
}
