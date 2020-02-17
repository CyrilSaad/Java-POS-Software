package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class CreatePanelCompte extends JPanel {
	CreatePanelCompte(String ss) {

			JPanel parentPanel, buttonPanel;
			JLabel Ventes = new JLabel("");

			parentPanel = new JPanel();
			parentPanel.setLayout(new BorderLayout());

			JButton creer, enregistrer, quitter;
			
		DefaultComboBoxModel<Ville> villesmod = new DefaultComboBoxModel();
		JComboBox villes = new JComboBox(villesmod);
		villes.setEditable(true);
			villes.setPreferredSize(new Dimension(200, 25));
			creer = new JButton("Créer");
			enregistrer = new JButton("Enregistrer");
			quitter = new JButton("Quitter");
			
			JRadioButton actif = new JRadioButton("Actif", true);
			JRadioButton ferme = new JRadioButton("Fermé");
			JRadioButton suspendu = new JRadioButton("Suspendu");
			ButtonGroup bg = new ButtonGroup();
			bg.add(actif);
			bg.add(ferme);
			bg.add(suspendu);
		
			this.setLayout(new BorderLayout());

			DefaultTableModel tabmod = new DefaultTableModel();

			tabmod.addColumn("No Trs");
			tabmod.addColumn("Type Trs");
			tabmod.addColumn("Date Trs");
			tabmod.addColumn("Montant Trs");
			JTable table = new JTable(tabmod);
			table.setPreferredScrollableViewportSize(new Dimension(500, 300));
			table.setFillsViewportHeight(true);
			table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			table.setShowGrid(true);
			table.setGridColor(Color.blue);
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

			JTextField textFields[] = new JTextField[4];
			for (int i = 0; i < 4; i++) {
				textFields[i] = new JTextField(20);
				textFields[i].setPreferredSize(new Dimension(500, 25));
				if (i == 1 || i==2)
					continue;
				else
					textFields[i].setEditable(false);
			}
			JPanel pp = new JPanel();
			pp.setLayout(new FlowLayout(FlowLayout.LEFT));
			childDisplayPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(10, 10, 10, 10);
			
			
			
				JLabel noVente = new JLabel("No. Client:");
				gbc.gridx = 0; gbc.gridy = 0;
				childDisplayPanel.add(noVente, gbc);
				
				gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 3;
				childDisplayPanel.add(textFields[0], gbc);
				
				JLabel description = new JLabel("Nom Client: ");
				gbc.gridx = 0; gbc.gridy = 1;
				childDisplayPanel.add(description, gbc);
				gbc.gridx = 1 ; gbc.gridy = 1;
				childDisplayPanel.add(textFields[1], gbc);
		
			JLabel dateVente = new JLabel("Ville: ");
			gbc.gridx = 0; gbc.gridy = 3;
			childDisplayPanel.add(dateVente, gbc);
			
			gbc.gridx = 1 ; gbc.gridy = 3;
			childDisplayPanel.add(villes, gbc);
			
			
			JLabel montantVente = new JLabel("Solde: ");
			gbc.gridx = 0; gbc.gridy = 4;
			childDisplayPanel.add(montantVente, gbc);
			
			gbc.gridx = 1; gbc.gridy = 4;
			childDisplayPanel.add(textFields[3], gbc);
			
			JLabel etatCompte = new JLabel("Etat Compte: ");
			gbc.gridx = 0; gbc.gridy = 5;
			childDisplayPanel.add(etatCompte, gbc);
			
			JPanel buttonPan = new JPanel(); buttonPan.add(actif); buttonPan.add(ferme);buttonPan.add(suspendu);
			gbc.gridx = 1; gbc.gridy = 5;
			childDisplayPanel.add(buttonPan, gbc);
		
			
			
			DefaultListModel listModel = new DefaultListModel();
			// forLoop arrayList into ListModel .setModel()
			JList achatsList = new JList(listModel);
			JScrollPane listScroller = new JScrollPane(achatsList);
			// listScroller.add
			listScroller.setPreferredSize(new Dimension(500, 500));
			listScroller.setBorder(new EmptyBorder(0, 0, 10, 10));
			achatsList.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), ss + "s",
					TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
			
			pp.add(childDisplayPanel);
			
			displayPanel.add(pp); // add list to EAS
			displayPanel.add(listScroller);

			// this.add(listScroller, BorderLayout.EAST);
			this.setBorder(new EmptyBorder(10, 10, 10, 10));
			this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Infos " + ss + "s",
					TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

			this.add(scrollPane, BorderLayout.SOUTH);
			this.add(displayPanel, BorderLayout.CENTER);
			this.add(parentPanel, BorderLayout.NORTH);
			// end of first panel

		}
}
