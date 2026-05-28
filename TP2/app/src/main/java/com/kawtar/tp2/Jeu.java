package com.kawtar.tp2;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Jeu extends AppCompatActivity {

    SeekBar seekBar;
    TextView word, pointTotal,pointchacun,remarque;
    Ecouteur ec;
    Lettre [][] grille;
    TableLayout grilleJeu;
    Intent i ;
    LinearLayout main;
    String mot = "";
    GestionBD instance;
    ArrayList<String> motTrouve;
    Mot m;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.jeu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        instance = GestionBD.getInstance(getApplicationContext());
        main = findViewById(R.id.main);
        seekBar= findViewById(R.id.seekBar);
        grilleJeu = findViewById(R.id.grilleJeu);
        word = findViewById(R.id.mot);
        remarque = findViewById(R.id.remarque);
        pointchacun = findViewById(R.id.pointnow);
        pointTotal = findViewById(R.id.totalpoint);
        ec = new Ecouteur();

        seekBar.setMax(75000);
        seekBar.setProgress(75000); // on le set au maximum

        motTrouve = new ArrayList<>();
        MonTimer m = new MonTimer();
        m.start();
        // creer la grille
        Grille g = new Grille();
        g.creerGrille(); // les lettres
        grille = g.getLettres(); // lier
        g.genererMultiplicateur();

        // parcours le table Layout
        for(int i =0; i < grilleJeu.getChildCount();i++){
            // faire
            TableRow child = (TableRow) grilleJeu.getChildAt(i); // dans la ligne
            for(int j = 0 ; j < child.getChildCount(); j++){
                Composante child2 = (Composante) child.getChildAt(j);
                child2.setOnDragListener(ec);
                child2.setOnTouchListener(ec);
                Lettre l = grille[i][j];
                child2.setLettreObjet(l);
                child2.getLettre().setText(String.valueOf(l.getAlphabet()));
                child2.getPoint().setText(String.valueOf(l.getValeur()));
                if(l.getMultiplicateur()>1){
                    // on met le multiplicateur pour lettre en orange
                    child2.getMultiplicateur().setText("x"+ l.getMultiplicateur());
                    child2.getMultiplicateur().setTextColor(Color.parseColor("#FFB347"));

                }
                // si c est un multiplicateur de mot on set le text en jaune comme ca on vois qui est de ot et qui de lettre
                else if(l.getMultiplicateurMot()>1){
                    child2.getMultiplicateur().setText("x"+ l.getMultiplicateurMot());
                    child2.getMultiplicateur().setTextColor(Color.YELLOW);

                }
                else
                { // quand y pas de mutiplicateur (mutilicateur = 1)
                    child2.getMultiplicateur().setText("x1");

                }
            }
        }
    }
    private class Ecouteur implements View.OnTouchListener,View.OnDragListener{
        Drawable selectionne = getResources().getDrawable(R.drawable.background_contenant_selectionne,null);
        Drawable normal = getResources().getDrawable(R.drawable.background_contenant,null);
        @Override
        public boolean onDrag(View source, DragEvent event) {
            Composante c = (Composante)  source;
            switch(event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    m = new Mot();
                    // reinitialiser
                    mot = "";
                    word.setText(mot);
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    remarque.setText("");
                    source.setBackground(selectionne);
                    Lettre lettreNow =  c.getLettreObjet(); // recuperer la lettre
                    m.ajouterLettres(lettreNow );
                    mot += c.getLettre().getText();
                    word.setText(mot);
                    break;
                case DragEvent.ACTION_DROP:
                    if(motTrouve.contains(mot)){ // si le mot existe deja dans la liste
                        remarque.setTextColor(Color.RED);
                        remarque.setText("Vous avez déja choisi le mot");
                        ObjectAnimator oa = ObjectAnimator.ofFloat(word, View.X, word.getX()-20,word.getX()+20,word.getX()-20,word.getX()+20, word.getX(),word.getX());
                        oa.setDuration(1000);
                        oa.start();
                    }
                     else if(instance.motExist(mot)) { // si existe dans la base de données
                        System.out.println("existe");
                         motTrouve.add(mot);
                         remarque.setTextColor(Color.GREEN);
                         remarque.setText("Vous avez trouvé un mot");
                         total += m.sommeValeur();
                         pointchacun.setText(String.valueOf(m.sommeValeur()));
                         pointTotal.setText(" Score : "+ total);

                    }
                    else{
                    ObjectAnimator oa = ObjectAnimator.ofFloat(word, View.X, word.getX()-20,word.getX()+20,word.getX()-20,word.getX()+20, word.getX(),word.getX());
                    oa.setDuration(1000);
                    oa.start();
                }
                    break;
                    // ici on dooit get la lettre selectiomne et la stocker dans le mot
                case DragEvent.ACTION_DRAG_ENDED:
                    source.setBackground(normal);
                    break;
            }
            return true;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ShadowInvisible shadowInvisible = new ShadowInvisible();
            v.startDragAndDrop(null,shadowInvisible,v,0);
            return true;
        }
    }
    private static class ShadowInvisible extends View.DragShadowBuilder
    {
        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            // tout petit
            outShadowSize.set(1, 1);
            outShadowTouchPoint.set(0, 0);
        }
        @Override
        public void onDrawShadow(Canvas canvas) {
            // rien faire, on ne dessine rien
        }

    }

    private class MonTimer extends CountDownTimer {

        public MonTimer() {
            super(75000, 100);
        } // achaque seconde on update

        @Override
        public void onTick(long millisUntilFinished) {
            seekBar.setProgress((int)millisUntilFinished);

        }

        @Override
        public void onFinish() {
            instance.ajouterPointage(new Pointage(total));
            seekBar.setProgress(0);
            i = new Intent(Jeu.this, FinJeu.class);
            startActivity(i);

        }

    }
}