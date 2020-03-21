package com.company;

import java.util.*;
import javax.swing.*;

public class Filter {
	public static Client getClient(String s) {
		int noClient = Integer.parseInt(s.substring(s.indexOf("(")+1, s.indexOf(")")));
		Object[] arr= Files.getComptes().values().toArray();
		Client c = null;
		Client[] clientData = (Client[]) new Client[arr.length];
		
		for(int i=0; i<clientData.length; i++) {
			clientData[i] = (Client) arr[i];
			if(noClient == clientData[i].noCompte) {
				c = (Client) clientData[i];
			}
		}//endloop
		return c;
	}
	
	public static Fournisseur getFournisseur(String s) {
		int noFournisseur = Integer.parseInt(s.substring(s.indexOf("(")+1, s.indexOf(")")));
		Object[] arr= Files.getComptesF().values().toArray();
		Fournisseur f = null;
		Fournisseur[] fournisseurData = (Fournisseur[]) new Fournisseur[arr.length];
		
		for(int i=0; i<fournisseurData.length; i++) {
			fournisseurData[i] = (Fournisseur) arr[i];
			if(noFournisseur == fournisseurData[i].noCompte ) {
				f = (Fournisseur) fournisseurData[i];
			}
		}//endloop
		return f;
	}
	
	public static ArrayList findClientVentes(Client c) {
		
		Object arr[] =   TransactionFiles.getVentes().values().toArray();
		Vente data[] = new Vente[arr.length];
		ArrayList ventesClient = new ArrayList<Vente>();
		for(int i=0; i<data.length; i++) {
			data[i] = (Vente) arr[i];
			if(c.noCompte == data[i].client.noCompte ) {
				ventesClient.add(data[i]);
			}
		}//end loop
		return  ventesClient;
	}

	public static ArrayList findClientRecus(Client c) {
		
		Object arr[] =   TransactionFiles.getRecus().values().toArray();
		Recu data[] = new Recu[arr.length];
		ArrayList ventesClient = new ArrayList<Vente>();
		for(int i=0; i<data.length; i++) {
			data[i] = (Recu) arr[i];
			if(c.noCompte == data[i].client.noCompte ) {
				ventesClient.add(data[i]);
			}
		}//end loop
		return  ventesClient;
	}

	public static ArrayList findFournisseurAchats(Fournisseur f) {
		
		Object arr[] =   TransactionFiles.getAchats().values().toArray();
		Achat data[] = new Achat[arr.length];
		ArrayList achatsFournisseur = new ArrayList<Achat>();
		for(int i=0; i<data.length; i++) {
			data[i] = (Achat) arr[i];
				if(f.noCompte == data[i].fournisseur.noCompte) {
				achatsFournisseur.add(data[i]);
			}
			
			
		}//end loop
		System.out.println("haha");
		return  achatsFournisseur;
	}

public static ArrayList findFournisseurPaiements(Fournisseur f) {
		
		Object arr[] =   TransactionFiles.getPaiements().values().toArray();
		Paiement data[] = new Paiement[arr.length];
		ArrayList paiementsFournisseur = new ArrayList<Paiement>();
		for(int i=0; i<data.length; i++) {
			data[i] = (Paiement) arr[i];
				if(f.noCompte == data[i].fournisseur.noCompte) {
					paiementsFournisseur.add(data[i]);
			}
			
			
		}//end loop
		System.out.println("haha");
		return  paiementsFournisseur;
	}
	public static void setListClientVentes(JList list, JComboBox cb){

		String item = cb.getSelectedItem().toString();
		Client clientChoisi = Filter.getClient(item);
		ArrayList<Vente> ventes = Filter.findClientVentes(clientChoisi);
		DefaultListModel<Vente> listModel = new DefaultListModel<Vente>();
		for (Vente v: ventes) {
			listModel.addElement(v);
		}
		list.setModel(listModel);
	
	}
	public static void setListClient(JList list){

		Collection<Client> clients = Files.getComptes().values();
		DefaultListModel<Client> listModel = new DefaultListModel<Client>();
		for (Client c: clients) {
			listModel.addElement(c);
		}
		list.setModel(listModel);
	
	}
	
	public static void setListCategorie(JList list){

		Collection<Categorie> categories = Files.getCategories().values();
		DefaultListModel<Categorie> listModel = new DefaultListModel<Categorie>();
		for (Categorie c: categories) {
			listModel.addElement(c);
		}
		list.setModel(listModel);
	
	}
	
	public static void setListFournisseur(JList list){

		Collection<Fournisseur> fournisseurs = Files.getComptesF().values();
		DefaultListModel<Fournisseur> listModel = new DefaultListModel<Fournisseur>();
		for (Fournisseur f: fournisseurs) {
			listModel.addElement(f);
		}
		list.setModel(listModel);
	
	}
	public static ArrayList<Transaction> getClientTransactions(Client clientChoisi){
		ArrayList<Transaction> ventes = Filter.findClientVentes(clientChoisi);
		ArrayList<Transaction> recus = Filter.findClientRecus(clientChoisi);
		ventes.addAll(recus);
		return ventes;
	}

	public static ArrayList<Transaction> getFournisseurTransactions(Fournisseur fournChoisi){
		ArrayList<Transaction> achats = Filter.findFournisseurAchats(fournChoisi);
		ArrayList<Transaction> paiements = Filter.findFournisseurPaiements(fournChoisi);
		achats.addAll(paiements);
		return achats;
	}
	
	public static void setListFournisseurAchats(JList list, JComboBox cb){

		String item = cb.getSelectedItem().toString();
		Fournisseur fournisseurChoisi = Filter.getFournisseur(item);
		ArrayList<Achat> achats = Filter.findFournisseurAchats(fournisseurChoisi);
		DefaultListModel<Achat> listModel = new DefaultListModel<Achat>();
		for (Achat a: achats) {
			listModel.addElement(a);
		}
		list.setModel(listModel);
	
	}
	public static void setListClientRecus(JList list, JComboBox cb){

		String item = cb.getSelectedItem().toString();
		Client clientChoisi = Filter.getClient(item);
		ArrayList<Recu> recus = Filter.findClientRecus(clientChoisi);
		DefaultListModel<Recu> listModel = new DefaultListModel<Recu>();
		for (Recu r: recus) {
			listModel.addElement(r);
		}
		list.setModel(listModel);
	
	}
	
	public static void setListFournisseurPaiements(JList list, JComboBox cb){

		String item = cb.getSelectedItem().toString();
		Fournisseur fournisseurChoisi = Filter.getFournisseur(item);
		ArrayList<Paiement> paiements = Filter.findFournisseurPaiements(fournisseurChoisi);
		DefaultListModel<Paiement> listModel = new DefaultListModel<Paiement>();
		for (Paiement p: paiements) {
			listModel.addElement(p);
		}
		list.setModel(listModel);
	
	}

	public static void setListCategories(JList categoriesList, JComboBox combobox) {
		// TODO Auto-generated method stub


		String item = combobox.getSelectedItem().toString();
		Categorie categorieChoisi = Filter.getCategorie(item);
		ArrayList<Article> articles = Filter.getCategorieArticles(categorieChoisi);
		DefaultListModel<Article> listModel = new DefaultListModel<Article>();
		for (Article a: articles) {
			listModel.addElement(a);
		}
		categoriesList.setModel(listModel);
	
	
	}

	public static ArrayList<Article> getCategorieArticles(Categorie item) {
		// TODO Auto-generated method stub

		
		Object arr[] =   Files.getArticle().values().toArray();
		Article data[] = new Article[arr.length];
		ArrayList<Article> articles = new ArrayList<Article>();
		for(int i=0; i<data.length; i++) {
			data[i] = (Article) arr[i];
				if(item.noCategorie == data[i].categorie.noCategorie) {
				articles.add(data[i]);
			}
			
			
		}//end loop
		return  articles;
	}
	
//	private static ArrayList getTransactionsArticle(Article item) {
//		// TODO Auto-generated method stub
//
//		
//		Object arr[] =  Filter.getA
//		Article data[] = new Article[arr.length];
//		ArrayList<Article> articles = new ArrayList<Article>();
//		for(int i=0; i<data.length; i++) {
//			data[i] = (Article) arr[i];
//				if(item.noCategorie == data[i].categorie.noCategorie) {
//				articles.add(data[i]);
//			}
//			
//			
//		}//end loop
//		System.out.println("haha");
//		return  articles;
//	}

	public static Categorie getCategorie(String s) {
		// TODO Auto-generated method stub

		int noCategorie = Integer.parseInt(s.substring(s.indexOf("(")+1, s.indexOf(")")));
		Object[] arr= Files.getCategories().values().toArray();
		Categorie c = null;
		Categorie[] categorieData = (Categorie[]) new Categorie[arr.length];
		
		for(int i=0; i<categorieData.length; i++) {
			categorieData[i] = (Categorie) arr[i];
			if(noCategorie == categorieData[i].noCategorie ) {
				c = (Categorie) categorieData[i];
			}
		}//endloop
		return c ;
	
	}

	public static Article getArticle(String s) {
		int noArticle = Integer.parseInt(s.substring(s.indexOf("(")+1, s.indexOf(")")));
		Object[] arr= Files.getArticle().values().toArray();
		Article a = null;
		Article[] categorieData = (Article[]) new Article[arr.length];
		
		for(int i=0; i<categorieData.length; i++) {
			categorieData[i] = (Article) arr[i];
			if(noArticle == categorieData[i].noArticle ) {
				a = (Article) categorieData[i];
			}
		}//endloop
		return a;

	}
}
//huhu(6)