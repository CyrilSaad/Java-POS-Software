package com.company;

import java.io.Serializable;
public class Categorie implements Serializable {

static int noSerie = Files.getCategories().size();
int noCategorie;
String nomCategorie;
public Categorie(String name) {
	// TODO Auto-generated constructor stub
	noSerie++;
	noCategorie = noSerie;
	nomCategorie = name;
}

public String toString() {
	return "Nom Catégorie: " + nomCategorie + "  No. Catégorie: " + noCategorie;
}

}
