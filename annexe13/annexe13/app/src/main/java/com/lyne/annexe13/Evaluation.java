package com.lyne.annexe13;

public class Evaluation {
    private String nom;
    private String microbrasserie;
    private double etoile;

    public Evaluation(String nom, String microbrasserie, double etoile) {
        this.nom = nom;
        this.microbrasserie = microbrasserie;
        this.etoile = etoile;
    }

    public String getNom() {
        return nom;
    }

    public String getMicrobrasserie() {
        return microbrasserie;
    }

    public double getEtoile() {
        return etoile;
    }
}
