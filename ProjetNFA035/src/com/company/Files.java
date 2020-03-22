package com.company;

import java.io.*;
import java.util.*;

public class Files implements Serializable {
	static HashMap<Integer, Client> compteMap = new HashMap<Integer, Client>();
	static HashMap<Integer, Fournisseur> compteFMap = new HashMap<Integer, Fournisseur>();
	static HashMap categorieMap = new HashMap();
	static HashMap articleMap = new HashMap();
	private static HashMap transactionMap = new HashMap();

	public static HashMap<Integer, Client> getComptes() {
		// TODO Auto-generated method stub
		try {
			FileInputStream in = new FileInputStream("C:\\projetNFA035\\clients.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			compteMap = ((HashMap<Integer, Client>) ois.readObject());
			ois.close();
		} catch (Exception e) {
		}
		return compteMap;
	}

	public static HashMap<Integer, Fournisseur> getComptesF() {
		try {
			FileInputStream in = new FileInputStream("C:\\projetNFA035\\fournisseurs.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			compteFMap = ((HashMap) ois.readObject());
			ois.close();
		} catch (Exception e) {
		}
		return compteFMap;
	}

	public static int ComptesSize() {
		HashMap map1 = getComptes();
		HashMap map2 = getComptesF();
		map1.putAll(map2);
		return map1.size();
	}

	public static HashMap getCategories() {
		// TODO Auto-generated method stub
		try {
			FileInputStream in = new FileInputStream("C:\\projetNFA035\\categories.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			categorieMap = ((HashMap) ois.readObject());
			ois.close();
		} catch (Exception e) {
		}
		return categorieMap;
	}

	public static HashMap getArticle() {
		// TODO Auto-generated method stub
		try {
			FileInputStream in = new FileInputStream("C:\\projetNFA035\\articles.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			articleMap = ((HashMap) ois.readObject());
		} catch (Exception e) {
		}
		return articleMap;
	}

	static void createClient(int noClient, Client c) {

		HashMap<Integer, Client> tempClient = new HashMap<Integer, Client>();
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\clients.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			compteMap.putAll(tempClient);
			oos.writeObject(compteMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {
		}
		getComptes();
		tempClient.put(noClient, c);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\clients.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			compteMap.putAll(tempClient);
			oos.writeObject(compteMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {
		}
	}

	static void createFournisseur(int noF, Fournisseur F) {

		HashMap tempFournisseur = new HashMap();
		tempFournisseur.put(noF, F);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\fournisseurs.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(compteFMap);
			oos.flush();
			oos.close();
		} catch (Exception e) {
		}
		try {

			FileInputStream in = new FileInputStream("C:\\projetNFA035\\fournisseurs.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			compteFMap = ((HashMap) ois.readObject());
			compteFMap.put(noF, F);
			ois.close();
		} catch (Exception e) {
		}
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\fournisseurs.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(compteFMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {
		}

	}

	static void createArticle(int noA, Article a) {

		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\articles.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(articleMap);
			oos.flush();
			oos.close();
		} catch (Exception e) {
		}
		getArticle();
		articleMap.put(noA, a);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\articles.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(articleMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {
		}

	}

	public static void debiterClient(Client c, double d) {

		compteMap = Files.getComptes();
		Client toModify = compteMap.get(c.noCompte);
		toModify.debiter(d);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\clients.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(compteMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {
		}

	}

	public static void createCategorie(int noCategorie, Categorie c) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\categories.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(categorieMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {
		}

		getCategories();
		categorieMap.put(noCategorie, c);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\categories.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(categorieMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {
			
		}
	}

	public static void crediterClient(Client c, double montant) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		compteMap = Files.getComptes();
		Client toModify = compteMap.get(c.noCompte);
		toModify.crediter(montant);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\clients.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(compteMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {
		}

	}

	public static void crediterFournisseur(Fournisseur f, double montant) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		compteFMap = Files.getComptesF();
		Fournisseur toModify = compteFMap.get(f.noCompte);
		toModify.crediter(montant);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\fournisseurs.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(compteFMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {
		}

	}

	public static void debiterFournisseur(Fournisseur f, double montant) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		compteFMap = Files.getComptesF();
		Fournisseur toModify = compteFMap.get(f.noCompte);
		toModify.debiter(montant);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\fournisseurs.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(compteFMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {
		}

	}

	public static void updateArticle(int noArticle, int qteStock, double prixVenteParUnite) {

		articleMap = Files.getArticle();
		Article toModify = (Article) articleMap.get(noArticle);
		toModify.qteStock = qteStock;
		toModify.prixVenteParUnite = prixVenteParUnite;
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\articles.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(articleMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {
		}
	
	}

	public static void updateClient(int noClient, String nom, Ville ville) {
		compteMap = getComptes();
		Client toModify = compteMap.get(noClient);
		toModify.nomCompte = nom;
		toModify.ville = ville;
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\clients.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(compteMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {	}
	}
	
	public static void updateFournisseur(int noClient, String nom, Ville ville) {
		compteFMap = getComptesF();
		Fournisseur toModify = compteFMap.get(noClient);
		toModify.nomCompte = nom;
		toModify.ville = ville;
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\fournisseurs.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(compteFMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {	}
	}

	public static void updateCategorie(int noC, String changedName) {
		// TODO Auto-generated method stub

		categorieMap = getCategories();
		Categorie toModify = (Categorie) categorieMap.get(noC);
		toModify.nomCategorie = changedName;
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\categories.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(categorieMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {	}
	
		
	}

	
	}

