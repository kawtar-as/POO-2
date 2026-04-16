package com.keyv.annexe12;

public class Inventaire {
    private String nom;
    private String origine;
    private String invention;
    private int annee;

    public Inventaire(String nom, String origine, String invention, int annee) {
        this.nom = nom;
        this.origine = origine;
        this.invention = invention;
        this.annee = annee;
    }

    public String getNom() {
        return nom;
    }

    public String getOrigine() {
        return origine;
    }

    public String getInvention() {
        return invention;
    }

    public int getAnnee() {
        return annee;
    }
}
