 package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;


public class CreateRapportPanelRP extends JPanel {
	private JComboBox comboBox = new JComboBox();
	JFrame frame;
	String currentPane;
	JButton quitterButton, okButton;
	JTextField tf1, tf2 = new JTextField(10);
	JTable table;
	DefaultTableModel tabmod;
	JRadioButton cash, cheque, transfert;
	ButtonGroup bg;
	CreateRapportPanelRP(String s, String ss, JFrame f) {
		frame = f;
		currentPane = s;
		
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Rapport " + s + "s",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

		JPanel gridPane = new JPanel();
		gridPane.setLayout(new GridLayout(1, 2, 20, 5));
		JPanel northPan = new JPanel();
		northPan.setLayout(new GridLayout(1, 2, 20, 5));
		northPan.setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel datePane = new JPanel();
		datePane.setLayout(new GridLayout(2, 2, 20, 5));
		JLabel deDate = new JLabel("De date", SwingConstants.CENTER);
		JLabel aDate = new JLabel("A date", SwingConstants.CENTER);
		datePane.add(deDate);
		datePane.add(aDate);
		tf1 = new JTextField(10);
		tf1.setToolTipText("dd/MM/yyyy");
		tf2 = new JTextField(10);
		tf2.setToolTipText("dd/MM/yyyy");
		datePane.add(tf1);
		datePane.add(tf2);
		gridPane.add(datePane);

		JPanel boxPane = new JPanel();
		boxPane.setLayout(new GridLayout(2, 2, 10, 10));
		JLabel client = new JLabel(ss, SwingConstants.CENTER);
		if (currentPane == "Reçu")
			Pattern.createClientBox(comboBox);
		else if (currentPane == "Paiement")
			Pattern.createFournisseurBox(comboBox);

		 quitterButton = new JButton("Quitter"); quitterButton.addActionListener(new rapportListener());
		 okButton = new JButton("OK"); okButton.addActionListener(new rapportListener());
		// cb.setPreferredSize(new Dimension(30,28));
		boxPane.add(client);
		boxPane.add(comboBox);
		gridPane.add(boxPane);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		JPanel panel2 = new JPanel();
		JPanel radioPane = new JPanel();
		JLabel modePaie = new JLabel("Mode Paie");
		panel2.add(modePaie);
		panel.add(panel2);
		panel.add(okButton);
		 cash = new JRadioButton("Cash"); cash.setActionCommand("CASH"); cash.setSelected(true);
		 cheque = new JRadioButton("Cheque"); cheque.setActionCommand("CHEQUE");
		 transfert = new JRadioButton("Transfert"); transfert.setActionCommand("TRANSFERT");
	    bg = new ButtonGroup();
		bg.add(cash); bg.add(cheque); bg.add(transfert);
		radioPane.add(cash);
		radioPane.add(cheque);
		radioPane.add(transfert);
		panel.add(radioPane);
		panel.add(quitterButton);

		northPan.add(gridPane);
		northPan.add(panel);

		// tablePan
		String[] columns = { "No " + s, "Date " + s, ss, "Montant " + s, "Mode Paie" };
		 tabmod = new DefaultTableModel();
		for (int i = 0; i < columns.length; i++)
			tabmod.addColumn(columns[i]);
		 table = Pattern.createTable(tabmod);
		JScrollPane scrollPane = new JScrollPane(table);

		// end
		this.add(northPan, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);


	
	}
	private class rapportListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
		

			if (source == quitterButton)
				frame.dispose();

			if (source == okButton) {
				for (int i = 0; i < table.getRowCount(); i++) {
					tabmod.setValueAt(null, i, 0);
					tabmod.setValueAt(null, i, 1);
					tabmod.setValueAt(null, i, 2);
					tabmod.setValueAt(null, i, 3);
					tabmod.setValueAt(null, i, 4);
				}

				String dateDebutString, dateFinString;
				String typePaie = bg.getSelection().getActionCommand();
				dateDebutString = tf1.getText();
				dateFinString = tf2.getText();
				boolean validDateDebut = Pattern.isDate(dateDebutString);
				boolean validDateFin = Pattern.isDate(dateFinString);
				Date debut = null, fin = null;
				if ((validDateDebut && !dateDebutString.isEmpty()) || (validDateFin && !dateFinString.isEmpty())) {
					try {
						debut = Pattern.format.parse(dateDebutString);
						fin = Pattern.format.parse(dateFinString);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",
								JOptionPane.WARNING_MESSAGE);
					}
				}

				else if (dateDebutString.isEmpty() || dateFinString.isEmpty())
					JOptionPane.showMessageDialog(null, "Veuillez préciser la date de l'achat", "Champ Obligatoire",
							JOptionPane.WARNING_MESSAGE);

				else if ((!validDateDebut && !dateDebutString.isEmpty()) || (!validDateFin && !dateFinString.isEmpty())) {
					JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",
							JOptionPane.ERROR_MESSAGE);
				}
				
				//sort by date for Ventes
				if (validDateDebut && validDateFin && debut != null && fin != null && currentPane == "Reçu") { 
					Client c = Filter.getClient(comboBox.getSelectedItem().toString());

					ArrayList<Recu> recus = Filter.findClientRecus(c);
					ArrayList<Recu> filteredRecus = new ArrayList<Recu>();
					Date date = new Date();
					for (int i = 0; i < recus.size(); i++) {
						date = recus.get(i).dateTransaction;
						if (!debut.after(date) && !fin.before(date) && recus.get(i).typePaie.toString() == typePaie)
							filteredRecus.add(recus.get(i));
					}

					Collections.sort(filteredRecus);
					for (int i = 0; i < filteredRecus.size(); i++) {
						Recu item = filteredRecus.get(i);
						tabmod.setValueAt(item.noTransaction, i, 0);
						tabmod.setValueAt(Pattern.format.format(item.dateTransaction), i, 1);
						tabmod.setValueAt(item.client.nomCompte + "(" + item.client.noCompte + ")", i, 2);
						tabmod.setValueAt(item.montant, i, 3);
						tabmod.setValueAt(item.typePaie.toString(), i, 4);
					}
				}
				
				
				if (validDateDebut && validDateFin && debut != null && fin != null && currentPane == "Paiement") {
					Fournisseur f = Filter.getFournisseur(comboBox.getSelectedItem().toString());

					ArrayList<Paiement> paiements = Filter.findFournisseurPaiements(f);
					ArrayList<Paiement> filteredPaiements = new ArrayList<Paiement>();
			
					Date date = new Date();
					for (int i = 0; i < paiements.size(); i++) {
						date = paiements.get(i).dateTransaction;
						if (!debut.after(date) && !fin.before(date) && paiements.get(i).typePaie.toString() == typePaie)
							filteredPaiements.add(paiements.get(i));
					}

			
					Collections.sort(filteredPaiements);
					for (int i = 0; i < filteredPaiements .size(); i++) {
						Paiement item = filteredPaiements.get(i);
						tabmod.setValueAt(item.noTransaction, i, 0);
						tabmod.setValueAt(Pattern.format.format(item.dateTransaction), i, 1);
						tabmod.setValueAt(item.fournisseur.nomCompte + "(" + item.fournisseur.noCompte + ")", i, 2);
						tabmod.setValueAt(item.montant, i, 3);
						tabmod.setValueAt(item.typePaie, i, 4);
					}
				}
				
				
			}

		}
	}

}
