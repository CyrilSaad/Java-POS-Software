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

@Override
public String toString() {
	return nomCategorie + "(" + noCategorie + ")";
}

}
