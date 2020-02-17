package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.io.*;
import com.sun.javafx.collections.MappingChange.Map;

public class backend {
	 ArrayList<Transaction> transactionList;
	 ArrayList<Client> clientList;
	 HashMap<Paiement, Transaction.TypePaie> paiementMAP;
	 HashMap<Recu, Transaction.TypePaie> recuMAP;
	    Map<Integer, String> clientsBox, fournisseurBox;
	    ArrayList<Fournisseur> fournisseurList;
	    ArrayList<Article> articleList;
	    ArrayList<Vente> venteList;
	    ArrayList<Achat> achatList;
	    ArrayList<Recu> recuList;
	    ArrayList<Paiement> paiementsList;
	    
	    backend() {
	    	  //READING FILES AND INITIALIZING ARRAYLISTS
	        transactionList = new ArrayList();
	        File fileTransactions = new File("C:\\projetNFA035");
	        if (fileTransactions.exists()) {
	            try {
	                FileInputStream in = new FileInputStream("C:\\javatest\\transactions.out");
	                ObjectInputStream ois = new ObjectInputStream(in);
	                transactionList = (ArrayList<Transaction>) (ois.readObject());
	            } catch (Exception e) {
	                System.out.println("Problem serializing: 1" + e);
	            }
	        }
	        Transaction.noSerie = transactionList.size() + 1;

	        clientList = new ArrayList();
	        clientList.add(new Client("hello", new Ville("abc")));
	        File fileClients = new File("C:\\javatest\\clients.out");
	        if (fileClients.exists()) {
	            try {
	                FileInputStream in = new FileInputStream("C:\\javatest\\clients.out");
	                ObjectInputStream ois = new ObjectInputStream(in);
	                clientList = (ArrayList<Client>) (ois.readObject());
	            } catch (Exception e) {
	                System.out.println("Problem serializing: 2 " + e);
	            }
	        }

	        clientList = new ArrayList();
	        File fileFournisseurs = new File("C:\\javatest\\fournisseurs.out");
	        if (fileFournisseurs.exists()) {
	            try {
	                FileInputStream in = new FileInputStream("C:\\javatest\\fournisseurs.out");
	                ObjectInputStream ois = new ObjectInputStream(in);
	                fournisseurList = (ArrayList<Fournisseur>) (ois.readObject());
	            } catch (Exception e) {
	                System.out.println("Problem serializing: 3 " + e);
	            }
	        }

	        articleList = new ArrayList();
	        articleList.add(new Article());
	        File fileArticles = new File("C:\\javatest\\articles.out");
	        if (fileArticles.exists()) {
	            try {
	                FileInputStream in = new FileInputStream("C:\\javatest\\articles.out");
	                ObjectInputStream ois = new ObjectInputStream(in);
	                articleList = (ArrayList<Article>) (ois.readObject());
	            } catch (Exception e) {
	                System.out.println("Problem serializing: 4 " + e);
	            }
	        }

	        venteList = new ArrayList();
	        File fileVentes = new File("C:\\javatest\\ventes.out");
	        if (fileVentes.exists()) {
	            try {
	                FileInputStream in = new FileInputStream("C:\\javatest\\ventes.out");
	                ObjectInputStream ois = new ObjectInputStream(in);
	                venteList = (ArrayList<Vente>) (ois.readObject());
	            } catch (Exception e) {
	                System.out.println("Problem serializing: 5 " + e);
	            }
	        }

	        achatList = new ArrayList();
	        File fileAchats = new File("C:\\javatest\\achats.out");
	        if (fileAchats.exists()) {
	            try {
	                FileInputStream in = new FileInputStream("C:\\javatest\\achats.out");
	                ObjectInputStream ois = new ObjectInputStream(in);
	                achatList = (ArrayList<Achat>) (ois.readObject());
	            } catch (Exception e) {
	                System.out.println("Problem serializing: " + e);
	            }
	        }

	        recuMAP = new HashMap();
	        File fileRecus = new File("C:\\javatest\\recus.out");
	        if (fileRecus.exists()) {
	            try {
	                FileInputStream in = new FileInputStream("C:\\javatest\\recus.out");
	                ObjectInputStream ois = new ObjectInputStream(in);
	                recuMAP = (HashMap<Recu, Transaction.TypePaie>) (ois.readObject());
	            } catch (Exception e) {
	                System.out.println("Problem serializing:" + e);
	            }
	        }
	        recuList = new ArrayList(recuMAP.keySet());
	        Collections.sort(recuList);

	        paiementMAP = new HashMap();
	        File filePaiements = new File("C:\\javatest\\paiements.out");
	        if (filePaiements.exists()) {
	            try {
	                FileInputStream in = new FileInputStream("C:\\javatest\\paiements.out");
	                ObjectInputStream ois = new ObjectInputStream(in);
	                paiementMAP = (HashMap<Paiement, Transaction.TypePaie>) (ois.readObject());
	            } catch (Exception e) {
	                System.out.println("Problem serializing: " + e);
	            }
	        }
	    }
}
