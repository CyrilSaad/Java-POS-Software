package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
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

import com.company.Compte.EtatCompte;
import com.company.Transaction.TypePaie;

public class CreatePanelRP extends JPanel {
	JFrame frame;
	ButtonGroup bg;
	JTextField textFields[];
	JButton enregistrer, creer, quitter;
	String currentPane;
	JRadioButton cash, cheque, transfert;
	JComboBox comboBox;
	JList RPList ;
	CreatePanelRP(String ss, String tag, JFrame f) {

		JPanel parentPanel, buttonPanel;
		JLabel Ventes = new JLabel("");
		this.frame = f;
		currentPane = ss;

		parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());

		comboBox = new JComboBox(); comboBox.addActionListener(new itemSelected());
		comboBox.setEditable(false);
		comboBox.setPreferredSize(new Dimension(200, 25));

		creer = new JButton("Créer");
		creer.addActionListener(new panelInitRP(textFields));
		enregistrer = new JButton("Enregistrer");
		enregistrer.addActionListener(new panelInitRP(textFields));
		enregistrer.setEnabled(false);
		quitter = new JButton("Quitter");
		quitter.addActionListener(new panelInitRP(textFields));

		cash = new JRadioButton("Cash", true);
		cash.setActionCommand("CASH");
		cheque = new JRadioButton("Cheque");
		cheque.setActionCommand("CHEQUE");
		transfert = new JRadioButton("Transfert");
		transfert.setActionCommand("TRANSFERT");
		bg = new ButtonGroup();
		bg.add(cash);
		bg.add(cheque);
		bg.add(transfert);

		this.setLayout(new BorderLayout());

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
		JLabel choirsirClient = new JLabel("Choisir " + tag + ":");
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

		JLabel description = new JLabel("Nom " + ss + ":");
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

		JLabel etatCompte = new JLabel("Mode Paiement: ");
		gbc.gridx = 0;
		gbc.gridy = 5;
		childDisplayPanel.add(etatCompte, gbc);

		JPanel buttonPan = new JPanel();
		buttonPan.add(cash);
		buttonPan.add(cheque);
		buttonPan.add(transfert);
		gbc.gridx = 1;
		gbc.gridy = 5;
		childDisplayPanel.add(buttonPan, gbc);

		
		// forLoop arrayList into ListModel .setModel()
		 RPList = new JList(); 
		JScrollPane listScroller = new JScrollPane(RPList);
		// listScroller.add
		listScroller.setPreferredSize(new Dimension(500, 500));
		listScroller.setBorder(new EmptyBorder(0, 0, 300, 10));
		RPList.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), ss + "s",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

		pp.add(childDisplayPanel);

		displayPanel.add(pp); // add list to EAS
		displayPanel.add(listScroller);

		// this.add(listScroller, BorderLayout.EAST);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Infos " + ss + "s",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

//			this.add(scrollPane, BorderLayout.SOUTH);
		this.add(displayPanel, BorderLayout.CENTER);
		this.add(parentPanel, BorderLayout.NORTH);
		// end of first panel
		if (currentPane == "Reçu")
			Pattern.createClientBox(comboBox);
		else
			Pattern.createFournisseurBox(comboBox);

	};

	private class panelInitRP implements ActionListener {

		panelInitRP(JTextField[] tf) {
			super();
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			Object eventSource = e.getSource();
			if (eventSource == quitter) {
				frame.dispose();
			}

			if (currentPane == "Reçu") {
				if (eventSource == creer) {
					if(comboBox.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Pas de client choisi", "Champ Manquant", JOptionPane.WARNING_MESSAGE);
					else {
						creer.setEnabled(false);
					textFields[0].setText("" + (Transaction.noSerie + 1));
					textFields[1].setText(currentPane);
					textFields[2].setEditable(true);
					textFields[3].setEditable(true);
					textFields[3].setToolTipText("e.g: '10.0', '105'...");
					enregistrer.setEnabled(true);
					Pattern.createClientBox(comboBox);
					Filter.setListClientRecus(RPList, comboBox);
					}
					
				} // Creer button

				if (eventSource == enregistrer) {
					String dateString = textFields[2].getText();
					boolean validDate = Pattern.isDate(dateString);

					String montantText = textFields[3].getText();
					boolean validMontant = Pattern.isDouble(montantText);
					double montant = 0;
					Date date = null;

					if (validMontant) {
						montant = Double.parseDouble(montantText);
					} 
					else
						JOptionPane.showMessageDialog(null, "Le montant est incorrect!", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					if(validDate && !dateString.isEmpty()) {
						try {
							 date = Pattern.format.parse(dateString);
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",
									JOptionPane.WARNING_MESSAGE);					
						}
					}
					else if (dateString.isEmpty())
						JOptionPane.showMessageDialog(null, "Veuillez préciser la date d'issue du reçu",
								"Champ Obligatoire", JOptionPane.WARNING_MESSAGE);
					else if (montantText.isEmpty())
						JOptionPane.showMessageDialog(null, "Veuillez préciser un taux de montant",
								"Champ Obligatoire", JOptionPane.WARNING_MESSAGE);
					else if (!validDate) JOptionPane.showMessageDialog(null, "Le format de la date donnée est incorrect!", "Erreur",
								JOptionPane.ERROR_MESSAGE);
				
					if(validMontant && date != null) {
						Client c =  Filter.getClient(comboBox.getSelectedItem().toString());
						enregistrer.setEnabled(true);
						Recu r = new Recu(date, montant, c);
						r.description = textFields[1].getText();
						String etatCompte = bg.getSelection().getActionCommand();
						switch (etatCompte) {
						case "CASH":
							r.setTypePaie(TypePaie.CASH);
							break;
						case "CHEQUE":
							r.setTypePaie(TypePaie.CHEQUE);
							break;
						case "TRANSFERT":
							r.setTypePaie(TypePaie.TRANSFERT);
							break;
						}

						JOptionPane.showMessageDialog(null, "Reçu enregistré avec succès", "Enregistrement de la reçu",
								JOptionPane.INFORMATION_MESSAGE);
						for (int i = 0; i < textFields.length; i++)
							textFields[i].setText("");
						TransactionFiles.createRecu(r.noTransaction, r);
						enregistrer.setEnabled(false);
						creer.setEnabled(true);
						Filter.setListClientRecus(RPList, comboBox);
					}
				}
			}

			if (currentPane == "Paiement") {

				if (eventSource == creer) {
					if(comboBox.getSelectedItem() == null) JOptionPane.showMessageDialog(null, "Pas de fournisseur choisi", "Champ Manquant", JOptionPane.WARNING_MESSAGE);
					else {
						comboBox.setSelectedIndex(0);
					creer.setEnabled(false);
					textFields[0].setText("" + (Transaction.noSerie + 1));
					textFields[1].setText(currentPane);
					textFields[2].setEditable(true);
					textFields[3].setEditable(true);
					enregistrer.setEnabled(true);
					Pattern.createFournisseurBox(comboBox);
					Filter.setListFournisseurPaiements(RPList, comboBox);
					}
					
				}
					if (eventSource == enregistrer) {
						String dateString = textFields[2].getText();
						boolean validDate = Pattern.isDate(dateString);
						Date date = null;
						String montantText = textFields[3].getText();
						boolean validMontant = Pattern.isDouble(montantText);
						double montant = 0;
						
						if (validMontant) {
							montant = Double.parseDouble(montantText);
						} 
						else
							JOptionPane.showMessageDialog(null, "Le montant est incorrect!", "Erreur",
									JOptionPane.ERROR_MESSAGE);
						if(validDate && !dateString.isEmpty()) {
							try {
								 date = Pattern.format.parse(dateString);
							} catch (ParseException e1) {
								JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",
										JOptionPane.WARNING_MESSAGE);					
							}
						}
						else if (dateString.isEmpty())
							JOptionPane.showMessageDialog(null, "Veuillez préciser la date d'issue du paiement",
									"Champ Obligatoire", JOptionPane.WARNING_MESSAGE);
						else if (montantText.isEmpty())
							JOptionPane.showMessageDialog(null, "Veuillez préciser un taux de montant",
									"Champ Obligatoire", JOptionPane.WARNING_MESSAGE);
						else if (!validDate) JOptionPane.showMessageDialog(null, "Le format de la date donnée est incorrect!", "Erreur",
									JOptionPane.ERROR_MESSAGE);
					
						if(validMontant && date != null) {
							Fournisseur f =  Filter.getFournisseur(comboBox.getSelectedItem().toString());
							Paiement p = new Paiement(date, montant, f);
							p.description = textFields[1].getText();
							enregistrer.setEnabled(true);

							String etatCompte = bg.getSelection().getActionCommand();
							switch (etatCompte) {
							case "CASH":
								p.setTypePaie(TypePaie.CASH);
								break;
							case "CHEQUE":
								p.setTypePaie(TypePaie.CHEQUE);
								break;
							case "TRANSFERT":
								p.setTypePaie(TypePaie.TRANSFERT);
								break;
							}

							JOptionPane.showMessageDialog(null, "Paiement enregistré avec succès",
									"Enregistrement de la reçu", JOptionPane.INFORMATION_MESSAGE);
							for (int i = 0; i < textFields.length; i++)
								textFields[i].setText("");
							TransactionFiles.createPaiement(p.noTransaction, p);
							enregistrer.setEnabled(false);
							creer.setEnabled(true);
							Filter.setListFournisseurPaiements(RPList, comboBox);
						}
					}

				}
			}

		} // CreerClient
	private class itemSelected implements ActionListener {

		@Override

		public void actionPerformed(ActionEvent arg0) {
			if (currentPane == "Reçu") {
				Filter.setListClientRecus(RPList, comboBox);
			}
			
			if (currentPane == "Paiement") {
				Filter.setListFournisseurPaiements(RPList, comboBox);
			}

		}

	}
	}
