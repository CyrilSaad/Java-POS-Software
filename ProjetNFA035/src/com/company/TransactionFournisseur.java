package com.company;

import java.util.Date;

abstract class TransactionFournisseur extends Transaction {
	Fournisseur fournisseur;
	TransactionFournisseur(Date d, double m,Fournisseur f) {
		super(d,m);
		fournisseur = f;
	}
	public abstract void MiseAJourCompte(double montant);
	@Override
	public String toString() {
		return "" + super.toString() + "   Fournisseur:" +   fournisseur.nomCompte + "(" + fournisseur.noCompte + ")";
		}
	}

