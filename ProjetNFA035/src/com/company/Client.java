package com.company;

public class Client extends Compte{
	Client(String s, Ville v) {
		super(s,v);
		noCompte = noSerie;	
	}
	@Override
	public void debiter(double montant) {
		solde -= montant;
	}
	
	@Override
	public void crediter(double montant) {
		solde +=montant;
	}
}
