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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.company.Compte.EtatCompte;

public class CreatePanelCompte extends JPanel {

	JTextField textFields[] = new JTextField[4];
	JButton creer, enregistrer, quitter;
	JFrame frame;
	JComboBox villes;
	JRadioButton actif, ferme, suspendu;
	String currentPane;
	ButtonGroup bg;
	CreatePanelCompte(String ss, JFrame f) {
			this.currentPane = ss;
			JPanel parentPanel, buttonPanel;
			JLabel Ventes = new JLabel("");
			this.frame = f;
			parentPanel = new JPanel();
			parentPanel.setLayout(new BorderLayout());
	
		DefaultComboBoxModel<String> villesmod = new DefaultComboBoxModel<String>(Ville.villeNoms);
		villes = new JComboBox(villesmod);
		villes.setEditable(false);
			villes.setPreferredSize(new Dimension(200, 25));
			creer = new JButton("Créer"); creer.addActionListener(new panelInitCompte(textFields));
			enregistrer = new JButton("Enregistrer"); enregistrer.addActionListener(new panelInitCompte(textFields));
			enregistrer.setEnabled(false);
			quitter = new JButton("Quitter");
			quitter.addActionListener(new panelInitCompte());
			
			 actif = new JRadioButton("Actif", true); actif.setActionCommand("ACTIF");
			 ferme = new JRadioButton("Fermé");	 ferme.setActionCommand("FERME");
			 suspendu = new JRadioButton("Suspendu"); suspendu.setActionCommand("SUSPENDU");
			 bg = new ButtonGroup();
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
			table.setGridColor(Color.DARK_GRAY);
			table.setRowHeight(25);
			for(int i=0; i<20; i++) tabmod.addRow(new Object[] {null,null,null,null});
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

			JPanel pp = new JPanel();
			pp.setLayout(new FlowLayout(FlowLayout.LEFT));
			childDisplayPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(10, 10, 10, 10);
			
			for (int i = 0; i < 4; i++) {
				textFields[i] = new JTextField(20);
				textFields[i].setPreferredSize(new Dimension(500, 25));
				textFields[i].setEditable(false);
			}
			
				JLabel noVente = new JLabel("No. "+ss+":");
				gbc.gridx = 0; gbc.gridy = 0;
				childDisplayPanel.add(noVente, gbc);
				
				gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 3;
				childDisplayPanel.add(textFields[0], gbc);
				
				JLabel description = new JLabel("Nom "+ss+":");
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
			childDisplayPanel.add(textFields[2], gbc);
			
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
	private class panelInitCompte implements ActionListener {
		JTextField tf [];
		panelInitCompte(JTextField[] tf) {
			super();
			this.tf = tf;
		}
		public panelInitCompte() {
		}
		@Override
		public void actionPerformed(ActionEvent e) {
	
			Object eventSource = e.getSource();
			if(currentPane == "Client") {
				if(eventSource == creer) {
					creer.setEnabled(false);
					textFields[0].setText(""+ (Compte.noSerie+1));
					textFields[1].setEditable(true); 
					textFields[2].setText(""+ 0);
					enregistrer.setEnabled(true);

					
					
				   } //Creer button 
				
				if(eventSource == quitter) {
					frame.dispose();
				}
				
				if(eventSource == enregistrer) {
					String name = textFields[1].getText();
					String nomVille = villes.getSelectedItem().toString();
					Ville selectedVille = Ville.findVille(nomVille);
					boolean nameErr = Main.isName(name);
					if(!nameErr && !name.isEmpty()) 
						JOptionPane.showMessageDialog(null, "Le champ 'Nom Client' doit uniquement contenir des lettres!","Erreur", JOptionPane.ERROR_MESSAGE);
					else if(name.isEmpty()) JOptionPane.showMessageDialog(null, "Le champ 'Nom Client' ne doit pas être vide!","Champ Obligatoire", JOptionPane.WARNING_MESSAGE);
					else {
						Client c = new Client(name, selectedVille);
						String etatCompte = bg.getSelection().getActionCommand();
						switch(etatCompte) {
						case "ACTIF": c.setEtat(EtatCompte.ACTIF); break;
						case "SUSPENDU": c.setEtat(EtatCompte.SUSPENDU); break;
						case "FERME": c.setEtat(EtatCompte.FERME); break;
						}
						creer.setEnabled(true);
						JOptionPane.showMessageDialog(null,"Compte enregistré avec succès", "Enregistrement du compte",JOptionPane.INFORMATION_MESSAGE);
						for(int i=0; i<textFields.length; i++) textFields[i].setText("");
						actif.setSelected(true);
						Files.createClient(c.noCompte, c);
						enregistrer.setEnabled(false);
					}
				}
			} //CreerClient
			
			else if(currentPane == "Fournisseur") {
				if(eventSource == creer) {
					creer.setEnabled(false);
					textFields[0].setText(""+ (Compte.noSerie+1));
					textFields[1].setEditable(true); 
					textFields[2].setText(""+ 0);
					enregistrer.setEnabled(true);
					
				   } //Creer button 
				
				if(eventSource == quitter) {
					frame.dispose();
				}
				
				if(eventSource == enregistrer) {
					String name = textFields[1].getText();
					String nomVille = villes.getSelectedItem().toString();
					Ville selectedVille = Ville.findVille(nomVille);
					boolean nameErr = Main.isName(name);
					if(!nameErr && !name.isEmpty()) 
						JOptionPane.showMessageDialog(null, "Le champ 'Nom Fournisseur' doit uniquement contenir des lettres!","Erreur", JOptionPane.ERROR_MESSAGE);
					else if(name.isEmpty()) JOptionPane.showMessageDialog(null, "Le champ 'Nom Fournisseur' ne doit pas être vide!","Champ Obligatoire", JOptionPane.WARNING_MESSAGE);
					else {
						Fournisseur f = new Fournisseur(name, selectedVille);
						String etatCompte = bg.getSelection().getActionCommand();
						switch(etatCompte) {
						case "ACTIF": f.setEtat(EtatCompte.ACTIF); break;
						case "SUSPENDU": f.setEtat(EtatCompte.SUSPENDU); break;
						case "FERME": f.setEtat(EtatCompte.FERME); break;
						}
						creer.setEnabled(true);
						JOptionPane.showMessageDialog(null,"Compte enregistré avec succès", "Enregistrement du compte",JOptionPane.INFORMATION_MESSAGE);
						for(int i=0; i<textFields.length; i++) textFields[i].setText("");
						actif.setSelected(true);
						Files.createFournisseur(f.noCompte, f);
						enregistrer.setEnabled(false);
				
					}
				}
			
			}
		}

	}
}
