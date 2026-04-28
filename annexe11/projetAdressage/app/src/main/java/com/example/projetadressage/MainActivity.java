package com.example.projetadressage;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;

import bla.HashtableAssociation;


public class MainActivity extends AppCompatActivity {

    EditText champPrenom, champNom, champAdresse, champZip;
    Spinner spinnerCapitale, spinnerEtat;

    Button bouton;
    ArrayList<String> listeEtat;
    ArrayList<String> listeCapitale;
    ArrayList<Inscrit> listElecteur;
    HashtableAssociation h = new HashtableAssociation();
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        champPrenom = findViewById(R.id.champPrenom);
        champNom= findViewById(R.id.champNom);
        champAdresse = findViewById(R.id.champAdresse);
        champZip = findViewById(R.id.champZip);

        spinnerCapitale = findViewById(R.id.spinnerCapitale);
        spinnerEtat = findViewById(R.id.spinnerEtat);

        bouton = findViewById(R.id.boutonInscrire);
        listeEtat = new ArrayList<>();
        listeCapitale = new ArrayList<>();
        listElecteur = new ArrayList<>();

        listeEtat.addAll(h.values());
        Collections.sort(listeEtat);
        listeCapitale.addAll(h.keySet());
        Collections.sort(listeCapitale);


        // remplir les spinner à l'aide de la Hashtable

        ArrayAdapter adapterCapitale = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listeCapitale);
        spinnerCapitale.setAdapter(adapterCapitale);

        ArrayAdapter adapterEtat = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listeEtat);
        spinnerEtat.setAdapter(adapterEtat);

        ec = new Ecouteur();
        bouton.setOnClickListener(ec);
    }
    private class Ecouteur implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
        String nom,prenom,adresse,zip ,capitale,etat;
        nom = champNom.getText().toString();
        prenom = champPrenom.getText().toString();
        adresse = champAdresse.getText().toString();
        zip = champZip.getText().toString();
        capitale= spinnerCapitale.getSelectedItem().toString();
        etat = spinnerEtat.getSelectedItem().toString();

        try{
            Inscrit i = new Inscrit(nom,prenom,adresse, zip,capitale,etat);
            creerAlertDialog("electeur inscit","message");
            listElecteur.add(i);


        } catch (AdresseException e) {
            creerAlertDialog(e.getMessage(),"Erreur");
            champNom.requestFocus();
            if(e.getValeurEronne().equals("nom")){
                champNom.requestFocus();
            }
            else  if(e.getValeurEronne().equals("prenom")){
                champPrenom.requestFocus();
            }
            else if(e.getValeurEronne().equals("adresse")){
                champAdresse.requestFocus();
            }
            else if(e.getValeurEronne().equals("codeZip")){
                champZip.requestFocus();
            }

        }

        }
    }
    public void creerAlertDialog(String message, String titre){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(message).setTitle(titre);
        AlertDialog d = builder.create();
        d.show();
    }
}