package com.company;

public class Client extends Compte{
	double solde;
	Client(String s, Ville v) {
		super(s,v);
		noCompte = noSerie;	
	}
	public void debiter(double montant) {
		solde -= montant;
	}
	
	public void crediter(double montant) {
		solde +=montant;
	}
}
