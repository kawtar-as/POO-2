package com.kawtar.annexe13;

public class Evaluation {

    private String nom;
    private String microbrasserie;
    private double rate;

    public Evaluation(String nom, String microbrasserie, double rating) {
        this.nom = nom;
        this.microbrasserie = microbrasserie;
        this.rate = rate;
    }

    public String getNom() {
        return nom;
    }

    public String getMicrobrasserie() {
        return microbrasserie;
    }

    public double getRate() {
        return rate;
    }
}
