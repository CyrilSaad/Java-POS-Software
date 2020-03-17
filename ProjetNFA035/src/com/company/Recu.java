package com.company;

import java.time.LocalDate;

public class Recu extends TransactionClient {

Recu(String d, double m, Client c) {
		super(d, m, c);
		// TODO Auto-generated constructor stub
	}

static final long serialVersionUID = 1L;

	@Override
	void MiseAJourCompte(double montant) {
		client.crediter(montant);

	}
	
	 public String toString() {
		return super.toString();
	}


}
