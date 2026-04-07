package com.kawtar.tp1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.ArrayList;

public class TraceLibre extends Forme {
    private Path p ;

    public TraceLibre( int couleur, int largeur) {
        super(couleur,largeur);
        this.p = new Path();

    }

   @Override
   public void dessiner(Canvas canvas){
    Paint paint = new Paint( Paint.ANTI_ALIAS_FLAG);
    paint.setColor(getCouleur());
    paint.setStrokeWidth(getLargeur());
    paint.setStyle(Paint.Style.STROKE);
    canvas.drawPath(p,paint);
   }
    @Override
    public void add(Point depart){
        p.moveTo(depart.x,depart.y);
    }
    @Override
    public void tracer(Point arrive){
       p.lineTo(arrive.x, arrive.y);
    }
    public Path getP() {
        return p;
    }


}
