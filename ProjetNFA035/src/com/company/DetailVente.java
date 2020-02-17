package com.company;

public class DetailVente {
	Vente vente;
	Article article;
	int quantite;
	DetailVente(Article a, Vente v, int quantity) {
		article = a;
		vente = v;
		quantite = quantity;
	}
	
	
	public String toString() {
		return "Vente:\t:" + vente + "\nArticle:\t" + article + "\nQuantité:\t" + quantite;
	}
	
	double calculerMontant(){
		return quantite* article.prixVenteParUnite;
	}
	
	public int compareTo(Object o) {
		int comp = 0;
		DetailAchat da = (DetailAchat) o;
		if(this.calculerMontant() > da.calculerMontant()) comp = 1;
		else if(this.calculerMontant() < da.calculerMontant()) comp = -1;
		return comp;
	}
}
