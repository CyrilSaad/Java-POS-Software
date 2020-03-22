package com.company;

import java.io.Serializable;


public class Article implements Serializable, Comparable {
	
	static int noSerie = Files.getArticle().size();
	int noArticle;
	String nomArticle;
	int qteStock =0;
	double prixVenteParUnite;
	double coutAchatParUnite;
	double tauxProfit;
	Categorie categorie;
	Article(String name, Double profit, Categorie cat) {
		noSerie++;
		this.nomArticle = name;
		this.tauxProfit = tauxProfit;
		prixVenteParUnite = (1+tauxProfit)*coutAchatParUnite;
		this.categorie = cat;
		noArticle = noSerie;
	}
	@Override
	public String toString() {
		return nomArticle + "(" +noArticle + ")";
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void entreeStock( int qty, double price) {
		qteStock += qty;
		prixVenteParUnite = (1+tauxProfit)*coutAchatParUnite;
		Files.updateArticle(noArticle, qteStock, prixVenteParUnite);
	}
	public void sortieStock(int qty, double montant) {
		// TODO Auto-generated method stub

		qteStock -= qty;
		Files.updateArticle(noArticle, qteStock, prixVenteParUnite);
	
	}
	
	

}
