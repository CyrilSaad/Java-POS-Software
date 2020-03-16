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
			
	CreatePanelVA(String ss, String s, JFrame f) {
		this.frame = f;
		this.currentPane = ss;
		JPanel parentPanel, buttonPanel;
		JLabel Ventes = new JLabel("");

		parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());

		
		DefaultComboBoxModel cbmod = new DefaultComboBoxModel();
		JComboBox combobox = new JComboBox(cbmod);
		combobox.setEditable(false);
		combobox.setPreferredSize(new Dimension(200, 25));
		creer = new JButton("Créer");
		enregistrer = new JButton("Enregistrer");
		quitter = new JButton("Quitter");
		quitter.addActionListener(new panelInitVA());
		this.setLayout(new BorderLayout());

		DefaultTableModel tabmod = new DefaultTableModel();
		String tb[] =  {"ok", "yo", "deal", "Haha"};
		tabmod.addColumn("Achat");
		tabmod.addColumn("Quantité");
		tabmod.addColumn(s+" Unit");
		tabmod.addColumn(s+ " Total");
		for(int i=0; i<20; i++) tabmod.addRow(new Object[] {null,null,null,null});
		JTable table = new JTable(tabmod);
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		Font font = new Font("Verdana", Font.PLAIN, 18);  
		table.setFont(font); 
		table.setFillsViewportHeight(true);
		table.setBackground(Color.white);
		table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		table.setShowGrid(true);
		table.setRowHeight(25);
		
		table.setGridColor(Color.DARK_GRAY);
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
		JLabel choirsirClient = new JLabel("Choisir Client: ");
		cbPan.add(choirsirClient);
		cbPan.add(combobox);
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
		//actionListeners
		creer.addActionListener(new panelInitVA(textFields, this));
		
		
		
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
			
			JLabel description = new JLabel("Description: ");
			gbc.gridx = 0; gbc.gridy = 1;
			childDisplayPanel.add(description, gbc);
			gbc.gridx = 1 ; gbc.gridy = 1;
			childDisplayPanel.add(textFields[1], gbc);
	
		JLabel dateVente = new JLabel("Date "+ss+":");
		gbc.gridx = 0; gbc.gridy = 3;
		childDisplayPanel.add(dateVente, gbc);
		
		gbc.gridx = 1 ; gbc.gridy = 3;
		childDisplayPanel.add(textFields[2], gbc);
		
		
		
		JLabel montantVente = new JLabel("Montant "+ss+":");
		gbc.gridx = 0; gbc.gridy = 4;
		childDisplayPanel.add(montantVente, gbc);
		
		gbc.gridx = 1; gbc.gridy = 4;
		childDisplayPanel.add(textFields[3], gbc);

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
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Infos " + ss + "s",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

		this.add(scrollPane, BorderLayout.SOUTH);
		this.add(displayPanel, BorderLayout.CENTER);
		this.add(parentPanel, BorderLayout.NORTH);
		// end of first panel

	};
	private class panelInitVA implements ActionListener {
		boolean inCreation;
		JTextField tf [];
		JPanel panelVA;
		panelInitVA(JTextField[] tf, JPanel pane) {
			super();
			this.tf = tf;
			this.panelVA = pane;
		}
		public panelInitVA() {
		}
		@Override
		public void actionPerformed(ActionEvent e) {
	
			Object eventSource = e.getSource();
			
			if(eventSource == creer) {
				creer.setEnabled(false);
				Transaction.noSerie++;
				inCreation = true;
				if(inCreation) {
					for (int i = 0; i < tf.length; i++) {
				tf[0].setText(Integer.toString(Transaction.noSerie));
				
				tf[2].setEditable(true); tf[2].setToolTipText("DD/MM/YYYY");
				}//end of textfield init
			   }	
			} //Creer button 
			
			if(eventSource == quitter) {
				frame.dispose();
			}
		}

	}
//	private class panelInitCompte implements ActionListener {
//		JTextField tf [];
//		JPanel panelVA;
//		panelInitCompte(JTextField[] tf, JPanel pane) {
//			super();
//			this.tf = tf;
//			this.panelVA = pane;
//		}
//		public panelInitCompte() {
//		}
//		@Override
//		public void actionPerformed(ActionEvent e) {
//	
//			Object eventSource = e.getSource();
//			if(currentPane == "Vente") {
//				if(eventSource == creer) {
//					creer.setEnabled(false);
//					textFields[0].setText(""+ (Client.noSerie+1));
//					textFields[1].setEditable(true); 
//					textFields[2].setText(""+ 0);
//					enregistrer.setEnabled(true);
//
//					
//					
//				   } //Creer button 
//				
//				if(eventSource == quitter) {
//					frame.dispose();
//				}
//				
//				if(eventSource == enregistrer) {
//					String name = textFields[1].getText();
//					String nomVille = villes.getSelectedItem().toString();
//					Ville selectedVille = Ville.findVille(nomVille);
//					boolean nameErr = isName(name);
//					if(!nameErr && !name.isEmpty()) 
//						JOptionPane.showMessageDialog(null, "Le champ 'Nom Client' doit uniquement contenir des lettres!");
//					else if(name.isEmpty()) JOptionPane.showMessageDialog(null, "Le champ 'Nom Client' ne doit pas être vide!");
//					else {
//						Client c = new Client(name, selectedVille);
//						String etatCompte = bg.getSelection().getActionCommand();
//						switch(etatCompte) {
//						case "ACTIF": c.setEtat(EtatCompte.ACTIF); break;
//						case "SUSPENDU": c.setEtat(EtatCompte.SUSPENDU); break;
//						case "FERME": c.setEtat(EtatCompte.FERME); break;
//						}
//						creer.setEnabled(true);
//						JOptionPane.showMessageDialog(null,"Compte enregistré avec succès", "Enregistrement du compte",JOptionPane.INFORMATION_MESSAGE);
//						for(int i=0; i<textFields.length; i++) textFields[i].setText("");
//						actif.setSelected(true);
//						Files.createClient( c.noCompte, c);
//						enregistrer.setEnabled(false);
//					}
//				}
//			} //CreerClient
//			
//			else if(currentPane == "Achat") {
//				if(eventSource == creer) {
//					creer.setEnabled(false);
//					textFields[0].setText(""+ (Fournisseur.noSerie+1));
//					textFields[1].setEditable(true); 
//					textFields[2].setText(""+ 0);
//					enregistrer.setEnabled(true);
//					frame.getJMenuBar().disable();
//					
//				   } //Creer button 
//				
//				if(eventSource == quitter) {
//					frame.dispose();
//				}
//				
//				if(eventSource == enregistrer) {
//					String name = textFields[1].getText();
//
//			;
//					if( !name.isEmpty()) 
//						JOptionPane.showMessageDialog(null, "Le champ 'Nom Fournisseur' doit uniquement contenir des lettres!");
//					else if(name.isEmpty()) JOptionPane.showMessageDialog(null, "Le champ 'Nom Fournisseur' ne doit pas être vide!");
//					else {
//						Fournisseur f = new Fournisseur(name, selectedVille);
//						String etatCompte = bg.getSelection().getActionCommand();
//						switch(etatCompte) {
//						case "ACTIF": f.setEtat(EtatCompte.ACTIF); break;
//						case "SUSPENDU": f.setEtat(EtatCompte.SUSPENDU); break;
//						case "FERME": f.setEtat(EtatCompte.FERME); break;
//						}
//						creer.setEnabled(true);
//						JOptionPane.showMessageDialog(null,"Compte enregistré avec succès", "Enregistrement du compte",JOptionPane.INFORMATION_MESSAGE);
//						for(int i=0; i<textFields.length; i++) textFields[i].setText("");
//						actif.setSelected(true);
//						Files.createFournisseur( f.noCompte, f);
//						enregistrer.setEnabled(false);
//				
//					}
//				}
//			
//			}
//		}

	}

