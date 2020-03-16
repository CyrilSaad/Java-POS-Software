package com.company;

import java.io.Serializable;


public class Article implements Serializable, Comparable {
	
	static int noSerie = 0;
	int noArticle;
	String nomArticle;
	int qteStock;
	double prixVenteParUnite;
	double coutAchatParUnite;
	double tauxProfit;
	Article(String name, Double profit) {
		noSerie++;
		this.nomArticle = name;
		this.tauxProfit = tauxProfit;
		noArticle = noSerie;
	}
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
