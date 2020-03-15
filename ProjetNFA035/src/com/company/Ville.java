package com.company;

import java.util.ArrayList;

public class Ville implements Comparable<Ville> {
	static int noSerie = 0;
	int noVille;
	String nomVille;
	static String villeNoms [] = { "Beyrouth", "Tripoli", "Sidon", "Tyr", "Nabatieh", "Jounieh",
			"Zahlé", "Baabda", "Zgharta", "Baalbek", "Aley", "Byblos", "Rayak", "Marjayoun", 
			"Amioun", "Jezzine", "Rachaya", "Hermel", "Batroun", "Joub Jenin" };
	 static Ville villes [] = new Ville[villeNoms.length];
	Ville(String nom, int noVille) {
		noSerie++;
		noVille = noSerie;
		for(int i=0; i<villeNoms.length; i++) {
			villes[i] = new Ville(villeNoms[i], noVille);	
		}
	}
	static Ville findVille(String s) {
		Ville v = null;
		for(int i=0; i<villeNoms.length; i++) {
			if(villeNoms[i] == s) v = villes[i];
		}
		return v;
	}
	public int compareTo(Ville v) {
		if(nomVille.compareTo(v.nomVille) == 1) return 1;
		else if(nomVille.compareTo(v.nomVille) == -1) return -1;
		else return 0;
	}

}
