package com.kawtar.tp2;

public class Lettre {
    private char alphabet;
    private int valeur;
    private int multiplicateur , multiplicateurMot , poids;

    public Lettre(char alphabet, int valeur , int poids) {
        this.alphabet = alphabet;
        this.valeur = valeur;
        this.multiplicateur =1;
        this.multiplicateurMot = 1;
        this.poids = poids;
    }

    public char getAlphabet() {
        return alphabet;
    }

    public int getValeur() {
        return valeur;
    }

    public int getMultiplicateur() {
        return multiplicateur;
    }

    public int getMultiplicateurMot() {
        return multiplicateurMot;
    }


    public int getPoids() {
        return poids;
    }


}
