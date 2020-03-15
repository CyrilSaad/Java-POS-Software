package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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

public class CreatePanelArticle extends JPanel {
	JFrame frame;
	CreatePanelArticle(String ss, JFrame frame) {

		this.frame = frame;
		JPanel parentPanel, buttonPanel;
		JLabel Ventes = new JLabel("");
		
		parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());

		JButton creer, enregistrer, quitter;
		JComboBox combobox = new JComboBox(); combobox.setEditable(false);
		combobox.setPreferredSize(new Dimension(200, 25));
		creer = new JButton("Créer");
		enregistrer = new JButton("Enregistrer");
		quitter = new JButton("Quitter");
	
		this.setLayout(new BorderLayout());

		DefaultTableModel tabmod = new DefaultTableModel();

		tabmod.addColumn("No Trs");
		tabmod.addColumn("Type Trs");
		tabmod.addColumn("Date Trs");
		tabmod.addColumn("Quantité");
		tabmod.addColumn("Prix/Cout");
		tabmod.addColumn("Total");

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
		JPanel cbPan = new JPanel();
		JLabel choirsirClient = new JLabel("Choisir catégorie: ");
		cbPan.add(choirsirClient);
		cbPan.add(combobox);
		buttonPanel.add(cbPan, BorderLayout.EAST);
		parentPanel.add(buttonPanel, BorderLayout.CENTER); // EndOfFirstPart

		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new GridLayout(1, 2));
		JPanel childDisplayPanel = new JPanel();

		JTextField textFields[] = new JTextField[6];
		for (int i = 0; i < 6; i++) {
			textFields[i] = new JTextField(20);
			textFields[i].setPreferredSize(new Dimension(500, 25));
			if (i == 1 || i== 5)
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
		
		
		
			JLabel noVente = new JLabel("No. " +ss+ ":");
			gbc.gridx = 0; gbc.gridy = 0;
			childDisplayPanel.add(noVente, gbc);
			
			gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 3;
			childDisplayPanel.add(textFields[0], gbc);
			
			JLabel description = new JLabel("Nom Article: ");
			gbc.gridx = 0; gbc.gridy = 1;
			childDisplayPanel.add(description, gbc);
			gbc.gridx = 1 ; gbc.gridy = 1;
			childDisplayPanel.add(textFields[1], gbc);
	
		JLabel dateVente = new JLabel("Quantité Stock:");
		gbc.gridx = 0; gbc.gridy = 3;
		childDisplayPanel.add(dateVente, gbc);
		
		gbc.gridx = 1 ; gbc.gridy = 3;
		childDisplayPanel.add(textFields[2], gbc);
		
		
		
		JLabel montantVente = new JLabel("Prix Vente:");
		gbc.gridx = 0; gbc.gridy = 4;
		childDisplayPanel.add(montantVente, gbc);
		
		gbc.gridx = 1; gbc.gridy = 4;
		childDisplayPanel.add(textFields[3], gbc);
		
		JLabel coutAchat = new JLabel("Cout Achat:");
		gbc.gridx = 0; gbc.gridy = 5;
		childDisplayPanel.add(coutAchat, gbc);
		
		gbc.gridx = 1; gbc.gridy = 5;
		childDisplayPanel.add(textFields[4], gbc);
		
		JLabel profit = new JLabel("Profit:");
		gbc.gridx = 0; gbc.gridy = 6;
		childDisplayPanel.add(profit, gbc);
		
		gbc.gridx = 1; gbc.gridy = 6;
		childDisplayPanel.add(textFields[5], gbc);

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
}
