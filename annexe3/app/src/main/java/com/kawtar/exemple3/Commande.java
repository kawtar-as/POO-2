package com.kawtar.exemple3;

import java.util.Vector;

public class Commande {

    private Vector<Produit> listeCommande;

    public Commande ( )
    {
        listeCommande = new Vector();
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

    public double taxes(double montant)
    {
        double taxes = 0;
  
	// compléter : montant des taxes sur le total de la commande
         montant=0;
        // tps sur le montant avant taxes ( 5% )
        double tps = montant *5/100;
        //tvq sur le montant avant taxes ( 9.975% )
        double tvq = montant * 9.975 /100;
        // taxes total = tps + tvq
        taxes = tps + tvq;
	

        return taxes;
    }

    public double grandTotal(){
	
	double grTotal = 0;
	
	// compléter
        grTotal+=total()  + taxes(total());

	return grTotal;
	

    }
}
