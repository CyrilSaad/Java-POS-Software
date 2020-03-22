package com.company;
import java.io.*;


public abstract class Compte implements Serializable, Comparable<Compte> {
	enum EtatCompte implements Serializable {
		ACTIF,
		FERME,
		SUSPENDU
	}
	
	static int noSerie = Files.ComptesSize();
	 int noCompte;
	String nomCompte;
	Ville ville;
	double solde = 0.0d;
	EtatCompte etat = EtatCompte.ACTIF;
	
	Compte(String s, Ville v){
		nomCompte = s;
		ville 	= v;
		noSerie++;
		noCompte = noSerie;
	}
	
	public void setEtat(EtatCompte e) {
		etat = e;}
	
	abstract void debiter(double d);
	abstract void crediter(double d);
	
	@Override
	public int compareTo (Compte c) {
		int comp = 0 ;
		if(nomCompte.compareTo(c.nomCompte) == 1) comp = 1;
		else if ( nomCompte.compareTo(c.nomCompte) == -1) comp = -1;
		return comp;
	}
	
	@Override
	public String toString() {
		return "Nom Compte: " + nomCompte + "  Numéro Compte: " + noCompte +  "  Solde: " + solde +  "  Etat du compte: " + etat;
	}
}
