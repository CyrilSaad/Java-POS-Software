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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class CreatePanelVA extends JPanel {
	JButton creer, enregistrer, quitter;
	JFrame frame;
	String currentPane;
	JTextField textFields[];
	JComboBox comboBox = new JComboBox();
	JList achatsList = new JList();
	JTable table = new JTable();
	TableColumn boxColumn;
	String changedDate;
	boolean validQty, validMontant = false;

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
		enregistrer.addActionListener(new panelInitVA());
		enregistrer.setEnabled(false);
		quitter = new JButton("Quitter");
		creer.addActionListener(new panelInitVA());
		quitter.addActionListener(new panelInitVA());
		this.setLayout(new BorderLayout());

		DefaultTableModel tabmod = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column)

			{
				if (column == 3) {
					TableColumn tm = table.getColumnModel().getColumn(3);
					tm.setCellRenderer(new ColorColumnRenderer(Color.lightGray, Color.darkGray));
				}

				return column == 2 || column == 1 || column == 0 ? true : false;
			}

		};
		tabmod.addColumn("Article");
		tabmod.addColumn("Quantité");
		tabmod.addColumn(s + " Unit");
		tabmod.addColumn(s + " Total");
		table = Pattern.createTable(tabmod);
		boxColumn = table.getColumnModel().getColumn(0);
//		table.setEnabled(false);
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
		if (currentPane == "Vente") {
			Pattern.createClientBox(comboBox);
			Filter.setListClientVentes(achatsList, comboBox);
		}
		if (currentPane == "Achat") {
			Pattern.createFournisseurBox(comboBox);
			Filter.setListFournisseurAchats(achatsList, comboBox);
		}
		achatsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				achatsListValueChanged(evt);
			}

			private void achatsListValueChanged(ListSelectionEvent evt) {
				// TODO Auto-generated method stub

				for (int i = 0; i < tabmod.getRowCount(); i++) {
					tabmod.setValueAt(null, i, 0);
					tabmod.setValueAt(null, i, 1);
					tabmod.setValueAt(null, i, 2);
					tabmod.setValueAt(null, i, 3);
				}
//				enregistrer.setEnabled(true);
				textFields[2].setEditable(true);

				textFields[2].addFocusListener(new FocusListener() {
					@Override
					public void focusGained(FocusEvent e) {
					}

					@Override
					public void focusLost(FocusEvent arg0) {
						// TODO Auto-generated method stub
						changedDate = textFields[2].getText();
					}

				});

				if (currentPane == "Vente") {
					if (!achatsList.getValueIsAdjusting() && !achatsList.isSelectionEmpty()) {
						Vente item = (Vente) achatsList.getSelectedValue();
						creer.setEnabled(true);
						enregistrer.setEnabled(true);
						textFields[0].setText(item.noTransaction + "");
						textFields[1].setText(item.description);
						textFields[2].setText("");
						textFields[3].setText(item.montant + "");
					}
					
				}

				if (currentPane == "Achat") {
					if (!achatsList.getValueIsAdjusting() && !achatsList.isSelectionEmpty()) {
						Achat item = (Achat) achatsList.getSelectedValue();
						creer.setEnabled(true);
						textFields[0].setText(item.noTransaction + "");
						textFields[1].setText(item.description);
						textFields[2].setText("");
						textFields[3].setText(item.montant + "");
					}
				}

			}
		});
	}

	private class panelInitVA implements ActionListener {
		private JComboBox box = new JComboBox();

		@Override
		public void actionPerformed(ActionEvent e) {
			Object eventSource = e.getSource();
			if (eventSource == quitter) {
				frame.dispose();
			}

			if (currentPane == "Vente") {

				if (eventSource == creer) {

					if (comboBox.getSelectedItem() == null)
						JOptionPane.showMessageDialog(null, "Pas de client choisi", "Champ Manquant",
								JOptionPane.WARNING_MESSAGE);
					else {
						Filter.setListClientVentes(achatsList, comboBox);
						comboBox.setSelectedIndex(0);
						creer.setEnabled(false);
						textFields[0].setText("" + (Transaction.noSerie + 1));
						textFields[1].setText(currentPane);
						textFields[2].setEditable(true);
						textFields[3].setText("" + 0);
						enregistrer.setEnabled(true);
						Pattern.createClientBox(comboBox);
						Filter.setListClientVentes(achatsList, comboBox);
						Pattern.createArticleBox(box);

						table.setEnabled(true);
						boxColumn.setCellEditor(new DefaultCellEditor(box));

						table.getModel().addTableModelListener(new TableModelListener() {
							@Override
							public void tableChanged(TableModelEvent e) {
								try {
									Article item = Filter.getArticle(table.getValueAt(e.getLastRow(), 0).toString());
									int qty = 0;
									double price = 0;

									if (e.getColumn() == 0) {
										if (item == null) {
											for (int i = 0; i < table.getColumnCount(); i++)
												table.setValueAt(null, e.getLastRow(), i++); // In case of clicking on
																								// empty
																								// cell,
																								// handles null pointer
																								// exception
										}
										if (item != null) {
											table.setValueAt(item.qteStock, e.getLastRow(), 1);
											table.setValueAt(item.prixVenteParUnite, e.getLastRow(), 2);
											qty = Integer.parseInt(table.getValueAt(e.getLastRow(), 1).toString());
											price = Double.parseDouble(table.getValueAt(e.getLastRow(), 2).toString());
											table.setValueAt((qty * price), e.getLastRow(), 3);
										}

									} // edit row on Article change

									if (e.getColumn() == 1 || e.getColumn() == 2) {
										String montantText = table.getValueAt(e.getLastRow(), 2).toString();
										validMontant = Pattern.isDouble(montantText);
										String quantityText = table.getValueAt(e.getLastRow(), 1).toString();
										validQty = Pattern.isNumeric(quantityText);
										boolean qtySufficient = false;

										if (validQty) {
											int qtyStock = Integer.parseInt(quantityText);
											if (qtyStock <= item.qteStock)
												qtySufficient = true;
										}

										if (!qtySufficient) {
											JOptionPane.showMessageDialog(null, "\r\n" + "Il n'y a pas assez de stock",
													"Stock Insuffisant", JOptionPane.WARNING_MESSAGE);
											table.setValueAt(0, e.getLastRow(), 1);
										}
										System.out.println(qtySufficient);
										if (!(validMontant || validQty) && qtySufficient) {
											JOptionPane.showMessageDialog(null, "Numéro non valide!", "Erreur",
													JOptionPane.ERROR_MESSAGE);
											table.setValueAt(0, e.getLastRow(), 2);
											table.setValueAt(0, e.getLastRow(), 1);
											table.setValueAt(0, e.getLastRow(), 3);
										}
										if (validMontant && validQty && qtySufficient) {
											price = Double.parseDouble(montantText);
											qty = Integer.parseInt(quantityText);
											table.setValueAt((qty * price), e.getLastRow(), 3);
											textFields[3].setText(qty * price + "");
										}

									}

								} catch (Exception ex) {
								}

							}
						});
					}

				} // Creer button

				if (eventSource == enregistrer) {
					String dateString = textFields[2].getText();
					boolean validDate = Pattern.isDate(dateString);
					Client c = Filter.getClient(comboBox.getSelectedItem().toString());
					double montant = Double.parseDouble(textFields[3].getText());
					Date date = null;
					boolean montantValide = textFields[3].getText().equals("0");
					if (validDate && !dateString.isEmpty()) {
						try {
							date = Pattern.format.parse(dateString);
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide",
									"Date invalide", JOptionPane.WARNING_MESSAGE);
						}
					}
					if (montantValide && !dateString.isEmpty())
						JOptionPane.showMessageDialog(null, "Choisisez un article!", "Champ invalide",
								JOptionPane.ERROR_MESSAGE);
					else if (dateString.isEmpty())
						JOptionPane.showMessageDialog(null, "Veuillez préciser la date de la vente",
								"Champ Obligatoire", JOptionPane.WARNING_MESSAGE);

					else if (!validDate && !dateString.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",
								JOptionPane.ERROR_MESSAGE);
					}

					if (validDate && montant > 0 && achatsList.isSelectionEmpty()) {
						Vente v = new Vente(date, montant, c);
						int qty = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 1).toString());
						Article article = Filter.getArticle(table.getValueAt(table.getSelectedRow(), 0).toString());
						DetailVente detailVente = new DetailVente(article, v, qty);
						article.prixVenteParUnite = montant / qty;
						v.description = currentPane;
						for (int i = 0; i < textFields.length; i++)
							textFields[i].setText("");

						JOptionPane.showMessageDialog(null, "Compte enregistré avec succès", "Enregistrement du compte",
								JOptionPane.INFORMATION_MESSAGE);
						v.MiseAJourCompte(montant);
						article.sortieStock(qty, montant);
						TransactionFiles.createVente(v.noTransaction, v);
						TransactionFiles.createDetailVente(detailVente);
						enregistrer.setEnabled(false);
						creer.setEnabled(true);
					}

					if (!achatsList.isSelectionEmpty()) {
						Date newDate = null;
						boolean validNewDate = Pattern.isDate(changedDate);
						if (validNewDate) {
							try {
								newDate = Pattern.format.parse(changedDate);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} // end date formatting
						Vente venteSelected = (Vente) achatsList.getSelectedValue();
						TransactionFiles.updateVente(venteSelected.noTransaction, newDate);
						creer.setEnabled(true);
						enregistrer.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Date modifiée", "Modification",JOptionPane.INFORMATION_MESSAGE);
					}

					Filter.setListClientVentes(achatsList, comboBox);

				}
			} // CreerClient

			if (currentPane == "Achat") {
				Article item;
				if (eventSource == creer) {
					creer.setEnabled(false);
					if (comboBox.getSelectedItem() == null)
						JOptionPane.showMessageDialog(null, "Pas de fournisseur choisi", "Champ Manquant",
								JOptionPane.WARNING_MESSAGE);
					else {
						comboBox.setSelectedIndex(0);
						textFields[0].setText("" + (Transaction.noSerie + 1));
						textFields[1].setText(currentPane);
						textFields[2].setEditable(true);
						textFields[3].setText("" + 0);
						enregistrer.setEnabled(true);
						Pattern.createFournisseurBox(comboBox);
						Filter.setListFournisseurAchats(achatsList, comboBox);
						Pattern.createArticleBox(box);
						table.setEnabled(true);
						boxColumn.setCellEditor(new DefaultCellEditor(box));

						table.getModel().addTableModelListener(new TableModelListener() {
							@Override
							public void tableChanged(TableModelEvent e) {
								try {
									Article item = Filter.getArticle(table.getValueAt(e.getLastRow(), 0).toString());
									int qty = 0;
									double price = 0;

									if (e.getColumn() == 0) {
										if (item == null) {
											for (int i = 0; i < table.getColumnCount(); i++)
												table.setValueAt(null, e.getLastRow(), i++); // In case of clicking on
																								// empty
																								// cell,
																								// handles null pointer
																								// exception
										}
										if (item != null) {
											table.setValueAt(item.qteStock, e.getLastRow(), 1);
											table.setValueAt(item.coutAchatParUnite, e.getLastRow(), 2);
											qty = Integer.parseInt(table.getValueAt(e.getLastRow(), 1).toString());
											price = Double.parseDouble(table.getValueAt(e.getLastRow(), 2).toString());
											table.setValueAt((qty * price), e.getLastRow(), 3);
										}

									} // edit row on Article change

									if (e.getColumn() == 1 || e.getColumn() == 2) {
										String montantText = table.getValueAt(e.getLastRow(), 2).toString();
										validMontant = Pattern.isDouble(montantText);
										String quantityText = table.getValueAt(e.getLastRow(), 1).toString();
										validQty = Pattern.isNumeric(quantityText);

										if (!validMontant | !validQty) {
											JOptionPane.showMessageDialog(null, "Numéro non valide!", "Erreur",
													JOptionPane.ERROR_MESSAGE);
											table.setValueAt(0, e.getLastRow(), 1);
											table.setValueAt(0, e.getLastRow(), 2);
											table.setValueAt(0, e.getLastRow(), 3);
										}

										if (validMontant && validQty) {
											price = Double.parseDouble(montantText);
											qty = Integer.parseInt(quantityText);
											table.setValueAt((qty * price), e.getLastRow(), 3);
											textFields[3].setText(qty * price + "");
										}

									}

								} catch (Exception ex) {
								}

							}
						});
					}

				} // Creer button

				if (eventSource == enregistrer) {

					String dateString = textFields[2].getText();
					boolean validDate = Pattern.isDate(dateString);
					double montant = Double.parseDouble(textFields[3].getText());
					Fournisseur f = Filter.getFournisseur(comboBox.getSelectedItem().toString());
					Date date = null;

					if (validDate && !dateString.isEmpty()) {
						try {
							date = Pattern.format.parse(dateString);
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide",
									"Date invalide", JOptionPane.WARNING_MESSAGE);
						}
					}

					else if (dateString.isEmpty())
						JOptionPane.showMessageDialog(null, "Veuillez préciser la date de l'achat", "Champ Obligatoire",
								JOptionPane.WARNING_MESSAGE);

					else if (!validDate && !dateString.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",
								JOptionPane.ERROR_MESSAGE);
					}

					if ((montant <= 0d || textFields[3].getText() == "0") && !dateString.isEmpty())
						JOptionPane.showMessageDialog(null, "Choisisez un article!", "Champ invalide",
								JOptionPane.ERROR_MESSAGE);
					if (validDate && montant > 0 && achatsList.isSelectionEmpty()) {
						Achat a = new Achat(date, montant, f);
						Article article = Filter.getArticle(table.getValueAt(table.getSelectedRow(), 0).toString());
						int qty = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 1).toString());
						article.coutAchatParUnite = montant / qty;
						DetailAchat detailAchat = new DetailAchat(article, a, qty);
						a.description = currentPane;
						creer.setEnabled(true);
						JOptionPane.showMessageDialog(null, "Compte enregistré avec succès", "Enregistrement du compte",
								JOptionPane.INFORMATION_MESSAGE);
						a.MiseAJourCompte(montant);
						for (int i = 0; i < textFields.length; i++)
							textFields[i].setText("");
						article.entreeStock(qty, montant);
						TransactionFiles.createAchat(a.noTransaction, a);
						TransactionFiles.createDetailAchat(detailAchat);
						enregistrer.setEnabled(false);

					}
					
					if (!achatsList.isSelectionEmpty()) {
						Date newDate = null;
						boolean validNewDate = Pattern.isDate(changedDate);
						if (validNewDate) {
							try {
								newDate = Pattern.format.parse(changedDate);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} // end date formatting
						Achat achatSelected = (Achat) achatsList.getSelectedValue();
						TransactionFiles.updateAchat(achatSelected.noTransaction, newDate);
						creer.setEnabled(true);
						enregistrer.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Date modifiée", "Modification",JOptionPane.INFORMATION_MESSAGE);
					}

			
					Filter.setListFournisseurAchats(achatsList, comboBox);
				} //fin Enregister

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
