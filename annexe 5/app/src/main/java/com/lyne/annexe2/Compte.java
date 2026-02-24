package com.lyne.annexe2;

public class Compte {
    private String nom ;
    private double solde ;

    public Compte(String nom, double solde) {
        this.nom = nom;
        this.solde = solde;
    }

    public String getNom() {
        return nom;
    }

    public double getSolde() {
        return solde;
    }
    //  methode pour diminuer le solde a un trnsfert
    public boolean transfert (int montant){
        if(solde >= montant) {
            solde -= montant;
            return true ;
        }
        else
            return false;
    }
}
