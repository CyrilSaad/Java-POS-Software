package com.company;

import java.util.*;
import javax.swing.*;
import javax.swing.DefaultListModel;

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
		
		Object arr[] =   TransactionFiles.getVentes().values().toArray();
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
}
//huhu(6)