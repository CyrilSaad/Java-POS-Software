package com.company;

public class DetailAchat {
	Achat achat;
	Article article;
	int quantite;
	double cout;
	
		DetailAchat(Article article, Achat achat, int qty) {
			this.achat = achat;
			this.article = article;
			this.quantite = qty;
			cout = achat.montant;
		}
	double calculerMontant () {		return quantite * cout;
	}
	public int compareTo(Object o) {
		int comp = 0;
		DetailAchat da = (DetailAchat) o;
		if(this.calculerMontant() > da.calculerMontant()) comp = 1;
		else if(this.calculerMontant() < da.calculerMontant()) comp = -1;
		return comp;
	}
	public String toString() {
		return "Achat:\t:" + achat + "\nArticle:\t" + article + "\nQuantité:\t" + quantite;
	}
	
}
