package com.company;

import java.time.LocalDate;

abstract class TransactionClient extends Transaction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Client client;
	TransactionClient(LocalDate d, double m,Client c) {
		super(d, m);
		client = c;
			
		}
	abstract void MiseAJourCompte(double montant);
	
	public String toString() {
		return "" + super.toString() + "\n Client:" +  client;
	}
}

