package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TransactionFiles implements Serializable {
	static HashMap achatsMap = new HashMap();
	static HashMap ventesMap = new HashMap();
	static HashMap recusMap = new HashMap();
	static HashMap paiementsMap = new HashMap();
	static ArrayList detailsAchat = new ArrayList();
	static ArrayList ventesDetail = new ArrayList();

	public static HashMap getAchats() {
		// TODO Auto-generated method stub
		try {
			FileInputStream in = new FileInputStream("C:\\projetNFA035\\achats.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			achatsMap = ((HashMap) ois.readObject());
			ois.close();
		} catch (Exception e) {

		}
		return achatsMap;
	}

	public static HashMap<Integer, Vente> getVentes() {
		// TODO Auto-generated method stub
		try {
			FileInputStream in = new FileInputStream("C:\\projetNFA035\\ventes.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			ventesMap = ((HashMap<Integer, Vente>) ois.readObject());
			ois.close();
		} catch (Exception e) {

		}
		return ventesMap;
	}

	public static HashMap getRecus() {
		// TODO Auto-generated method stub
		try {
			FileInputStream in = new FileInputStream("C:\\projetNFA035\\recus.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			recusMap = ((HashMap) ois.readObject());
			ois.close();
		} catch (Exception e) {

		}
		return recusMap;
	}

	public static HashMap getPaiements() {
		// TODO Auto-generated method stub
		try {
			FileInputStream in = new FileInputStream("C:\\projetNFA035\\paiements.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			paiementsMap = ((HashMap) ois.readObject());
			ois.close();
		} catch (Exception e) {

		}
		return paiementsMap;
	}

	public static int getTransactionsSize() {
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
		} catch (Exception e) {

		}
		getAchats();
		achatsMap.put(noA, a);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\achats.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(achatsMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {

		}
	}

	static void createPaiement(int noA, Paiement a) {

		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\paiements.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(paiementsMap);
			oos.flush();
			oos.close();
		} catch (Exception e) {

		}
		getPaiements();
		paiementsMap.put(noA, a);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\paiements.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(paiementsMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {

		}

	}

	static void createRecu(int noA, Recu a) {

		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\recus.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(recusMap);
			oos.flush();
			oos.close();
		} catch (Exception e) {

		}
		getRecus();
		recusMap.put(noA, a);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\recus.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(recusMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {

		}

	}

	static void createVente(int noA, Vente a) {

		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\ventes.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(ventesMap);
			oos.flush();
			oos.close();
		} catch (Exception e) {

		}
		getVentes();
		ventesMap.put(noA, a);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\ventes.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(ventesMap);
			oos.flush();
			oos.close();

		} catch (Exception e) {

		}

	}

	public static int getTransactionSize() {
		ArrayList map1 = new ArrayList(getVentes().values());
		ArrayList map2 = new ArrayList(getAchats().values());
		map1.addAll(map2);
		return map1.size();

	}

	public static void createDetailAchat(DetailAchat newDA) {
		// TODO Auto-generated method stub

		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\detailsachat.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(detailsAchat);
			oos.flush();
			oos.close();
		} catch (Exception e) {

		}
		getDetailAchat();
		detailsAchat.add(newDA);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\detailsachat.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(detailsAchat);
			oos.flush();
			oos.close();

		} catch (Exception e) {

		}

	}

	public static ArrayList getDetailVente() {
		// TODO Auto-generated method stub
		try {
			FileInputStream in = new FileInputStream("C:\\projetNFA035\\detailsventes.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			ventesDetail = ((ArrayList) ois.readObject());
			ois.close();
		} catch (Exception e) {

		}
		return ventesDetail;

	}

	public static void createDetailVente(DetailVente v) {

		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\detailsventes.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(ventesDetail);
			oos.flush();
			oos.close();
		} catch (Exception e) {

		}
		getDetailVente();
		ventesDetail.add(v);
		try {
			FileOutputStream out = new FileOutputStream("C:\\projetNFA035\\detailsventes.txt");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(ventesDetail);
			oos.flush();
			oos.close();

		} catch (Exception e) {

		}
	}

	public static ArrayList getDetailAchat() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		try {
			FileInputStream in = new FileInputStream("C:\\projetNFA035\\detailsachat.txt");
			ObjectInputStream ois = new ObjectInputStream(in);
			detailsAchat = ((ArrayList) ois.readObject());
			ois.close();
		} catch (Exception e) {

		}
		return detailsAchat;

	}

}
