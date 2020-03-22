package com.company;

import java.util.Date;

public class Achat extends TransactionFournisseur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Achat(Date d, double m, Fournisseur f) {
		super(d, m, f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void MiseAJourCompte(double montant) {
		Files.debiterFournisseur(fournisseur, montant);
		
	}

	@Override
	public String toString() {
		return super.toString() + "\n Nouveau solde: " + fournisseur.solde; 
	}

	@Override
	public int compareTo(Transaction arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
