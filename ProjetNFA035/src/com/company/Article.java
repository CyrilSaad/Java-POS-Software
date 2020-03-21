package com.company;

import java.io.Serializable;


public class Article implements Serializable, Comparable {
	
	static int noSerie = Files.getArticle().size();
	int noArticle;
	String nomArticle;
	int qteStock;
	double prixVenteParUnite;
	double coutAchatParUnite;
	double tauxProfit;
	Categorie categorie;
	Article(String name, Double profit, Categorie cat) {
		noSerie++;
		this.nomArticle = name;
		this.tauxProfit = tauxProfit;
		this.categorie = cat;
		noArticle = noSerie;
	}
	public String toString() {
		return "Nom Article: " + nomArticle + "  No. Article: " + noArticle;
	}
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
