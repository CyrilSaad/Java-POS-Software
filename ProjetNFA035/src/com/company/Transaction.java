package com.company;
import java.util.*;

import java.io.*;
public abstract class Transaction implements Serializable, Comparable<Transaction> {
	static int noSerie = TransactionFiles.getTransactionsSize();
	int noTransaction =0;
	String description;
	Date dateTransaction ;
	protected double montant = 0.0d;
	TypePaie typePaie = TypePaie.CASH;
	enum TypePaie implements Serializable {
		CASH,
		CHEQUE,
		TRANSFERT
	};
	//TODO: set mode paie
	Transaction(Date d, double m) {
		noSerie ++;
		dateTransaction = d;	
		montant = m;
		noTransaction = noSerie;
	}
	protected void setTypePaie(TypePaie e) {
		typePaie = e;}
	
	
	@Override
	public int compareTo(Transaction t) {
		int i = description.compareTo(t.description);
	    if (i != 0) return i;

	    i = dateTransaction.compareTo(t.dateTransaction);
	    if (i != 0) return i;

	    return noTransaction - t.noTransaction;
	
 }
	 
	
	@Override
	public String toString() {
		return 
				" ID: " + noTransaction +
				" Date Issue: " + dateTransaction + 
				" total: " + montant;
	}
}
