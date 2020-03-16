package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import com.company.Compte.EtatCompte;

public class CreatePanelCategorie extends JPanel{
	JFrame frame;
	JTextField textFields[];
	JButton creer, enregistrer, quitter;
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
		creer = new JButton("Créer"); creer.addActionListener(new initPanelCategorie(textFields));
		enregistrer = new JButton("Enregistrer"); enregistrer.addActionListener(new initPanelCategorie(textFields));
		quitter = new JButton("Quitter"); quitter.addActionListener(new initPanelCategorie());
	
		this.setLayout(new BorderLayout());

		DefaultTableModel tabmod = new DefaultTableModel();
		for(int i=0; i<20; i++) tabmod.addRow(new Object[] {null,null,null,null});
		tabmod.addColumn("No. Article");
		tabmod.addColumn("Nom Article");
		tabmod.addColumn("Quantité Stock");
		tabmod.addColumn("Prix Vente");
		tabmod.addColumn("Cout Achat");
		tabmod.addColumn("Profit");

		JTable table = new JTable(tabmod);
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.setFillsViewportHeight(true);
		table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		table.setShowGrid(true);
		table.setGridColor(Color.DARK_GRAY);
		table.setRowHeight(25);
		table.getTableHeader().setReorderingAllowed(false);
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
		
		
		
			JLabel noVente = new JLabel("No. " +ss+ ":");
			gbc.gridx = 0; gbc.gridy = 0;
			childDisplayPanel.add(noVente, gbc);
			
			gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 3;
			childDisplayPanel.add(textFields[0], gbc);
			
			JLabel description = new JLabel("Nom Catégorie: ");
			gbc.gridx = 0; gbc.gridy = 1;
			childDisplayPanel.add(description, gbc);
			gbc.gridx = 1 ; gbc.gridy = 1;
			childDisplayPanel.add(textFields[1], gbc);
	
		JLabel dateVente = new JLabel(" ");
		gbc.gridx = 0; gbc.gridy = 3;
		childDisplayPanel.add(dateVente, gbc);
		
	
		
		
		JLabel montantVente = new JLabel("");
		gbc.gridx = 0; gbc.gridy = 4;
		childDisplayPanel.add(montantVente, gbc);
		

		DefaultListModel listModel = new DefaultListModel();
		// forLoop arrayList into ListModel .setModel()
		JList achatsList = new JList(listModel);
		JScrollPane listScroller = new JScrollPane(achatsList);
		// listScroller.add
		listScroller.setPreferredSize(new Dimension(500, 500));
		achatsList.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), ss + "s",
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
		JTextField tf [];
		initPanelCategorie(JTextField[] tf) {
			super();
			this.tf = tf;
		}
		initPanelCategorie() {
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
	
			Object eventSource = e.getSource();
				if(eventSource == creer) {
					creer.setEnabled(false);
					textFields[0].setText(""+ (Categorie.noSerie+1));
					textFields[1].setEditable(true); 
					enregistrer.setEnabled(true);

				   } //Creer button 
				
				if(eventSource == quitter) {
					frame.dispose();
				}
				
				if(eventSource == enregistrer) {
					String name = textFields[1].getText();
					boolean nameErr = Main.isName(name);
					if(!nameErr && !name.isEmpty()) 
						JOptionPane.showMessageDialog(null, "Le champ 'Nom Catégorie' doit uniquement contenir des lettres!");
					else if(name.isEmpty()) JOptionPane.showMessageDialog(null, "Le champ 'Nom Catégorie' ne doit pas être vide!");
					else {
						Categorie c = new Categorie(name);
					enregistrer.setEnabled(true);
						JOptionPane.showMessageDialog(null,"Catégorie enregistrée avec succès", "Enregistrement du catégorie",JOptionPane.INFORMATION_MESSAGE);
						for(int i=0; i<textFields.length; i++) textFields[i].setText("");
						Files.categorieMap.put( c.noCategorie, c);
						enregistrer.setEnabled(false);
						creer.setEnabled(true);
					}
				}
			}
	}
}
