package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class CreateRapportPanelCompte extends JPanel {
	JFrame frame;
	ButtonGroup bg;
	JTextField textFields[];
	JButton enregistrer, creer, quitter;
	String currentPane;
	JRadioButton venteRadio, recuRadio;
	JButton okButton, quitterButton;
	JTextField tf1, tf2;
	JComboBox villes, comboBox = new JComboBox();;
	JList comptesList;
	JTable table;
	CreateRapportPanelCompte(String s, String ss, String label1, String label2, JFrame f) {
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
		JLabel client = new JLabel(s, SwingConstants.CENTER);
		if (currentPane == "Client")
			Pattern.createClientBox(comboBox);
		else
			Pattern.createFournisseurBox(comboBox);
		boxPane.add(client);
		boxPane.add(comboBox);
		gridPane.add(boxPane);

		JPanel parentPan = new JPanel();
		parentPan.setLayout(new GridLayout(1, 3));

		JLabel comboLabel = new JLabel("Ville", SwingConstants.CENTER);
		DefaultComboBoxModel<String> villesmod = new DefaultComboBoxModel<String>(Ville.villeNoms);
		villes = new JComboBox(villesmod);
		JPanel comboPane = new JPanel();
		comboPane.setLayout(new GridLayout(2, 1));
		comboPane.add(comboLabel);
		comboPane.add(villes);
		parentPan.add(comboPane);

		JPanel radioPan = new JPanel();
		radioPan.setLayout(new GridLayout(2, 1));
		venteRadio = new JRadioButton(label1);
		venteRadio.setSelected(true);
		venteRadio.setActionCommand(label1);
		recuRadio = new JRadioButton(label2);
		recuRadio.setActionCommand(label2);
		bg = new ButtonGroup();
		bg.add(venteRadio);
		bg.add(recuRadio);
		JPanel tempan = new JPanel();
		JLabel emp = new JLabel("     ");
		tempan.add(venteRadio);
		tempan.add(recuRadio);
		radioPan.add(emp);
		radioPan.add(tempan);
		parentPan.add(radioPan);

		JPanel buttonPan = new JPanel();
		buttonPan.setLayout(new GridLayout(2, 1));
		okButton = new JButton("OK");
		okButton.addActionListener(new rapportListener());
		quitterButton = new JButton("Quitter");
		quitterButton.addActionListener(new rapportListener());

		buttonPan.add(okButton);
		buttonPan.add(quitterButton);
		parentPan.add(buttonPan);

		northPan.add(gridPane);
		northPan.add(parentPan);

		// tablePan
		String[] columns = { "No Trs", "Descrip Trs", "Date Trs", ss, "Montant " };
		DefaultTableModel tabmod = new DefaultTableModel();
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
				
				DefaultTableModel tabmod = (DefaultTableModel) table.getModel();
				for (int i = 0; i < table.getRowCount(); i++) {
					tabmod.setValueAt(null, i, 0);
					tabmod.setValueAt(null, i, 1);
					tabmod.setValueAt(null, i, 2);
					tabmod.setValueAt(null, i, 3);
					tabmod.setValueAt(null, i, 4);
				}
				String dateDebutString, dateFinString;
				String typeTransaction = bg.getSelection().getActionCommand().toString();
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
					JOptionPane.showMessageDialog(null, "Veuillez pr�ciser la date de l'achat", "Champ Obligatoire",
							JOptionPane.WARNING_MESSAGE);

				else if ((!validDateDebut && !dateDebutString.isEmpty())
						|| (!validDateFin && !dateFinString.isEmpty())) {
					JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",
							JOptionPane.ERROR_MESSAGE);
				}

				// sort by date for Ventes
				if (validDateDebut && validDateFin && debut != null && fin != null && currentPane == "Client") {
					Client c = Filter.getClient(comboBox.getSelectedItem().toString());

					ArrayList<Recu> recus = Filter.findClientRecus(c);
					ArrayList<Recu> filteredRecus = new ArrayList<Recu>();

					ArrayList<Vente> ventes = Filter.findClientVentes(c);
					ArrayList<Vente> filteredVentes = new ArrayList<Vente>();
					Date dateRecu = new Date();
					Date dateVente = new Date();
				
					for (int i = 0; i < recus.size(); i++) {
						dateRecu = recus.get(i).dateTransaction;
						if (!debut.after(dateRecu) && !fin.before(dateRecu))
							filteredRecus.add(recus.get(i));
					}
					
					for (int i = 0; i < ventes.size(); i++) {
						dateVente = ventes.get(i).dateTransaction;
						if (!debut.after(dateVente) && !fin.before(dateVente))
							filteredVentes.add(ventes.get(i));
					}
					if (typeTransaction == "Re�u") {

						
						Collections.sort(filteredRecus);
						for (int i = 0; i < filteredRecus.size(); i++) {
							Recu item = filteredRecus.get(i);
							tabmod.setValueAt(item.noTransaction, i, 0);
							tabmod.setValueAt(item.description, i, 1);
							tabmod.setValueAt(Pattern.format.format(item.dateTransaction), i, 2);
							tabmod.setValueAt(item.client.nomCompte + "(" + item.client.noCompte + ")", i, 3);
							tabmod.setValueAt(item.montant, i, 4);

						} // end loop
					
					} // end recu Filtering

					else if(typeTransaction == "Vente") {
					
						Collections.sort(filteredVentes);
						for (int i = 0; i < filteredVentes.size(); i++) {
							Vente item = filteredVentes.get(i);
							tabmod.setValueAt(item.noTransaction, i, 0);
							tabmod.setValueAt(item.description, i, 1);
							tabmod.setValueAt(Pattern.format.format(item.dateTransaction), i, 2);
							tabmod.setValueAt(item.client.nomCompte + "(" + item.client.noCompte + ")", i, 3);
							tabmod.setValueAt(item.montant, i, 4);

						} // end loop
					} // end Vente Filtering

				} // end case Client

				if (validDateDebut && validDateFin && debut != null && fin != null && currentPane == "Fournisseur") {

					Fournisseur c = Filter.getFournisseur(comboBox.getSelectedItem().toString());

					ArrayList<Paiement> Paiements = Filter.findFournisseurPaiements(c);
					ArrayList<Paiement> filteredPaiements = new ArrayList<Paiement>();

					ArrayList<Achat> Achats = Filter.findFournisseurAchats(c);
					ArrayList<Achat> filteredAchats = new ArrayList<Achat>();
					Date datePaiement = new Date();
					Date dateAchat = new Date();
				
					for (int i = 0; i < Paiements.size(); i++) {
						datePaiement = Paiements.get(i).dateTransaction;
						if (!debut.after(datePaiement) && !fin.before(datePaiement))
							filteredPaiements.add(Paiements.get(i));
					}
					
					for (int i = 0; i < Achats.size(); i++) {
						dateAchat = Achats.get(i).dateTransaction;
						if (!debut.after(dateAchat) && !fin.before(dateAchat))
							filteredAchats.add(Achats.get(i));
					}
					
					if (typeTransaction == "Paie") {

						
						Collections.sort(filteredPaiements);
						for (int i = 0; i < filteredPaiements.size(); i++) {
							Paiement item = filteredPaiements.get(i);
							tabmod.setValueAt(item.noTransaction, i, 0);
							tabmod.setValueAt(item.description, i, 1);
							tabmod.setValueAt(Pattern.format.format(item.dateTransaction), i, 2);
							tabmod.setValueAt(item.fournisseur.nomCompte + "(" + item.fournisseur.noCompte + ")", i, 3);
							tabmod.setValueAt(item.montant, i, 4);

						} // end loop
					
					} // end Paiement Filtering

					else if(typeTransaction == "Achat") {
					
						Collections.sort(filteredAchats);
						for (int i = 0; i < filteredAchats.size(); i++) {
							Achat item = filteredAchats.get(i);
							tabmod.setValueAt(item.noTransaction, i, 0);
							tabmod.setValueAt(item.description, i, 1);
							tabmod.setValueAt(Pattern.format.format(item.dateTransaction), i, 2);
							tabmod.setValueAt(item.fournisseur.nomCompte + "(" + item.fournisseur.noCompte + ")", i, 3);
							tabmod.setValueAt(item.montant, i, 4);

						} // end loop
					} // end Achat Filtering

				
				}

			}

		}
	}
}
