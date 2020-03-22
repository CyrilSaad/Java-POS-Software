package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class CreateRapportPanelArticle extends JPanel {
	JRadioButton venteButton, achatButton;
	JFrame frame;
	String currentPane;
	JButton quitterButton, okButton;
	JTextField tf1, tf2;
	JTable table;
	DefaultTableModel tabmod;
	ButtonGroup bg;
	JComboBox comboBox = new JComboBox();
	JComboBox categorieBox = new JComboBox();

	CreateRapportPanelArticle(JFrame frame) {
		this.frame = frame;
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Rapport Articles",
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
		Pattern.createCategorieBox(categorieBox);
		categorieBox.setSelectedItem(null);
		comboBox.setEnabled(false);
		categorieBox.addItemListener(new ItemChangeListener());

		quitterButton = new JButton("Quitter");
		quitterButton.addActionListener(new rapportListener());
		okButton = new JButton("OK");
		okButton.addActionListener(new rapportListener());
		categorieBox.setPreferredSize(new Dimension(40, 30));
		boxPane.add(new JLabel("Catégorie Article", SwingConstants.CENTER));
		boxPane.add(categorieBox);
		gridPane.add(boxPane);

		JPanel parentPan = new JPanel();
		parentPan.setLayout(new GridLayout(1, 3));

		JLabel comboLabel = new JLabel("Article", SwingConstants.CENTER);
		JPanel comboPane = new JPanel();
		comboPane.setLayout(new GridLayout(2, 1));
		comboPane.add(comboLabel);
		comboPane.add(comboBox);
		parentPan.add(comboPane);

		JPanel radioPan = new JPanel();
		radioPan.setLayout(new GridLayout(2, 1));

		bg = new ButtonGroup();
		venteButton = new JRadioButton("Vente");
		bg.add(venteButton);
		achatButton = new JRadioButton("Achat");
		bg.add(achatButton);
		JPanel tempan = new JPanel();
		JLabel emp = new JLabel("     ");
		tempan.add(venteButton);
		tempan.add(achatButton);
		radioPan.add(emp);
		radioPan.add(tempan);
		parentPan.add(radioPan);

		JPanel buttonPan = new JPanel();
		buttonPan.setLayout(new GridLayout(2, 1));
		quitterButton = new JButton("Quitter");
		okButton = new JButton("OK");
		quitterButton.addActionListener(new rapportListener());
		okButton.addActionListener(new rapportListener());
		buttonPan.add(okButton);
		buttonPan.add(quitterButton);
		parentPan.add(buttonPan);

		northPan.add(gridPane);
		northPan.add(parentPan);

		// tablePan
		String[] columns = { "No Trs", "Descrip Trs", "Date Trs", "Catégorie", "Article", "Quantité", "Prix/Cout",
				"Total" };
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

				DefaultTableModel tabmod = (DefaultTableModel) table.getModel();
				for (int i = 0; i < table.getRowCount(); i++) {
					tabmod.setValueAt(null, i, 0);
					tabmod.setValueAt(null, i, 1);
					tabmod.setValueAt(null, i, 2);
					tabmod.setValueAt(null, i, 3);
					tabmod.setValueAt(null, i, 4);
					tabmod.setValueAt(null, i, 5);
					tabmod.setValueAt(null, i, 6);
					tabmod.setValueAt(null, i, 7);
				}
				String dateDebutString, dateFinString;
				String typeTransaction = null;
				if (venteButton.isSelected())
					typeTransaction = "Vente";
				if (achatButton.isSelected())
					typeTransaction = "Achat";
				dateDebutString = tf1.getText();
				dateFinString = tf2.getText();
				boolean validDateDebut = Pattern.isDate(dateDebutString);
				boolean validDateFin = Pattern.isDate(dateFinString);
				boolean categorySelected = categorieBox.getSelectedIndex() == -1;
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
					JOptionPane.showMessageDialog(null, "Veuillez préciser une date!", "Champ Obligatoire",
							JOptionPane.WARNING_MESSAGE);

				else if ((!validDateDebut && !dateDebutString.isEmpty())
						|| (!validDateFin && !dateFinString.isEmpty())) {
					JOptionPane.showMessageDialog(null, "Le format de la date n'est pas valide", "Date invalide",
							JOptionPane.ERROR_MESSAGE);
				} 
				 if (categorySelected)
					JOptionPane.showMessageDialog(null, "Choisissez une article!", "Champ Obligatoire",
							JOptionPane.WARNING_MESSAGE);
				// sort by date for Ventes // end case Client
				System.out.println(categorySelected);

				if (validDateDebut && validDateFin && debut != null && fin != null && !categorySelected) {

					Article a = Filter.getArticle(comboBox.getSelectedItem().toString());
					ArrayList<DetailVente> ventes = Filter.getArticleDetailVentes(a);
					ArrayList<DetailAchat> achats = Filter.getArticleDetailAchats(a);

					ArrayList<DetailVente> ventesFiltered = new ArrayList<DetailVente>();
					ArrayList<DetailAchat> achatsFiltered = new ArrayList<DetailAchat>();

					Date datePaiement = new Date();
					Date dateAchat = new Date();

					for (int i = 0; i < ventes.size(); i++) {
						datePaiement = ventes.get(i).vente.dateTransaction;
						if (!debut.after(datePaiement) && !fin.before(datePaiement))
							ventesFiltered.add(ventes.get(i));
					}

					for (int i = 0; i < achats.size(); i++) {
						dateAchat = achats.get(i).achat.dateTransaction;
						if (!debut.after(dateAchat) && !fin.before(dateAchat))
							achatsFiltered.add(achats.get(i));
					}

					if (venteButton.isSelected()) {

						Collections.sort(ventesFiltered);
						for (int i = 0; i < ventesFiltered.size(); i++) {

							DetailVente item = ventesFiltered.get(i);
							tabmod.setValueAt(item.vente.noTransaction, i, 0);
							tabmod.setValueAt(item.vente.description, i, 1);
							tabmod.setValueAt(Pattern.format.format(item.vente.dateTransaction), i, 2);
							tabmod.setValueAt(item.article.categorie, i, 3);
							tabmod.setValueAt(item.article, i, 4);
							tabmod.setValueAt(item.quantite, i, 5);
							tabmod.setValueAt(item.article.coutAchatParUnite, i, 6);
							tabmod.setValueAt(item.calculerMontant(), i, 7);

						} // end loop

					} // end Paiement Filtering

					else if (achatButton.isSelected()) {

						Collections.sort(achatsFiltered);
						for (int i = 0; i < achatsFiltered.size(); i++) {

							DetailAchat item = achatsFiltered.get(i);
							tabmod.setValueAt(item.achat.noTransaction, i, 0);
							tabmod.setValueAt(item.achat.description, i, 1);
							tabmod.setValueAt(Pattern.format.format(item.achat.dateTransaction), i, 2);
							tabmod.setValueAt(item.article.categorie, i, 3);
							tabmod.setValueAt(item.article, i, 4);
							tabmod.setValueAt(item.quantite, i, 5);
							tabmod.setValueAt(item.article.coutAchatParUnite, i, 6);
							tabmod.setValueAt(item.calculerMontant(), i, 7);

						} // end loop
					} // end Achat Filtering
					else {

						int j = 0;
						for (int i = 0; i < ventesFiltered.size(); i++) {

							DetailVente item = ventesFiltered.get(i);
							tabmod.setValueAt(item.vente.noTransaction, i, 0);
							tabmod.setValueAt(item.vente.description, i, 1);
							tabmod.setValueAt(Pattern.format.format(item.vente.dateTransaction), i, 2);
							tabmod.setValueAt(item.article.categorie, i, 3);
							tabmod.setValueAt(item.article, i, 4);
							tabmod.setValueAt(item.quantite, i, 5);
							tabmod.setValueAt(item.article.coutAchatParUnite, i, 6);
							tabmod.setValueAt(item.calculerMontant(), i, 7);

						}

						for (int i = 0; i < achatsFiltered.size(); i++) {

							DetailAchat item = achatsFiltered.get(i);
							tabmod.setValueAt(item.achat.noTransaction, i, 0);
							tabmod.setValueAt(item.achat.description, i, 1);
							tabmod.setValueAt(Pattern.format.format(item.achat.dateTransaction), i, 2);
							tabmod.setValueAt(item.article.categorie, i, 3);
							tabmod.setValueAt(item.article, i, 4);
							tabmod.setValueAt(item.quantite, i, 5);
							tabmod.setValueAt(item.article.coutAchatParUnite, i, 6);
							tabmod.setValueAt(item.calculerMontant(), i, 7);

						}

					}

				}

			}

		}
	}

	private class ItemChangeListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			comboBox.setEnabled(true);
			DefaultComboBoxModel mod = (DefaultComboBoxModel) comboBox.getModel();
			mod.removeAllElements();
			if (event.getStateChange() == ItemEvent.SELECTED) {
				Categorie item = Filter.getCategorie(categorieBox.getSelectedItem().toString());
				ArrayList<Article> articles = Filter.getCategorieArticles(item);
				for (int i = 0; i < articles.size(); i++) {
					mod.addElement(articles.get(i).nomArticle + "(" + articles.get(i).noArticle + ")");
				}

				// do something with object
			}
		}
	}
}
