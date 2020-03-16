package com.company;


import java.awt.Component;
import java.io.*;
import java.util.*;

public class Files {
	static HashMap compteMap = new HashMap();
	static HashMap categorieMap = new HashMap();
	static HashMap articleMap = new HashMap();
	public static HashMap getComptes() {
		// TODO Auto-generated method stub
		try {
			   FileInputStream in = new FileInputStream("C:\\projetNFA035\\clients.txt");
			   ObjectInputStream ois = new ObjectInputStream(in);
			   compteMap =( (HashMap) ois.readObject()); 
		} catch(Exception e) {System.out.println("Problem serializing " + e);}
		return compteMap;
	}
	
	public static HashMap getCategories() {
		// TODO Auto-generated method stub
		try {
			   FileInputStream in = new FileInputStream("C:\\projetNFA035\\categories.txt");
			   ObjectInputStream ois = new ObjectInputStream(in);
			   categorieMap =( (HashMap) ois.readObject()); 
		} catch(Exception e) {System.out.println("Problem serializing " + e);}
		return categorieMap;
	}

	public static HashMap getArticle() {
		// TODO Auto-generated method stub
		try {
			FileInputStream in = new FileInputStream("C:\\projetNFA035\\articles.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			articleMap =( (HashMap) ois.readObject()); 
		} catch(Exception e) {System.out.println("Problem serializing " + e);}
		return articleMap;
	}
	
	static void createClient() {


		 HashMap tempClient = new HashMap();
		
		try {
			
		   FileInputStream in = new FileInputStream("C:\\projetNFA035\\clients.txt");
		   ObjectInputStream ois = new ObjectInputStream(in);
		   compteMap =( (HashMap) ois.readObject()) ;
		   	ois.close(); 
	   } catch (Exception e) {System.out.println("Problem serializing " + e);}
		try {
		 	FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\clients.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
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
			
		   FileInputStream in = new FileInputStream("C:\\projetNFA035\\clients.txt");
		   ObjectInputStream ois = new ObjectInputStream(in);
		   compteMap =( (HashMap) ois.readObject()) ;
		   compteMap.put(noF, F);
		   	ois.close(); 
	   } catch (Exception e) {System.out.println("Problem serializing " + e);}
		try {
		 	FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\clients.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(compteMap);
			oos.flush();
			oos.close();
	
	}catch(Exception e) { System.out.println("Problem serializing " + e);}
	System.out.println(compteMap);

	  
	}



	public static void createCategorie(int noCategorie, Categorie c) {
		// TODO Auto-generated method stub
		 HashMap tempCategorie = new HashMap();
		 tempCategorie.put(noCategorie, c);
		try {
			
		   FileInputStream in = new FileInputStream("C:\\projetNFA035\\categories.txt");
		   ObjectInputStream ois = new ObjectInputStream(in);
		   categorieMap =( (HashMap) ois.readObject()) ;
		   	categorieMap.put(noCategorie, c);
		   	ois.close(); 
	   } catch (Exception e) {System.out.println("Problem serializing " + e);}
		try {
		 	FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\categories.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(categorieMap);
			oos.flush();
			oos.close();
	
	}catch(Exception e) { System.out.println("Problem serializing " + e);}
	System.out.println(categorieMap);

	}



	}


	
	


