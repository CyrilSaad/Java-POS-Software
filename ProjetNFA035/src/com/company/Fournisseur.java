package com.company;



public class Fournisseur extends Compte {

	Fournisseur(String s, Ville v) {
		super(s, v);
	}

	@Override
	public void debiter(double montant) {
			solde += montant;
	}

	@Override
	public void crediter(double montant) {
		solde -= montant;
	}


	

}
