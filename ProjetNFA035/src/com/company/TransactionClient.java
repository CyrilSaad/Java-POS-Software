package com.company;

import java.time.LocalDate;
import java.util.Date;

abstract class TransactionClient extends Transaction {
	
	Client client;
	TransactionClient(Date d, double m,Client c) {
		super(d, m);
		client = c;
			
		}
	abstract void MiseAJourCompte(double montant);
	
	public String toString() {
		return "" + super.toString() + "	Client:" +  client.nomCompte + "(" + client.noCompte + ")";
	}
}

