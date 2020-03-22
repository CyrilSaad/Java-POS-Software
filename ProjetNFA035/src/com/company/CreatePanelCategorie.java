package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class CreatePanelCategorie extends JPanel {
	JFrame frame;
	JTextField textFields[];
	JButton creer, enregistrer, quitter;
	ButtonGroup bg;
	JList articleList;
	JTable table = new JTable();
	boolean validQty, validMontant = false;
	boolean validPrice, validCout;
	CreatePanelCategorie(String ss, JFrame frame) {
		this.frame = frame;
		JPanel parentPanel, buttonPanel;
		JLabel Ventes = new JLabel("");

		parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());

		DefaultComboBoxModel cbmod = new DefaultComboBoxModel();
		JComboBox combobox = new JComboBox(cbmod);
		combobox.setEditable(false);
		combobox.setPreferredSize(new Dimension(200, 25));
		creer = new JButton("Cr�er");
		creer.addActionListener(new initPanelCategorie());
		enregistrer = new JButton("Enregistrer");
		enregistrer.addActionListener(new initPanelCategorie());
		enregistrer.setEnabled(false);
		quitter = new JButton("Quitter");
		quitter.addActionListener(new initPanelCategorie());

		this.setLayout(new BorderLayout());

		DefaultTableModel tabmod = new DefaultTableModel() {
			
			    @Override 
			    public boolean isCellEditable(int row, int column)
			    {
			    	 return column == 2 || column==4 ? true : false;
			    }
			
		};
		for (int i = 0; i < 20; i++)
			tabmod.addRow(new Object[] { null, null, null, null });
		tabmod.addColumn("No. Article");
		tabmod.addColumn("Nom Article");
		tabmod.addColumn("Quantit� Stock");
		tabmod.addColumn("Prix Vente");
		tabmod.addColumn("Cout Achat");
		tabmod.addColumn("Profit");

		table = Pattern.createTable(tabmod);
		
		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				try {
					Article item = Filter.getArticle(combobox.getSelectedItem().toString());
					int qty = 0;
					double price = 0;

					if (e.getColumn() == 0) {
						if (item == null) {
							for (int i = 0; i < table.getColumnCount(); i++)
								table.setValueAt(null, e.getLastRow(), i++); 
						}
						if (item != null) {
							
							table.setValueAt(item.noArticle, e.getLastRow(), 0);
							table.setValueAt(item.nomArticle, e.getLastRow(), 1);
							table.setValueAt(item.qteStock, e.getLastRow(), 2);
							table.setValueAt(item.prixVenteParUnite, e.getLastRow(), 3);
							table.setValueAt(item.coutAchatParUnite, e.getLastRow(), 4);
							table.setValueAt(item.tauxProfit*100 +"%", e.getLastRow(), 5);
							qty = Integer.parseInt(table.getValueAt(e.getLastRow(), 2).toString());
							price = Double.parseDouble(table.getValueAt(e.getLastRow(), 3).toString());
						}

					} // edit row on Article change

					if (e.getColumn() == 2 || e.getColumn() == 4) {
						String montantText = table.getValueAt(e.getLastRow(), 4).toString();
						validMontant = Pattern.isDouble(montantText);
						String quantityText = table.getValueAt(e.getLastRow(), 2).toString();
						validQty = Pattern.isNumeric(quantityText);

						if (!validMontant | !validQty) {
							JOptionPane.showMessageDialog(null, "Num�ro non valide!", "Erreur",
									JOptionPane.ERROR_MESSAGE);
							table.setValueAt(0, e.getLastRow(), 1);
							table.setValueAt(0, e.getLastRow(), 2);
							table.setValueAt(0, e.getLastRow(), 3);
						}

						if (validMontant && validQty) {
							price = Double.parseDouble(montantText);
							qty = Integer.parseInt(quantityText);
							double sellingPrice = price + price*item.tauxProfit;
							table.setValueAt(sellingPrice, e.getLastRow(), 3);
//							textFields[3].setText(qty * price + "");
						}

					}

				} catch (Exception ex) {
				}

			}
		});
		
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

		JLabel description = new JLabel("Nom Cat�gorie: ");
		gbc.gridy = 1; gbc.gridx = 0;
		childDisplayPanel.add(description, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		childDisplayPanel.add(textFields[1], gbc);

		JLabel dateVente = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		childDisplayPanel.add(dateVente, gbc);

		JLabel montantVente = new JLabel("");
		gbc.gridx = 0;
		gbc.gridy = 4;
		childDisplayPanel.add(montantVente, gbc);

		DefaultListModel listModel = new DefaultListModel();
		// forLoop arrayList into ListModel .setModel()
		articleList = new JList(listModel);
		articleList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				articleListValueChanged(evt);
			}
		});
		JScrollPane listScroller = new JScrollPane(articleList);
		// listScroller.add
		listScroller.setPreferredSize(new Dimension(500, 500));
		articleList.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), ss + "s",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

		pp.add(childDisplayPanel);

		displayPanel.add(pp); // add list to EAS
		displayPanel.add(listScroller);

		listScroller.setBorder(new EmptyBorder(0, 0, 10, 10));
		// this.add(listScroller, BorderLayout.EAST);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Infos " + ss + "s",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

		this.add(scrollPane, BorderLayout.SOUTH);
		this.add(displayPanel, BorderLayout.CENTER);
		this.add(parentPanel, BorderLayout.NORTH);
		// end of first panel

	}

	private class initPanelCategorie implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object eventSource = e.getSource();
			if (eventSource == creer) {
				creer.setEnabled(false);
				textFields[0].setText("" + (Categorie.noSerie + 1));
				textFields[1].setEditable(true);
				enregistrer.setEnabled(true);
				Filter.setListCategorie(articleList);

			} // Creer button

			if (eventSource == quitter) {
				frame.dispose();
			}

			if (eventSource == enregistrer) {
				if (articleList.isSelectionEmpty()) {

					String name = textFields[1].getText();
					boolean nameErr = Main.isName(name);
					if (!nameErr && !name.isEmpty())
						JOptionPane.showMessageDialog(null,
								"Le champ 'Nom Cat�gorie' doit uniquement contenir des lettres!");
					else if (name.isEmpty())
						JOptionPane.showMessageDialog(null, "Le champ 'Nom Cat�gorie' ne doit pas �tre vide!",
								"Champ Obligatoire", JOptionPane.WARNING_MESSAGE);
					else {
						Categorie c = new Categorie(name);
						enregistrer.setEnabled(true);
						JOptionPane.showMessageDialog(null, "Cat�gorie enregistr�e avec succ�s",
								"Enregistrement du cat�gorie", JOptionPane.INFORMATION_MESSAGE);
						for (int i = 0; i < textFields.length; i++)
							textFields[i].setText("");
						Files.createCategorie(c.noCategorie, c);
						enregistrer.setEnabled(false);
						creer.setEnabled(true);
					}
				} 
				else {
					
				}
			} // end enregis

			Filter.setListCategorie(articleList);
		}
	}

	private void articleListValueChanged(ListSelectionEvent evt) {

		DefaultTableModel tabmod = (DefaultTableModel) table.getModel();
		for (int i = 0; i < tabmod.getRowCount(); i++) {
			tabmod.setValueAt(null, i, 0);
			tabmod.setValueAt(null, i, 1);
			tabmod.setValueAt(null, i, 2);
			tabmod.setValueAt(null, i, 3);
			tabmod.setValueAt(null, i, 4);
			tabmod.setValueAt(null, i, 5);
		}
		if (!articleList.getValueIsAdjusting()) {
			Categorie c = (Categorie) articleList.getSelectedValue();
			ArrayList<Article> articles = Filter.getCategorieArticles(c);

			for (int i = 0; i < articles.size(); i++) {
				Article item = articles.get(i);
				tabmod.setValueAt(item.noArticle, i, 0);
				tabmod.setValueAt(item.nomArticle, i, 1);
				tabmod.setValueAt(item.qteStock, i, 2);
				tabmod.setValueAt(item.prixVenteParUnite, i, 3);
				tabmod.setValueAt(item.coutAchatParUnite, i, 4);
				tabmod.setValueAt(item.tauxProfit * 100 + "%", i, 5);

			}
		}

	}
}
