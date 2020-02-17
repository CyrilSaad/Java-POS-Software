package com.company;

import java.time.LocalDate;

public class Paiement extends TransactionFournisseur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Paiement(LocalDate d, double m, Fournisseur f) {
		super(d, m, f);
		// TODO Auto-generated constructor stub
	}

	public void MiseAJourCompte(double montant) {
		fournisseur.crediter(montant);
		
	}

	public String toString() {
		return super.toString() + "\n Nouveau solde: " + fournisseur.solde; 
	}

}
