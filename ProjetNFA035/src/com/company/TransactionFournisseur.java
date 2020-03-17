package com.company;

import java.time.LocalDate;

abstract class TransactionFournisseur extends Transaction {
	Fournisseur fournisseur;
	TransactionFournisseur(String d, double m,Fournisseur f) {
		super(d,m);
		fournisseur = f;
	}
	public abstract void MiseAJourCompte(double montant);
	public String toString() {
		return "" + super.toString() + "\n Fournisseur:" +  fournisseur;
		}
	}

