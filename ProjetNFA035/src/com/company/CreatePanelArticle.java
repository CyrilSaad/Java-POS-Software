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

import javax.swing.BorderFactory;
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

public class CreatePanelArticle extends JPanel {
	JFrame frame;
	JTextField textFields[];
	JButton creer, enregistrer, quitter;
	CreatePanelArticle(String ss, JFrame frame) {

		this.frame = frame;
		JPanel parentPanel, buttonPanel;
		JLabel Ventes = new JLabel("");
		
		parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());

		JComboBox combobox = new JComboBox(); combobox.setEditable(false);
		combobox.setPreferredSize(new Dimension(200, 25));
		creer = new JButton("Créer"); creer.addActionListener(new initPanelArticle(textFields));
		enregistrer = new JButton("Enregistrer"); enregistrer.setEnabled(false); enregistrer.addActionListener(new initPanelArticle(textFields));
		quitter = new JButton("Quitter"); quitter.addActionListener(new initPanelArticle());
	
		this.setLayout(new BorderLayout());

		DefaultTableModel tabmod = new DefaultTableModel();

		tabmod.addColumn("No Trs");
		tabmod.addColumn("Type Trs");
		tabmod.addColumn("Date Trs");
		tabmod.addColumn("Quantité");
		tabmod.addColumn("Prix/Cout");
		tabmod.addColumn("Total");
		for(int i=0; i<20; i++) tabmod.addRow(new Object[] {null,null,null,null});
		JTable table = new JTable(tabmod);
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.setFillsViewportHeight(true);
		table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		table.setShowGrid(true);
		table.setGridColor(Color.DARK_GRAY);
		table.setRowHeight(25);
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

		 textFields = new JTextField[6];
		for (int i = 0; i < 6; i++) {
			textFields[i] = new JTextField(20);
			textFields[i].setPreferredSize(new Dimension(500, 25));
			textFields[i].setEditable(false);
			if(i==5) textFields[i].setToolTipText("Le taux de profit est contenu entre 0 et 1");
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
	private class initPanelArticle implements ActionListener {
		JTextField tf [];
		initPanelArticle(JTextField[] tf) {
			super();
			this.tf = tf;
		}
		initPanelArticle() {
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
	
			Object eventSource = e.getSource();
				if(eventSource == creer) {
					creer.setEnabled(false);
					textFields[0].setText(""+ (Article.noSerie+1));
					textFields[1].setEditable(true); 
					textFields[5].setEditable(true); 
					enregistrer.setEnabled(true);

				   } //Creer button 
				
				if(eventSource == quitter) {
					frame.dispose();
				}
				
				if(eventSource == enregistrer) {	
					String name = textFields[1].getText();
					boolean nameErr = Main.isName(name);
					String profitText = textFields[5].getText();
					boolean profitErr = Pattern.isDouble(profitText);
					double profit = -1;
					if(profitErr) {
						 profit = Double.parseDouble(profitText);
						} else JOptionPane.showMessageDialog(null, "Le taux de profit est incorrect!", "Erreur", JOptionPane.ERROR_MESSAGE);
					if(profitErr)
					if(!nameErr && !name.isEmpty()) 
						JOptionPane.showMessageDialog(null, "Le champ 'Nom Article' doit uniquement contenir des lettres!","Erreur", JOptionPane.ERROR_MESSAGE);
					else if(name.isEmpty()) JOptionPane.showMessageDialog(null, "Le champ 'Nom Article' ne doit pas être vide!","Erreur", JOptionPane.ERROR_MESSAGE);
					else if(profitText.isEmpty()) JOptionPane.showMessageDialog(null, "Veuillez préciser un taux de profit","Erreur", JOptionPane.ERROR_MESSAGE);
					else if((!profitErr && !profitText.isEmpty() ) || !(profit>=0 && profit <=1)) JOptionPane.showMessageDialog(null, "Le taux de profit est incorrect!","Erreur", JOptionPane.ERROR_MESSAGE);
				
					else  {
						Article c = new Article(name, profit);
					enregistrer.setEnabled(true);
						JOptionPane.showMessageDialog(null,"Article enregistré avec succès", "Enregistrement de l'article",JOptionPane.INFORMATION_MESSAGE);
						for(int i=0; i<textFields.length; i++) textFields[i].setText("");
						Files.articleMap.put( c.noArticle, c);
						enregistrer.setEnabled(false);
						creer.setEnabled(true);
					}
				}
			}
	}
}
