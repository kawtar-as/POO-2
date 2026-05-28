package com.kawtar.tp2;

import java.util.ArrayList;

public class Mot {
    private ArrayList<Lettre> lettres;

    public Mot() {
        lettres = new ArrayList<>();
    }


    // ajouter lettre
    public void ajouterLettres(Lettre l){
        this.lettres.add(l);
    }
    public int sommeValeur( ){
        int somme = 0, bonusMot = 1;

       for(Lettre l : lettres){
           int valeurLettre = l.getValeur(); // valeur de la lettre
            // verification des multiplicateurs
           if(l.getMultiplicateur() > 1)
           {
               somme +=  valeurLettre *  l.getMultiplicateur();
           }
           else{
               somme +=valeurLettre;
           }

           if(l.getMultiplicateurMot() > 1){
               bonusMot = l.getMultiplicateurMot();
           }
       }

       return somme * bonusMot ;
    }
}
