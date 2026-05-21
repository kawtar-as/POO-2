package com.kawtar.tp2;

import java.util.ArrayList;

public class Mot {
    private ArrayList<Lettre> lettres;

    public Mot() {
        lettres = new ArrayList<>();
    }
    // methode calculer valeur qui fait la somme des valeur des lettres

    // ajouter lettre
    public void ajouterLettres(Lettre l){
        this.lettres.add(l);
    }
    public int sommeValeur( ){
        int somme = 0, bonus = 1;

       for(Lettre l : lettres){
           somme += l.getValeur();

           if(l.getMultiplicateurMot() > 1)
           {
               bonus = l.getMultiplicateurMot();
           }else if(l.getMultiplicateur() > 1){
               bonus = l.getMultiplicateur();
           }
       }
       return  somme * bonus ;
    }
}
