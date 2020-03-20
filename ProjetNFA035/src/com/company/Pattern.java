package com.company;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class Pattern {
	static DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	public static boolean isDouble(String number) {
		boolean bool = false;
		try {
			double d = Double.parseDouble(number);
			if(d>=0) bool = true;
		} catch (NumberFormatException ex) {
			return false;
		}
		return bool;
	}

	public static boolean isDate(String s) {
	

        // Input to be parsed should strictly follow the defined date format
        // above.
        format.setLenient(false);
  
       
        try {
            format.parse(s);  
            String yearString = s.substring(6);
            int year = Integer.parseInt(yearString);
            if(year>=0 && year<=9999) return true;
        } catch (ParseException e) {
            return false;
        }
        return false;
	}
	
	public static void createClientBox(JComboBox clientComboBox) {
		
		Object[] clientData= Files.getComptes().values().toArray();
		int length = clientData.length;
		Client arr [] = new Client[length];
		String [] comboBoxData = new String [length];
		for(int i=0; i<length; i++) {
			arr[i] =  (Client) clientData[i];
			comboBoxData [i] = arr[i].nomCompte + "(" + arr[i].noCompte + ")";
		}
		DefaultComboBoxModel cbmod = new DefaultComboBoxModel(comboBoxData);
		clientComboBox.setModel(cbmod);
	}
	
public static void createFournisseurBox(JComboBox fournisseurComboBox) {
		
		Object[] clientData= Files.getComptesF().values().toArray();
		int length = clientData.length;
		Fournisseur arr [] = new Fournisseur[length];
		String [] comboBoxData = new String [length];
		for(int i=0; i<length; i++) {
			arr[i] =  (Fournisseur) clientData[i];
			comboBoxData [i] = arr[i].nomCompte + "(" + arr[i].noCompte + ")";
		}
		DefaultComboBoxModel cbmod = new DefaultComboBoxModel(comboBoxData);
		fournisseurComboBox.setModel(cbmod);
		
	}

public static void createCategorieBox(JComboBox box) {
	
	Object[] data= Files.getCategories().values().toArray();
	int length = data.length;
	Categorie arr [] = new Categorie[length];
	String [] comboBoxData = new String [length];
	for(int i=0; i<length; i++) {
		arr[i] =  (Categorie) data[i];
		comboBoxData [i] = arr[i].nomCategorie + "(" + arr[i].noCategorie + ")";
	}
	DefaultComboBoxModel cbmod = new DefaultComboBoxModel(comboBoxData);
	box.setModel(cbmod);
	
}
public static JTable createTable(DefaultTableModel tabmod) {
	for (int i = 0; i < 20 + TransactionFiles.getTransactionSize(); i++)
		tabmod.addRow(new Object[] { null, null, null, null });
	JTable table = new JTable(tabmod);
	table.setPreferredScrollableViewportSize(new Dimension(500, 300));
	table.setFillsViewportHeight(true);
	table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
	table.setShowGrid(true);
	table.setGridColor(Color.DARK_GRAY);
	table.setRowHeight(30);
	table.getTableHeader().setReorderingAllowed(false);
	table.setFont(new Font("Serif", Font.BOLD, 25));
	return table;
}

public static void validateDate(String dateString, Date date)
 {
	boolean validDate = Pattern.isDate(dateString);;

	 if(validDate && !dateString.isEmpty()) {
			try {
				 date = Pattern.format.parse(dateString);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",
						JOptionPane.WARNING_MESSAGE);					
			}
		}
		
	 else if (dateString.isEmpty())	JOptionPane.showMessageDialog(null, "Veuillez préciser une date valide", "Champ Obligatoire",JOptionPane.WARNING_MESSAGE);
	
	 else if(!validDate && !dateString.isEmpty()) {
		JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",JOptionPane.ERROR_MESSAGE);			
	}			
 
 
 }
 }
