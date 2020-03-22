package com.company;

import java.util.Date;

abstract class TransactionClient extends Transaction {
	
	Client client;
	TransactionClient(Date d, double m,Client c) {
		super(d, m);
		client = c;
			
		}
	abstract void MiseAJourCompte(double montant);
	
	@Override
	public String toString() {
		return "" + super.toString() + "	Client:" +  client.nomCompte + "(" + client.noCompte + ")";
	}
}

