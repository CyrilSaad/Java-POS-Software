package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.company.Compte.EtatCompte;

public class CreatePanelVA extends JPanel {
	JButton creer, enregistrer, quitter;
	JFrame frame;
	String currentPane;
	JTextField textFields[];
	JComboBox comboBox;
	JList achatsList;

	CreatePanelVA(String ss, String s, String cbLabel, JFrame f) {
		this.frame = f;
		this.currentPane = ss;
		JPanel parentPanel, buttonPanel;
		JLabel Ventes = new JLabel("");

		parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());
		comboBox = new JComboBox();
		comboBox.addActionListener(new itemSelected());
		comboBox.setEditable(false);
		comboBox.setPreferredSize(new Dimension(200, 25));
		creer = new JButton("Créer");
		enregistrer = new JButton("Enregistrer");
		enregistrer.addActionListener(new panelInitVA(textFields));
		enregistrer.setEnabled(false);
		quitter = new JButton("Quitter");
		creer.addActionListener(new panelInitVA(textFields));
		quitter.addActionListener(new panelInitVA());
		this.setLayout(new BorderLayout());

		DefaultTableModel tabmod = new DefaultTableModel();
		tabmod.addColumn("Article");
		tabmod.addColumn("Quantité");
		tabmod.addColumn(s + " Unit");
		tabmod.addColumn(s + " Total");
		for (int i = 0; i < 20; i++)
			tabmod.addRow(new Object[] { null, null, null, null });
		JTable table = Pattern.createTable(tabmod);
		JScrollPane scrollPane = new JScrollPane(table);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());

		JPanel title = new JPanel();
		title.add(Ventes, SwingConstants.CENTER);
		parentPanel.add(title, BorderLayout.NORTH);
		JPanel buttonChildPanel = new JPanel();
		buttonChildPanel.add(creer);
		buttonChildPanel.add(enregistrer);
		buttonChildPanel.add(quitter);
		buttonPanel.add(buttonChildPanel, BorderLayout.WEST);
		JPanel cbPan = new JPanel();
		JLabel choirsirClient = new JLabel("Choisir " + cbLabel + ": ");
		cbPan.add(choirsirClient);
		cbPan.add(comboBox);
		buttonPanel.add(cbPan, BorderLayout.EAST);
		parentPanel.add(buttonPanel, BorderLayout.CENTER); // EndOfFirstPart

		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new GridLayout(1, 2));
		JPanel childDisplayPanel = new JPanel();

		textFields = new JTextField[4];
		for (int i = 0; i < 4; i++) {
			textFields[i] = new JTextField(20);
			textFields[i].setPreferredSize(new Dimension(500, 25));
			textFields[i].setEditable(false);
		}
		textFields[2].setToolTipText("dd/MM/yyyy");
		JPanel pp = new JPanel();
		pp.setLayout(new FlowLayout(FlowLayout.LEFT));
		childDisplayPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel noVente = new JLabel("No. " + ss + ":");
		gbc.gridx = 0;
		gbc.gridy = 0;
		childDisplayPanel.add(noVente, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 3;
		childDisplayPanel.add(textFields[0], gbc);

		JLabel description = new JLabel("Description: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		childDisplayPanel.add(description, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		childDisplayPanel.add(textFields[1], gbc);

		JLabel dateVente = new JLabel("Date " + ss + ":");
		gbc.gridx = 0;
		gbc.gridy = 3;
		childDisplayPanel.add(dateVente, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		childDisplayPanel.add(textFields[2], gbc);

		JLabel montantVente = new JLabel("Montant " + ss + ":");
		gbc.gridx = 0;
		gbc.gridy = 4;
		childDisplayPanel.add(montantVente, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		childDisplayPanel.add(textFields[3], gbc);

		achatsList = new JList();
		JScrollPane listScroller = new JScrollPane(achatsList);
		// listScroller.add
		listScroller.setPreferredSize(new Dimension(500, 500));
		achatsList.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), ss + "s",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

		pp.add(childDisplayPanel);

		displayPanel.add(pp); // add list to EAS
		displayPanel.add(listScroller);

		listScroller.setBorder(new EmptyBorder(0, 0, 10, 10));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Infos " + ss + "s",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

		this.add(scrollPane, BorderLayout.SOUTH);
		this.add(displayPanel, BorderLayout.CENTER);
		this.add(parentPanel, BorderLayout.NORTH);
		// end of first panel

	};

	private class panelInitVA implements ActionListener {

		JTextField tf[];

		panelInitVA(JTextField[] tf) {
			super();
			this.tf = tf;
		}

		public panelInitVA() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			Object eventSource = e.getSource();
			if (currentPane == "Vente") {
				if (eventSource == creer) {
					creer.setEnabled(false);
					textFields[0].setText("" + (Transaction.noSerie + 1));
					textFields[2].setEditable(true);
					textFields[3].setText("" + 0);
					enregistrer.setEnabled(true);
					Pattern.createClientBox(comboBox);

				} // Creer button

				if (eventSource == quitter) {
					frame.dispose();
				}

				if (eventSource == enregistrer) {
					String dateString = textFields[2].getText();
					boolean validDate = Pattern.isDate(dateString);
					Client c = Filter.getClient(comboBox.getSelectedItem().toString());
					double montant = Double.parseDouble(textFields[3].getText());
					if (!dateString.isEmpty() && !validDate)
						JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",
								JOptionPane.ERROR_MESSAGE);
					else if (dateString.isEmpty())
						JOptionPane.showMessageDialog(null, "Veuillez préciser la date de la vente",
								"Champ Obligatoire", JOptionPane.WARNING_MESSAGE);
					else {
						Vente a = new Vente(dateString, montant, c);
						creer.setEnabled(true);
						JOptionPane.showMessageDialog(null, "Compte enregistré avec succès", "Enregistrement du compte",
								JOptionPane.INFORMATION_MESSAGE);
						for (int i = 0; i < textFields.length; i++)
							textFields[i].setText("");
						TransactionFiles.createVente(a.noTransaction, a);
						enregistrer.setEnabled(false);
					}
				}
			} // CreerClient

			else if (currentPane == "Achat") {
				if (eventSource == creer) {
					creer.setEnabled(false);
					textFields[0].setText("" + (Transaction.noSerie + 1));
					textFields[2].setEditable(true);
					textFields[3].setText("" + 0);
					enregistrer.setEnabled(true);
					Pattern.createFournisseurBox(comboBox);

				} // Creer button

				if (eventSource == quitter) {
					frame.dispose();
				}

				if (eventSource == enregistrer) {
					String dateString = textFields[2].getText();
					boolean validDate = Pattern.isDate(dateString);
					Fournisseur f = Filter.getFournisseur(comboBox.getSelectedItem().toString());
					double montant = Double.parseDouble(textFields[3].getText());
					if (!dateString.isEmpty() && !validDate)
						JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",
								JOptionPane.WARNING_MESSAGE);
					else if (dateString.isEmpty())
						JOptionPane.showMessageDialog(null, "Veuillez préciser la date de l'achat", "Champ Obligatoire",
								JOptionPane.WARNING_MESSAGE);
					else {
						Achat a = new Achat(dateString, montant, f);
						creer.setEnabled(true);
						JOptionPane.showMessageDialog(null, "Compte enregistré avec succès", "Enregistrement du compte", JOptionPane.DEFAULT_OPTION);
						for (int i = 0; i < textFields.length; i++)
							textFields[i].setText("");
						TransactionFiles.createAchat(a.noTransaction, a);
						enregistrer.setEnabled(false);

					}
				}

			}
		}

	}

	private class itemSelected implements ActionListener {

		@Override

		public void actionPerformed(ActionEvent arg0) {
			if (currentPane == "Vente") {
				Filter.setListClientVentes(achatsList, comboBox);
			}
			
			if (currentPane == "Achat") {
				Filter.setListFournisseurAchats(achatsList, comboBox);
			}

		}

	}
}

//
