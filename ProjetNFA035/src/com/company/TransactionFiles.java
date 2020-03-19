package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class TransactionFiles  implements Serializable{
static HashMap achatsMap = new HashMap();
static HashMap <Integer,Vente>ventesMap = new HashMap<Integer, Vente>();
static HashMap recusMap = new HashMap();
static HashMap paiementsMap = new HashMap();

public static HashMap getAchats() {
	// TODO Auto-generated method stub
	try {
		   FileInputStream in = new FileInputStream("C:\\projetNFA035\\achats.txt");
		   ObjectInputStream ois = new ObjectInputStream(in);
		   achatsMap =( (HashMap) ois.readObject()); 
		   ois.close();
	} catch(Exception e) {System.out.println("Problem serializing1 " + e);}
	return achatsMap;
}

public static HashMap<Integer,Vente> getVentes() {
	// TODO Auto-generated method stub
	try {
		   FileInputStream in = new FileInputStream("C:\\projetNFA035\\ventes.txt");
		   ObjectInputStream ois = new ObjectInputStream(in);
		   ventesMap =( (HashMap<Integer,Vente>) ois.readObject()); 
		   ois.close();
	} catch(Exception e) {System.out.println("Problem serializing1 " + e);}
	return ventesMap;
}

public static HashMap getRecus() {
	// TODO Auto-generated method stub
	try {
		   FileInputStream in = new FileInputStream("C:\\projetNFA035\\recus.txt");
		   ObjectInputStream ois = new ObjectInputStream(in);
		   recusMap =( (HashMap) ois.readObject()); 
		   ois.close();
	} catch(Exception e) {System.out.println("Problem serializing1 " + e);}
	return recusMap;
}

public static HashMap getPaiements() {
	// TODO Auto-generated method stub
	try {
		   FileInputStream in = new FileInputStream("C:\\projetNFA035\\paiements.txt");
		   ObjectInputStream ois = new ObjectInputStream(in);
		   paiementsMap =( (HashMap) ois.readObject()); 
		   ois.close();
	} catch(Exception e) {System.out.println("Problem serializing1 " + e);}
	return paiementsMap;
}

public static int getTransactionsSize
() {
	int map1 = getPaiements().size();
	int map2 = getRecus().size();
	int map3 = getAchats().size();
	int map4 = getVentes().size();
	return map1 + map2 + map3 + map4;
	
}

static void createAchat(int noA, Achat a) {
	 
	 try {
		 FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\achats.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(achatsMap);
			oos.flush();
			oos.close();
	 } catch(Exception e) { System.out.println("Problem serializing 2" + e);}
	getAchats();
	  achatsMap.put(noA, a);
	try {
	 	FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\achats.txt");
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(achatsMap);
		oos.flush();
		oos.close();

}catch(Exception e) { System.out.println("Problem serializing 2" + e);}
	}

static void createPaiement(int noA, Paiement a) {

	 
	 try {
		 FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\paiements.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(paiementsMap);
			oos.flush();
			oos.close();
	 } catch(Exception e) { System.out.println("Problem serializing 2" + e);}
	getPaiements();
	  paiementsMap.put(noA, a);
	try {
	 	FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\paiements.txt");
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(paiementsMap);
		oos.flush();
		oos.close();

}catch(Exception e) { System.out.println("Problem serializing 2" + e);}
System.out.println(paiementsMap);
	}

static void createRecu(int noA, Recu a) {

	 try {
		 FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\recus.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(recusMap);
			oos.flush();
			oos.close();
	 } catch(Exception e) { System.out.println("Problem serializing 2" + e);}
	getRecus();
	  recusMap.put(noA, a);
	try {
	 	FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\recus.txt");
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(recusMap);
		oos.flush();
		oos.close();

}catch(Exception e) { System.out.println("Problem serializing 2" + e);}
System.out.println(recusMap);

	}


static void createVente(int noA, Vente a) {
	 
	 try {
		 FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\ventes.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(ventesMap);
			oos.flush();
			oos.close();
	 } catch(Exception e) { System.out.println("Problem serializing 2" + e);}
	getVentes();
	  ventesMap.put(noA, a);
	try {
	 	FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\ventes.txt");
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(ventesMap);
		oos.flush();
		oos.close();

}catch(Exception e) { System.out.println("Problem serializing 2" + e);}
System.out.println(ventesMap);


}
}
