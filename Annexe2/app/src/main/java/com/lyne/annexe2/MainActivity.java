package com.lyne.annexe2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Button boutonValider;
    Button bouttonEnvoyer;
    EditText champNomCompte, courriel,transfert;

    TextView champSolde;
    ArrayList<String> choix;

    int solde;

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
        bouttonEnvoyer = findViewById(R.id.boutonEnvoyer);
        boutonValider = findViewById(R.id.boutonValider);
        champNomCompte = findViewById(R.id.compteChoisi);
        champSolde = findViewById(R.id.solde);
        courriel = findViewById(R.id.mailAdress);
        transfert = findViewById(R.id.montant);

        choix = new ArrayList<>();
        choix.add("CHEQUE");
        choix.add("EPARGNE");
        choix.add("EPARGNEPLUS");

        // étape 1

        ec = new Ecouteur();
        // étape 2
        boutonValider.setOnClickListener(ec);
        bouttonEnvoyer.setOnClickListener(ec);
    }

    //étape 3

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            if( source == boutonValider) {
                //quand on clic on est ici
                String nomCompte = champNomCompte.getText().toString();
                //trim retire les espaces au début et à la fin
                nomCompte = nomCompte.trim().toUpperCase();
                if (choix.contains(nomCompte)) {
                    solde = 500;
                    champSolde.setText(String.valueOf(solde));
                } else {
                    champSolde.setText("Pas un bon nom");
                    champNomCompte.setText("");
                }

            } // bouuton envoyer
            else{
                String mail = courriel.getText().toString();
                String t = transfert.getText().toString();

               if(mail.length()!=0){
                  if(solde >= Integer.parseInt(String.valueOf(t))){
                      solde -=  Integer.parseInt(String.valueOf(t))  ;
                     champSolde.setText(String.valueOf(solde));
                  }else{
                      courriel.setText("Impossible");
                      transfert.setText("");
                  }
               }else{
                   courriel.setText("Indiquer un destinataire ");
               }

            }
        }
    }
}