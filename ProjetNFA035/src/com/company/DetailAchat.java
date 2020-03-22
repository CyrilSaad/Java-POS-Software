package com.company;

import java.io.Serializable;

public class DetailAchat implements Serializable, Comparable<DetailAchat>{
	Article article;
	Achat achat;
	int quantite;
		DetailAchat(Article article, Achat achat, int qty) {
			this.article = article;
			this.achat = achat;
			this.quantite = qty;
		}
	double calculerMontant () {		return quantite * article.coutAchatParUnite;
	}
	@Override
	public int compareTo(DetailAchat da) {
		int comp = 0;
		if(this.calculerMontant() > da.calculerMontant()) comp = 1;
		else if(this.calculerMontant() < da.calculerMontant()) comp = -1;
		return comp;
	}
	@Override
	public String toString() {
		return "Achat:\t:" + achat + "\nArticle:\t" + article + "\nQuantité:\t" + quantite;
	}
	
}
