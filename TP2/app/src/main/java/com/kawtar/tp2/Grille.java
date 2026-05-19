package com.kawtar.tp2;

import java.util.HashMap;
import java.util.Random;

public class Grille {
    // la grillle un tableau double dimension
     Lettre [][]lettres ;
    Random random;
    HashMap <Character, Lettre> hm = new HashMap<>();

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

    }

    // methode pour creer la grille

    public Lettre[][] creerGrille(){
        Lettre[][]  grille = new Lettre[4][4];
        for (int i = 0; i < grille.length;i++){ // ligne

            for (int j =0; j < grille[i].length; j++){ // colonne

               grille[i][j] = choisirRandom();
                System.out.println( grille[i][j].getAlphabet());

            }
            System.out.println();
        }
        return  grille;
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
            return lettre;}
        }
        return null;
    };

}
