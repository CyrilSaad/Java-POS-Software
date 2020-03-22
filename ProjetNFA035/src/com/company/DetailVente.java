package com.company;

import java.io.Serializable;

public class DetailVente implements Serializable, Comparable<DetailVente> {
	Article article;
	Vente vente;
	int quantite;
		DetailVente(Article article, Vente Vente, int qty) {
			this.article = article;
			this.vente = Vente;
			this.quantite = qty;
		}
	double calculerMontant () {		return quantite * article.prixVenteParUnite;
	}
	@Override
	public int compareTo(DetailVente da) {
		int comp = 0;
		if(this.calculerMontant() > da.calculerMontant()) comp = 1;
		else if(this.calculerMontant() < da.calculerMontant()) comp = -1;
		return comp;
	}
	@Override
	public String toString() {
		return "Vente:\t:" + vente + "\nArticle:\t" + article + "\nQuantité:\t" + quantite;
	}
	
}
