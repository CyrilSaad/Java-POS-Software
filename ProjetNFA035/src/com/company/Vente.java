package com.company;

import java.util.Date;

public class Vente extends TransactionClient {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Vente(Date d, double m,Client c) 
		{super(d, m, c);}

	@Override
	 public void MiseAJourCompte(double d) {
		Files.debiterClient(client, d);
		
	}
	
	double calculerTotalVente() {
		return montant;
		
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n Nouveau solde: " + client.solde; 
	}
	

}
