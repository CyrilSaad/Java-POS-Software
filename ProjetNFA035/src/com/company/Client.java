package com.company;

public class Client extends Compte{
	Client(String s, Ville v) {
		super(s,v);
	}
	public void debiter(double montant) {
		solde -= montant;
	}
	
	public void crediter(double montant) {
		solde +=montant;
	}
}
