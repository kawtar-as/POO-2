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
    DialogTrait dialog;

    LinearLayout palette,outils;
    Paint crayon;
    Forme formeEnCours;
    ArrayList<Forme>paths,formeRdo;
    ArrayList<Paint>crayons;
    Point depart = new Point();
    Point arrivee = new Point();
    String color,outilActuel;
    int couleurFond, cptsommet =0 ;
    int width = 10;
    public void changerWidth(int largeur){this.width = largeur;}

    Bitmap bitmap;


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
        formeRdo = new ArrayList<>();
        dialog = new DialogTrait(this);
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
                if (formeEnCours == null) {
                    if (outilActuel.equals("tracer libre")) {
                        formeEnCours = new TraceLibre(Color.parseColor(color), width);
                    } else if (outilActuel.equals("efface")) {
                        formeEnCours = new Efface(couleurFond, width);
                    } else if (outilActuel.equals("rectangle")) {
                        formeEnCours = new Rectangle(Color.parseColor(color), width);
                    } else if (outilActuel.equals("cercle")) {
                        formeEnCours = new Cercle(Color.parseColor(color), width);
                    } else if (outilActuel.equals("triangle")) {
                        formeEnCours = new Triangle(Color.parseColor(color), width);
                    }
                }
                // ÉTAPE 2 : ACTION (Ajouter le point)
                if (formeEnCours != null) {
                    formeEnCours.add(depart);
                    if(formeEnCours instanceof Triangle){cptsommet++;}
                    v.invalidate();
                }
            }
            else if(event.getAction() == event.ACTION_MOVE){
                    arrivee.x = (int) event.getX();
                    arrivee.y = (int) event.getY();
                if (formeEnCours != null) {
//                    if(formeEnCours instanceof Triangle) formeEnCours.tracer2();
                    if(formeEnCours instanceof Efface)paths.add(formeEnCours);
                    if(formeEnCours instanceof Triangle){
                        if(cptsommet == 1){
                            formeEnCours.tracer(depart);
                            cptsommet ++;
                        }
                        else if(cptsommet == 2){
                            formeEnCours.tracer2(depart);
                            cptsommet =0;
                        }
                        v.invalidate();
                    }
                    formeEnCours.tracer(arrivee);
                    v.invalidate();
                }
            } else if (event.getAction() == event.ACTION_UP) {
                if(formeEnCours!=null) {
                    depart.x = (int) event.getX();
                    depart.y = (int) event.getY();
                    if(formeEnCours instanceof Triangle){

                    }
                    paths.add(formeEnCours);
                    formeEnCours = null; // vider le trait
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
            else if(v == outils.getChildAt(10)){
                outilActuel = "pipette";
                return;

            }
            else if(v == outils.getChildAt(3)){
                System.out.println("hello popo");
                outilActuel = "pot";
                if (color != null) {
                    couleurFond = Color.parseColor(color);
                    surface.invalidate();
                }
                return;

            }
           else  if(v == outils.getChildAt(6)){
                outilActuel = "taille_trait";
                dialog.show();


            }else  if(v == outils.getChildAt(1)){
                outilActuel = "rectangle";
                return;

            }
           else  if(v == outils.getChildAt(0)){
                outilActuel ="cercle";
                return;
            }
           else  if(v==outils.getChildAt(2)){
                outilActuel ="triangle";
                return;
           }
           else  if(v == outils.getChildAt(8) ){
                System.out.println("hello popo");
                outilActuel = "undo";
                    if (paths != null) {
                        formeRdo.add(paths.get(paths.size() - 1));
                        paths.remove(paths.size() - 1);
                        surface.invalidate();
                    }
          }
           else if (v == outils.getChildAt(9)){
               outilActuel = "redo";
               if(formeRdo!=null){
                   paths.add(formeRdo.get(formeRdo.size()-1));
                   formeRdo.remove(formeRdo.size()-1);
                   surface.invalidate();
               }
           }
            else if(v == outils.getChildAt(4)){
                outilActuel = "tracer libre";
                return;
            }
            // boutton pot de peinture
            outilActuel = "tracer libre";

            if(v instanceof  Button )  { // ajouter le truc de crayon aussi
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

            if(formeEnCours!=null) formeEnCours.dessiner(canvas);
            if(paths!= null){
                for (int i = 0 ; i < paths.size() ; i++){
                    paths.get(i).dessiner(canvas);

                }
            }

        }
    }

}