package com.company;


import java.awt.Component;
import java.io.*;
import java.util.*;

public class Files implements Serializable{
	 static HashMap compteMap = new HashMap();
	 static HashMap compteFMap = new HashMap();
	private static HashMap categorieMap = new HashMap();
	private static HashMap articleMap = new HashMap();
	 private static HashMap transactionMap = new HashMap();
	  public static HashMap getComptes() {
		// TODO Auto-generated method stub
		try {
			   FileInputStream in = new FileInputStream("C:\\projetNFA035\\clients.txt");
			   ObjectInputStream ois = new ObjectInputStream(in);
			   compteMap =( (HashMap) ois.readObject()); 
			   ois.close();
		} catch(Exception e) {System.out.println("Problem serializing1 " + e);}
		return compteMap;
	}
	  
	  public static HashMap getComptesF() {
			try {
				   FileInputStream in = new FileInputStream("C:\\projetNFA035\\fournisseurs.txt");
				   ObjectInputStream ois = new ObjectInputStream(in);
				   compteFMap =( (HashMap) ois.readObject()); 
				   ois.close();
			} catch(Exception e) {System.out.println("Problem serializing1 " + e);}
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
			   categorieMap =( (HashMap) ois.readObject()); 
			   ois.close();
		} catch(Exception e) {System.out.println("Problem serializing5 " + e);}
		return categorieMap;
	}

	public static HashMap getArticle() {
		// TODO Auto-generated method stub
		try {
			FileInputStream in = new FileInputStream("C:\\projetNFA035\\articles.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			articleMap =((HashMap) ois.readObject()); 
		} catch(Exception e) {System.out.println("Problem serializing1 " + e);}
		return articleMap;
	}
	
	static void createClient(int noClient, Client c) {


		 HashMap tempClient = new HashMap();
		 tempClient.put(noClient, c);		
		getComptes();
		try {
		 	FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\clients.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			compteMap.putAll(tempClient);
			oos.writeObject(compteMap);
			oos.flush();
			oos.close();
	
	}catch(Exception e) { System.out.println("Problem serializing " + e);}
	System.out.println(compteMap);
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
		 } catch(Exception e) { System.out.println("Problem serializing 2" + e);}
		try {
			
		   FileInputStream in = new FileInputStream("C:\\projetNFA035\\fournisseurs.txt");
		   ObjectInputStream ois = new ObjectInputStream(in);
		   compteFMap =( (HashMap) ois.readObject()) ;
		   compteFMap.put(noF, F);
		   	ois.close(); 
	   } catch (Exception e) {System.out.println("Problem serializing " + e);}
		try {
		 	FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\fournisseurs.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(compteFMap);
			oos.flush();
			oos.close();
	
	}catch(Exception e) { System.out.println("Problem serializing 2" + e);}
	System.out.println(compteFMap);

	  
	}
	static void createArticle(int noA, Article a) {
		 
		 try {
			 FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\articles.txt");
				ObjectOutputStream oos = new ObjectOutputStream(out);
				oos.writeObject(articleMap);
				oos.flush();
				oos.close();
		 } catch(Exception e) { System.out.println("Problem serializing 2" + e);}
		getArticle();
		  articleMap.put(noA, a);
		try {
		 	FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\articles.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(articleMap);
			oos.flush();
			oos.close();

	}catch(Exception e) { System.out.println("Problem serializing 2" + e);}
	System.out.println(articleMap);

	 
	}



	public static void createCategorie(int noCategorie, Categorie c) {
		// TODO Auto-generated method stub
		 HashMap tempCategorie = new HashMap();
		 tempCategorie.put(noCategorie, c);
		 try {
			 	FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\categories.txt");
				ObjectOutputStream oos = new ObjectOutputStream(out);
				oos.writeObject(categorieMap);
				oos.flush();
				oos.close();
		
		}catch(Exception e) { System.out.println("Problem serializing 4" + e);}

		 getCategories();
		 categorieMap.put(noCategorie, c);
		try {
		 	FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\categories.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(categorieMap);
			oos.flush();
			oos.close();
	
	}catch(Exception e) { System.out.println("Problem serializing 4" + e);}
	System.out.println(categorieMap);

	}



	}


	
	


