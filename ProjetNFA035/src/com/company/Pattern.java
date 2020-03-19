package com.company;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class Pattern {
	public static boolean isDouble(String number) {

		try {
			double d = Double.parseDouble(number);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static boolean isDate(String s) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        // Input to be parsed should strictly follow the defined date format
        // above.
        format.setLenient(false);

        try {
            format.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
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


public static JTable createTable(DefaultTableModel tabmod) {
	JTable table = new JTable(tabmod);
	table.setPreferredScrollableViewportSize(new Dimension(500, 300));
	table.setFillsViewportHeight(true);
	table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
	table.setShowGrid(true);
	table.setGridColor(Color.DARK_GRAY);
	table.setRowHeight(25);
	return table;
}
}
