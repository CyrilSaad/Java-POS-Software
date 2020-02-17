package com.company;
import java.util.*;
import java.io.*;
import java.time.*;
public abstract class Transaction implements Serializable, Comparable<Transaction> {
	static int noSerie =0;
	int noTransaction =0;
	String description;
	LocalDate dateTransaction ;
	protected double montant = 0.0d;
	
	enum TypePaie {
		CASH,
		CHEQUE,
		TRANSFERT
	};
	//TODO: set mode paie
	Transaction(LocalDate d, double m) {
		noSerie ++;
		dateTransaction = d;	
		montant = m;
		noTransaction = noSerie;
	}
	
	public int compareTo(Object o) {
	 	int comp = 0;
	 Transaction t = (Transaction) o;
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
