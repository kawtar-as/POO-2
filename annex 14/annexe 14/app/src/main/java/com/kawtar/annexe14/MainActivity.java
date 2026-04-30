package com.kawtar.annexe14;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    LinearLayout main ;
    Ecouteur ec;
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

        ec = new Ecouteur();

        main = findViewById(R.id.main);
        //j'ajoute mes sources au écouteur
        // si on a eu bcp de jeutons on fait double boucle
        for(int i = 0; i < main.getChildCount();i++){
            LinearLayout enfant = (LinearLayout) main.getChildAt(i);
            enfant.setOnDragListener(ec); // colonnes
            enfant.getChildAt(0).setOnTouchListener(ec);//jetons

        }

    }
    private class Ecouteur implements View.OnDragListener, View.OnTouchListener
    {

        Drawable normal = getResources().getDrawable(R.drawable.background_contenant,null);
        Drawable select = getResources().getDrawable(R.drawable.background_contenant_selectionne,null);
        View jeton = null;
        @Override
        public boolean onDrag(View source, DragEvent event) {

            switch (event.getAction()){

                case DragEvent.ACTION_DRAG_ENTERED:
                    source.setBackground(select);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    source.setBackground(normal);

                    break;

                case DragEvent.ACTION_DROP:
                    jeton = (View) event.getLocalState(); //  il s'agit du jeton qu'on a choisi dans l' ombre qu'on drag
                    // chercher le jeton invisible
                    LinearLayout parent = (LinearLayout)jeton.getParent(); // on recupere le parent du jeton laissé invisible dans la colonne de départ
                    parent.removeView(jeton);// on l'enleve de son conteneur de départ
                    LinearLayout destination = (LinearLayout) source; // chercher la colonne d'arriver
                    destination.addView(jeton); //  ajouter a la colonne de destination
                    jeton.setVisibility(VISIBLE);
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    source.setBackground(normal);
                    break;

            }
            return true;
        }

        @Override
        public boolean onTouch(View source, MotionEvent event) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(source);
            source.startDragAndDrop(null,shadowBuilder,source,0);
            //cacher la source temporairement
            source.setVisibility(INVISIBLE);
            return true;
        }
    }


}