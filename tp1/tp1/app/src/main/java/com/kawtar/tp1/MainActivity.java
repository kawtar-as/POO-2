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
    LinearLayout palette,outils; // conteneur des boutons
    Paint crayon;
    Forme formeEnCours;
    // paths : toutes les formes dessinés   |    formeRdo: pour le redo
    ArrayList<Forme>paths,formeRdo;
    ArrayList<Paint>crayons;
    Point depart = new Point(),arrivee = new Point();
    String color,outilActuel;
    int couleurFond,width = 10;
    // méthode pour changer l'épaisseur du crayon
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

        // 1 ere etape : creation des objets
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
            // initialiser la couleur par défaut
            if (outilActuel == null) outilActuel = "tracer libre";
            // début du dessin
            if( event.getAction() == event.ACTION_DOWN){
                //garder en mémoire le départ
                depart.x = (int) event.getX();
                depart.y = (int) event.getY();
               // gestion des formes autres que triangle
                if(!outilActuel.equals("triangle")) {
                    // crée la forme selon l'outil choisi
                    if (formeEnCours == null) {
                        if (outilActuel.equals("tracer libre")) {
                            formeEnCours = new TraceLibre(Color.parseColor(color), width);
                        } else if (outilActuel.equals("efface")) {
                            formeEnCours = new Efface(couleurFond, width);
                        } else if (outilActuel.equals("rectangle")) {
                            formeEnCours = new Rectangle(Color.parseColor(color), width);
                        } else if (outilActuel.equals("cercle")) {
                            formeEnCours = new Cercle(Color.parseColor(color), width);
                        }else if(outilActuel.equals("pipette")){
                            // récuperer la couleur du pixel cliqé
                            image = surface.getBitmapImage();
                            int couleurP = image.getPixel(depart.x,depart.y);
                           // format trouvée sur internet puisque ma couleur est un String pas un int pour convertir en format hexadecimal
                            color = String.format("#%08X", couleurP);
                            outilActuel = "tracer libre";
                            formeEnCours = new TraceLibre(Color.parseColor(color), width);
                        }
                        // ajouter le point de départ a la forme
                        if(formeEnCours != null) formeEnCours.add(depart);
                        v.invalidate();
                        return  true;
                    }
                }
                if (outilActuel.equals("triangle")) {
                    if (formeEnCours == null) {
                        // premier clic
                        formeEnCours = new Triangle(Color.parseColor(color), width);
                        formeEnCours.add(depart); // Sommet 1
                        v.invalidate();
                        return  true;
                    }else{
                        //deuxieme clic
                        formeEnCours.tracer(depart);
                        paths.add(formeEnCours);
                        formeEnCours = null;
                        v.invalidate();
                        return true;
                    }
                }
            }
            // ÉTAPE 2 : ACTION (Ajouter le point)
            else if(event.getAction() == event.ACTION_MOVE){
                    arrivee.x = (int) event.getX();
                    arrivee.y = (int) event.getY();
                if (formeEnCours != null) {
                    //(Efface) ajouter chaque trait à l'historique
                    if(formeEnCours instanceof Efface)paths.add(formeEnCours);
                    // Tracer la forme en cours jusqu'au point d'arrivée
                    formeEnCours.tracer(arrivee);
                    v.invalidate();
                }
            } else if (event.getAction() == event.ACTION_UP) {
                if(formeEnCours!=null) {
                    depart.x = (int) event.getX();
                    depart.y = (int) event.getY();
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
            // pot de peinture
            else if(v == outils.getChildAt(3)){
                outilActuel = "pot";
                if (color != null) {
                    couleurFond = Color.parseColor(color);
                    surface.invalidate();
                }
                return;

            }
            // taille du trait avec alertDialog
            else if(v == outils.getChildAt(6)){
                outilActuel = "taille_trait";
                dialog.show();


            }
            //rectangle
            else if(v == outils.getChildAt(1)){
                outilActuel = "rectangle";
                return;

            }
            //cercle
            else  if(v == outils.getChildAt(0)){
                outilActuel ="cercle";
                return;
            }
            //triangle rectangle
            else  if(v==outils.getChildAt(2)){
                outilActuel ="triangle";
                return;
            }
            //annuler la derniere forme
            else if(v == outils.getChildAt(8) ){
                outilActuel = "undo";
                    if (paths != null && paths.size() > 0) {
                        // Déplacer la dernière forme de paths vers formeRdo
                        formeRdo.add(paths.get(paths.size() - 1));
                        paths.remove(paths.size() - 1);
                        surface.invalidate();}
            }
            // refaire ce qu'on a enlevé
            else if (v == outils.getChildAt(9)){
               outilActuel = "redo";
               if(formeRdo!=null && formeRdo.size() > 0){
                   // Restaurer la dernière forme supprimée
                   paths.add(formeRdo.get(formeRdo.size()-1));
                   formeRdo.remove(formeRdo.size()-1);
                   surface.invalidate();
               }
            }
            // bouton tracer libre
            else if(v == outils.getChildAt(4)){
                outilActuel = "tracer libre";
                return;
            }

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
            // Initialiser le crayon
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            color = "#000000"; // Couleur par défaut : noir
            couleurFond = Color.WHITE; // Fond par défaut : blanc
            crayons = new ArrayList<>();
            crayon.setStrokeWidth(width);
            crayon.setStyle(Paint.Style.STROKE);

        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            // Remplir le fond avec la couleur du fond
            canvas.drawColor(couleurFond);
            // Dessiner la forme en cours
            if(formeEnCours!=null) formeEnCours.dessiner(canvas);
            // Dessiner toutes les formes finalisée
            if(paths!= null){
                for (int i = 0 ; i < paths.size() ; i++){
                    //  l'efface doit utiliser la couleur de fond
                    if(paths.get(i) instanceof  Efface){
                        paths.get(i).setCouleur(couleurFond);
                    }
                    paths.get(i).dessiner(canvas);

                }
            }
        }
    }
}