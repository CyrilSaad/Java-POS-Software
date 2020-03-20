package com.company;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class RapportFilter {
	public static Vente[] rapportVente(Client c, String debutStr, String finStr) throws ParseException {
		ArrayList<Vente> ventes = Filter.findClientVentes(c);
		Vente filteredVentes[] = new Vente[ventes.size()];
		Date date = ventes.get(0).dateTransaction;
		Date debut = Pattern.format.parse(debutStr);
		Date fin = Pattern.format.parse(finStr);
		boolean notBefore = false;
		boolean notAfter = false;


		return filteredVentes;
	}
}
