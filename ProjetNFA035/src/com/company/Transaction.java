package com.company;
import java.util.*;

import com.company.Compte.EtatCompte;

import java.io.*;
import java.time.*;
public abstract class Transaction implements Serializable, Comparable<Transaction> {
	static int noSerie = TransactionFiles.getTransactionsSize();
	int noTransaction =0;
	String description;
	String dateTransaction ;
	protected double montant = 0.0d;
	TypePaie typePaie = TypePaie.CASH;
	enum TypePaie {
		CASH,
		CHEQUE,
		TRANSFERT
	};
	//TODO: set mode paie
	Transaction(String d, double m) {
		noSerie ++;
		dateTransaction = d;	
		montant = m;
		noTransaction = noSerie;
	}
	protected void setTypePaie(TypePaie e) {
		typePaie = e;}
	
	
	public int compareTo(Transaction t) {
	 	int comp = 0;
	 if(description.compareTo(t.description) == 1)  comp = 1;
	 else if (dateTransaction.compareTo(t.dateTransaction) == 1)  comp = 1;
	 else if(noTransaction > t.noTransaction)  comp =1;	 
	return comp;
	
 }
	 
	
	public String toString() {
		return 
				"Date de la transaction: \t" + dateTransaction + 
				"\n Numéro de la transaction:" + noTransaction +
				"\n Montant total:\t" + montant;
	}
}
