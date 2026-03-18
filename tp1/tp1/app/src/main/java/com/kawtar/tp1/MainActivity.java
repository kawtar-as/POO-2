package com.kawtar.tp1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SurfaceDessin surface;
    Ecouteur ec;
    LinearLayout main;
    ConstraintLayout dessin;
//    Button vert,rouge,rose,jaune,orange,blanc,noir,bleu;
    LinearLayout palette,outils;
    Paint crayon;
    Forme formeEnCours;
    ArrayList<Forme>paths;
    ArrayList<Paint>crayons;
    Point depart = new Point();
    Point arrivee = new Point();
    String color,outilActuel;
    int couleurFond ;
    int width = 15,  compteurSommet = 0;
    TraceLibre t;
    Rectangle r;
    Cercle c;
    Bitmap bitmap;
    Triangle tr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        main = findViewById(R.id.main);
        dessin = findViewById(R.id.dessin);
        palette = findViewById(R.id.palette);
        outils=findViewById(R.id.outils);

        // 1 ere etape
        ec = new Ecouteur();
        surface= new SurfaceDessin(this);
        //2 eme étape: parcourir chaque boutton pour mettre un écouteur
            // pour les bouttons
        for(int i= 0; i <palette.getChildCount();i++){
            if(palette.getChildAt(i) instanceof Button)
                palette.getChildAt(i).setOnClickListener(ec);
        }
        // pour les imagebutton
        for(int i= 0; i <outils.getChildCount();i++){
            if(outils.getChildAt(i) instanceof ImageButton)
                outils.getChildAt(i).setOnClickListener(ec);
        }

        surface.setOnTouchListener(ec);
        dessin.addView(surface);
        paths = new ArrayList<>();
    }


    private class Ecouteur implements View.OnClickListener, View.OnTouchListener{
        // appuie sur la surface
        Bitmap image;
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (outilActuel == null) outilActuel = "tracer libre";
            if( event.getAction() == event.ACTION_DOWN){
                //garder en mémoire le départ
                depart.x = (int) event.getX();
                depart.y = (int) event.getY();
            // si on dessine libre
                if(outilActuel.equals("tracer libre") ){
                    t = new TraceLibre(Color.parseColor(color),width);
                }
                else if (outilActuel.equals("efface")){
                    t = new TraceLibre(couleurFond,width);
                }
                else if(outilActuel.equals("pipette")){
                    int x = (int)(event.getX());
                    int y = (int)(event.getY());
                   image = surface.getBitmapImage() ;
                   int couleurP = image.getPixel(x,y);// renvoie entier couleur ou on a clicker
                    color= String.format("#%06X", (0xFFFFFF & couleurP));
                    outilActuel="tracer libre";
                    t = new TraceLibre(Color.parseColor(color),width);
                }
                else if(outilActuel.equals("pot")){
                    System.out.println("hello");
                    couleurFond = Color.parseColor(color);
                    t = null;
                    surface.invalidate();
                }
                else if(outilActuel.equals("taille_trait")){
                    // a faire jsp
                }
                else if(outilActuel.equals("rectangle")){
                    r = new Rectangle(Color.parseColor(color),width,depart,arrivee);
                    r.add(depart);
                }
                else if(outilActuel.equals("cercle")){
                    c = new Cercle(Color.parseColor(color),width);
                    c.add(depart);
                }
                else if(outilActuel.equals("triangle")){

                    if(compteurSommet == 0){
                    tr = new Triangle(Color.parseColor(color),width);
                        tr.add(depart);
                        compteurSommet++;
                    }else if(compteurSommet ==1){
                        tr.tracer(depart);
                        compteurSommet++;
                    }else if(compteurSommet == 2){
                        tr.tracer2(depart);
                        compteurSommet=0;
                        paths.add(tr);
                        tr=null;
                        v.invalidate();
                    }
                }
                if (t != null) t.add(depart);

            }
            else if(event.getAction() == event.ACTION_MOVE){
                    arrivee.x = (int) event.getX();
                    arrivee.y = (int) event.getY();
                if (t != null) {
                    t.tracer(arrivee);
                    v.invalidate();
                }
                if(r!= null) {
                    r.tracer(arrivee);
                    v.invalidate();
                }
                if(c!= null) {
                    c.tracer(arrivee);
                    v.invalidate();
                }
                if(tr != null && compteurSommet ==2){
                    v.invalidate();
                }

            } else if (event.getAction() == event.ACTION_UP) {
                if(t!=null) {
                    depart.x = (int) event.getX();
                    depart.y = (int) event.getY();
                    paths.add(t);
                    t = null; // vider le trait
                    v.invalidate();
                }
                if(r!=null){
                    paths.add(r);
                    r=null;
                    v.invalidate();
                }if(c!=null){
                    paths.add(c);
                    c=null;
                    v.invalidate();
                }

            }
            return true;
        }
        @Override
        public void onClick(View v) {
            // boutton effacer
            if(v == outils.getChildAt(5)){
                outilActuel = "efface";
                return;

            }
            //boutton pipette
            if(v == outils.getChildAt(10)){
                outilActuel = "pipette";
                return;

            }
            if(v == outils.getChildAt(3)){
                System.out.println("hello popo");
                outilActuel = "pot";
                if (color != null) {
                    couleurFond = Color.parseColor(color);
                    surface.invalidate();
                }
                return;

            }
            if(v == outils.getChildAt(6)){
                outilActuel = "taille_trait";
                return;

            } if(v == outils.getChildAt(1)){
                outilActuel = "rectangle";
                return;

            }
            if(v==outils.getChildAt(0)){
                outilActuel ="cercle";
                return;
            }
            if(v==outils.getChildAt(2)){
                outilActuel ="triangle";
                return;
            }
            // boutton pot de peinture
            outilActuel = "tracer libre";
            if(v!=null) {
                // on get le tag ou on a mis la valeur de la couleur en hexa
                color = v.getTag().toString();
                // on converti en couleur de android
                crayon.setColor(Color.parseColor(color));
            }


        }

    }
    private class SurfaceDessin extends View {
        public Bitmap getBitmapImage() {

            this.buildDrawingCache();
            bitmap = Bitmap.createBitmap(this.getDrawingCache());
            this.destroyDrawingCache();

            return bitmap;
        }
        public SurfaceDessin(Context context){
            super(context);
            // ici on change background/ les trucs de crayon
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            color = "#000000";
            couleurFond = Color.WHITE;
            crayons = new ArrayList<>();
            crayon.setStrokeWidth(width);
            crayon.setStyle(Paint.Style.STROKE);

        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(couleurFond);
            if(tr!=null) tr.dessiner(canvas);
            if(r!=null) r.dessiner(canvas);
            if(c!=null) c.dessiner(canvas);
            if(t!= null)  t.dessiner(canvas);
            if(paths!= null){

                for (int i = 0 ; i < paths.size() ; i++){
                   paths.get(i).dessiner(canvas);


                }
            }

        }
    }

}