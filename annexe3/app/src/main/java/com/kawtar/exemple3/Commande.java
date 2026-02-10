package com.kawtar.exemple3;

import java.util.ArrayList;
import java.util.Vector;

public class Commande {

    private ArrayList<Produit> listeCommande;

    public Commande ( )
    {
        listeCommande = new ArrayList();
    }

    public void ajouterProduit ( Produit p )
    {
        listeCommande.add(p);
    }

    public double total ()
    {
	double total =0;
      // compléter : total de la commande
        for(Produit p : listeCommande ){
            total += p.getPrixUnitaire() * p.getQte();
        }
	return total;
    }

    public double taxes()
    {
        double taxes = total();
  
	// compléter : montant des taxes sur le total de la commande

        // tps sur le montant avant taxes ( 5% )
        double tps = taxes *0.05;
        //tvq sur le montant avant taxes ( 9.975% )
        double tvq = taxes *0.09975;
        // taxes total = tps + tvq
        taxes = tps + tvq;
	

        return tps +tvq;
    }

    public double grandTotal(){
	
	double grTotal = 0;
	
	// compléter
        grTotal+=total()  + taxes();

	return grTotal;
	

    }
}
