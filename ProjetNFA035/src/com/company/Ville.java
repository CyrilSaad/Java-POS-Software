package com.company;

public class Ville implements Comparable {
	static int noSerie = 0;
	int noVille;
	String nomVille;
	Ville(String nom) {
		nomVille = nom;
		noSerie++;
		noVille = noSerie;
	}
	
	public int compareTo(Object o) {
		Ville v = (Ville) o;
		if(nomVille.compareTo(v.nomVille) == 1) return 1;
		else if(nomVille.compareTo(v.nomVille) == -1) return -1;
		else return 0;
	}

}
