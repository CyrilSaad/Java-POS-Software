package com.company;

import java.time.LocalDate;

abstract class TransactionClient extends Transaction {
	
	Client client;
	TransactionClient(String d, double m,Client c) {
		super(d, m);
		client = c;
			
		}
	abstract void MiseAJourCompte(double montant);
	
	public String toString() {
		return "" + super.toString() + "\n Client:" +  client;
	}
}

