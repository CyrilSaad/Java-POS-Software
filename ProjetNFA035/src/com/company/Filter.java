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

	private static ArrayList getCategorieArticles(Categorie item) {
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
		System.out.println("haha");
		return  articles;
	}

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
}
//huhu(6)