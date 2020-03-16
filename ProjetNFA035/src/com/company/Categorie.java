package com.company;

import java.io.Serializable;
class Categorie implements Serializable {
/**
	 * 
	 */
//	private static final long serialVersionUID = -7162658196037428571L;
static int noSerie;
int noCategorie;
private String nomCategorie;

public Categorie(String name) {
	// TODO Auto-generated constructor stub
	noSerie++;
	noCategorie = noSerie;
	nomCategorie = name;
}



}
