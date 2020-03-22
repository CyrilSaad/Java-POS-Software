package com.company;

import java.util.Date;

public class Recu extends TransactionClient {

Recu(Date d, double m, Client c) {
		super(d, m, c);
		// TODO Auto-generated constructor stub
	}

static final long serialVersionUID = 1L;

	@Override
	void MiseAJourCompte(double montant) {
		Files.crediterClient(client, montant);

	}
	


}
