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

import javax.swing.table.TableColumn;

public class CreateRapportPanelVA extends JPanel {
	private JComboBox comboBox = new JComboBox();
	JFrame frame;
	String currentPane;
	JButton quitterButton, okButton;
	JTextField tf1, tf2 = new JTextField(10);
	JTable table;
	DefaultTableModel tabmod;
	TableColumn boxColumn;
	CreateRapportPanelVA(String s, String ss, JFrame f) {
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

		if (currentPane == "Vente")
			Pattern.createClientBox(comboBox);
		else if (currentPane == "Achat")
			Pattern.createFournisseurBox(comboBox);

		boxPane.add(client);
		boxPane.add(comboBox);
		gridPane.add(boxPane);
		JPanel panel = new JPanel();
		okButton = new JButton("OK");
		okButton.addActionListener(new rapportListener());
		quitterButton = new JButton("Quitter");
		quitterButton.addActionListener(new rapportListener());
		panel.add(okButton);
		panel.add(quitterButton);
		quitterButton.setBounds(500, 0, 40, 40);
		northPan.add(gridPane);
		northPan.add(panel);

		// tablePan
		String[] columns = { "No " + s, "Date " + s, ss, "Montant " + s };
		tabmod = new DefaultTableModel();
		for (int i = 0; i < columns.length; i++) tabmod.addColumn(columns[i]);	
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
				}
				String dateDebutString, dateFinString;

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

				else if ((!validDateDebut && !dateDebutString.isEmpty())
						|| (!validDateFin && !dateFinString.isEmpty())) {
					JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",
							JOptionPane.ERROR_MESSAGE);
				}
				
				//sort by date for Ventes
				if (validDateDebut && validDateFin && debut != null && fin != null && currentPane == "Vente") { 
					Client c = Filter.getClient(comboBox.getSelectedItem().toString());

					ArrayList<Vente> ventes = Filter.findClientVentes(c);
					ArrayList<Vente> filteredVentes = new ArrayList<Vente>();
		
					Date date = new Date();
					for (int i = 0; i < ventes.size(); i++) {
						date = ventes.get(i).dateTransaction;
						if (!debut.after(date) && !fin.before(date))
							filteredVentes.add(ventes.get(i));
					}

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					Collections.sort(filteredVentes);
			
					for (int i = 0; i < filteredVentes.size(); i++) {
						Vente item = filteredVentes.get(i);
						model.setValueAt(item.noTransaction, i, 0);
						model.setValueAt(Pattern.format.format(item.dateTransaction), i, 1);
						model.setValueAt(item.client.nomCompte + "(" + item.client.noCompte + ")", i, 2);
						model.setValueAt(item.montant, i, 3);
					}
				}
				
				
				if (validDateDebut && validDateFin && debut != null && fin != null && currentPane == "Achat") {
					Fournisseur f = Filter.getFournisseur(comboBox.getSelectedItem().toString());

					ArrayList<Achat> achats = Filter.findFournisseurAchats(f);
					ArrayList<Achat> filteredAchats = new ArrayList<Achat>();
					String str;
					Date date = new Date();
					for (int i = 0; i < achats.size(); i++) {
						date = achats.get(i).dateTransaction;
						if (!debut.after(date) && !fin.before(date))
							filteredAchats.add(achats.get(i));
					}

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					Collections.sort(filteredAchats);
					int i = 0;
					for (int j = 0; j < filteredAchats.size(); j++) {
						Achat item = filteredAchats.get(i);
						model.setValueAt(item.noTransaction, i, 0);
						model.setValueAt(Pattern.format.format(item.dateTransaction), i, 1);
						model.setValueAt(item.fournisseur.nomCompte + "(" + item.fournisseur.noCompte + ")",
								i, 2);
						model.setValueAt(item.montant, i, 3);
						i++;
					}
				}
			}

		}
	}
}
