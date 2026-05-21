package com.kawtar.tp2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Grille {
    // la grillle un tableau double dimension
    private Lettre [][]lettres ;
    private Random random;
    private HashMap <Character, Lettre> hm = new HashMap<>();
    private int totalPoint;
    public Grille() {
        lettres = new Lettre[4][4];
        this.random = new Random();
        hm.put('a',new Lettre('a',1,5));
        hm.put('b',new Lettre('b',1,3));
        hm.put('c',new Lettre('c',1,3));
        hm.put('d',new Lettre('d',1,2));
        hm.put('e',new Lettre('e',1,5));
        hm.put('f',new Lettre('f',2,2));
        hm.put('g',new Lettre('g',2,2));
        hm.put('h',new Lettre('h',1,2));
        hm.put('i',new Lettre('i',1,4));
        hm.put('j',new Lettre('j',2,1));
        hm.put('k',new Lettre('k',2,2));
        hm.put('l',new Lettre('l',2,2));
        hm.put('m',new Lettre('m',3,3));
        hm.put('n',new Lettre('n',3,2));
        hm.put('o',new Lettre('o',1,4));
        hm.put('p',new Lettre('p',4,1));
        hm.put('q',new Lettre('q',4,1));
        hm.put('r',new Lettre('r',2,2));
        hm.put('s',new Lettre('s',5,3));
        hm.put('t',new Lettre('t',2,2));
        hm.put('u',new Lettre('u',2,3));
        hm.put('v',new Lettre('v',4,1));
        hm.put('w',new Lettre('w',5,1));
        hm.put('x',new Lettre('x',5,1));
        hm.put('y',new Lettre('y',5,1));
        hm.put('z',new Lettre('z',5,1));
        this.totalPoint = 0;
    }

    // methode pour creer la grille

    public void creerGrille(){
       // Lettre[][]  grille = new Lettre[4][4];
        for (int i = 0; i < lettres.length;i++){ // ligne

            for (int j =0; j < lettres[i].length; j++) { // colonne

                lettres[i][j] = choisirRandom();
                System.out.println(lettres[i][j].getAlphabet());

            }
        }
    }
    // methode pour choisir aléatoire lettre
    public Lettre choisirRandom(){
        int poidsTotal = 59;
        int somme =0;
        int nombreRandom = random.nextInt(poidsTotal);
        for(Lettre lettre : hm.values()){ // parcourir
            somme += lettre.getPoids();
            if (nombreRandom < somme)
            {  System.out.println(lettre);
            return new Lettre(lettre.getAlphabet(),lettre.getValeur(), lettre.getPoids());
            }
        }
        return null;
    };
    // methode pour generer multiplicateur dependamenet
    public void genererMultiplicateur(){
        ArrayList<Integer> pos = new ArrayList<>() ; // une arratluist de position de la grille
        for (int i = 0; i<= 15; i++){
            pos.add(i); // on ajoute les 16 cases
        }
        Collections.shuffle(pos); // melanger

        // double
        for (int i = 0; i< 2; i++){
            int  position =pos.get(i);
            int ligne = position /4;
            int colonne = position %4;
            lettres[ligne][colonne].setMultiplicateur(2);


        }
        //triple
       int posTriple = pos.get(2);
        lettres[posTriple/4][posTriple%4].setMultiplicateur(3);

       // double mot
        int posMotDouble = pos.get(3);
        lettres[posMotDouble / 4][posMotDouble % 4].setMultiplicateurMot(2);
    }

    public Lettre[][] getLettres() {
        return lettres;
    }
}
