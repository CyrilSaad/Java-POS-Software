package com.company;

import java.util.Date;

public class Paiement extends TransactionFournisseur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Paiement(Date d, double m, Fournisseur f) {
		super(d, m, f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void MiseAJourCompte(double montant) {
		Files.crediterFournisseur(fournisseur, montant);
		
	}

//	
}
