package com.company;

import java.time.LocalDate;

public class Achat extends TransactionFournisseur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Achat(LocalDate d, double m, Fournisseur f) {
		super(d, m, f);
		// TODO Auto-generated constructor stub
	}

	public void MiseAJourCompte(double montant) {
		fournisseur.debiter(montant);
		
	}

	public String toString() {
		return super.toString() + "\n Nouveau solde: " + fournisseur.solde; 
	}

}