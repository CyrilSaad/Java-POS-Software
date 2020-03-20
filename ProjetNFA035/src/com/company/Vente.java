package com.company;

import java.time.LocalDate;
import java.util.Date;

public class Vente extends TransactionClient {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Vente(Date d, double m,Client c) 
		{super(d, m, c);}

	@Override
	 void MiseAJourCompte(double d) {
		client.debiter(d);
		
	}
	
	
	
	
	

}
