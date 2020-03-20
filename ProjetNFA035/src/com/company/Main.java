package com.company;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import com.sun.javafx.collections.MappingChange.Map;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Border;

import java.awt.*;
import java.awt.TextArea;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	String versionNumber = "0.0.1";
	JFrame frame = new JFrame("Gestion de transactions commerciales");   
	JPanel ventePanel;
	JPanel achatPanel;
	JPanel recuPanel ;
	JPanel paiementPanel ;
	JPanel clientPanel ;
	JPanel fournisseurPanel ;
	JPanel articlePanel;
	JPanel categoriePanel ;

	JPanel rapportVentePanel;
	JPanel rapportAchatPanel;
	JPanel rapportClientPanel ;
	JPanel rapportFournisseurPanel ;
	JPanel rapportRecuPanel ;
	JPanel rapportPaiementPanel ;
	JPanel rapportArticlePanel;
	JMenuBar mb;
	JMenu transactionMenu, compteMenu, stockMenu, toolMenu, quitterMenu;
	JMenu venteMenu, achatMenu, recuMenu, paiementMenu, clientMenu, fournisseurMenu;
	JMenuItem charger, save, version, quitter, ville;
	JMenuItem[] create = new JMenuItem[10];
	JMenuItem[] rapport = new JMenuItem[10];
	
	JPanel currentPane;

	static JButton enregistrer = new JButton("Enregistrer");

	public void addModifier(JMenu menu, JMenuItem i1, JMenuItem i2) {
		menu.add(i1);
		menu.add(i2);
	}

	Main() {
		
		mb = new JMenuBar();
		ville = new JMenuItem("Villes");
		frame.setSize(new Dimension(1300, 800));
		for (int i = 0; i < 10; i++) {
			create[i] = new JMenuItem("Créer/Modifier");
			rapport[i] = new JMenuItem("Rapports");
		}
		currentPane = new JPanel();
		charger = new JMenuItem("Charger sur disque");
		save = new JMenuItem("Sauvegarder sur disque");
		version = new JMenuItem("Version"); version.addActionListener(new menuListener());
		quitter = new JMenuItem("Quitter"); quitter.addActionListener(new menuListener());

		clientMenu = new JMenu("Clients");
		venteMenu = new JMenu("Ventes");
		paiementMenu = new JMenu("Paiements");
		fournisseurMenu = new JMenu("Fournisseurs");
		quitterMenu = new JMenu("Quitter");
		recuMenu = new JMenu("Recus");
		achatMenu = new JMenu("Achats");

		transactionMenu = new JMenu("Transactions");
		transactionMenu.add(venteMenu);
		transactionMenu.add(recuMenu);
		transactionMenu.add(paiementMenu);
		transactionMenu.add(achatMenu);

		addModifier(venteMenu, create[0], rapport[0]);
		create[0].addActionListener(new menuListener());
		rapport[0].addActionListener(new menuListener());

		addModifier(paiementMenu, create[2], rapport[2]);
		create[2].addActionListener(new menuListener());
		rapport[2].addActionListener(new menuListener());

		addModifier(achatMenu, create[1], rapport[1]);
		create[1].addActionListener(new menuListener());
		rapport[1].addActionListener(new menuListener());
		addModifier(recuMenu, create[3], rapport[3]);
		create[3].addActionListener(new menuListener());
		rapport[3].addActionListener(new menuListener());

		compteMenu = new JMenu("Comptes");
		compteMenu.add(clientMenu);
		compteMenu.add(fournisseurMenu);

		addModifier(clientMenu, create[4], rapport[4]);
		create[4].addActionListener(new menuListener());
		rapport[4].addActionListener(new menuListener());

		addModifier(fournisseurMenu, create[5], rapport[5]);
		create[5].addActionListener(new menuListener());
		rapport[5].addActionListener(new menuListener());

		stockMenu = new JMenu("Stock");
		JMenu articleMenu = new JMenu("Articles");
		JMenu categorieMenu = new JMenu("Catégories Articles");
		stockMenu.add(articleMenu);
		stockMenu.add(categorieMenu);

		addModifier(articleMenu, create[6], rapport[6]);
		create[6].addActionListener(new menuListener());
		rapport[6].addActionListener(new menuListener());
	
		categorieMenu.add(create[7]);
		create[7].addActionListener(new menuListener());

		toolMenu = new JMenu("Outils");
		toolMenu.add(charger);
		toolMenu.add(save);

		quitterMenu = new JMenu("Quitter");
		quitterMenu.add(version);
		quitterMenu.add(quitter);

		mb.add(transactionMenu);
		mb.add(compteMenu);
		mb.add(stockMenu);
		mb.add(toolMenu);
		mb.add(quitterMenu);
		frame.setJMenuBar(mb);
		frame.setResizable(false);
		frame.setVisible(true);
	}


	public static void main(String[] args) {
		Main a = new Main();
	}

	public class menuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object event = e.getSource();
			if (event == create[0]) {
				frame.setContentPane(ventePanel  = new CreatePanelVA("Vente", "Prix", "Client",frame));
				frame.validate();
				frame.repaint();
				currentPane = ventePanel;
			} else if (event == rapport[0]) {
				frame.setContentPane(rapportVentePanel  = new CreateRapportPanelVA("Vente", "Client", frame));
				frame.validate();
				frame.repaint();
				currentPane = rapportVentePanel;
			} else if (event == create[1]) {
				frame.setContentPane(achatPanel  = new CreatePanelVA("Achat", "Cout", "Fournisseur", frame));
				frame.validate();
				frame.repaint();
				currentPane = achatPanel;
			} else if (event == rapport[1]) {
				frame.setContentPane(rapportAchatPanel  = new CreateRapportPanelVA("Achat", "Fournisseur", frame));
				frame.validate();
				frame.repaint();
				currentPane = rapportAchatPanel;
			} else if (event == create[2]) {
				frame.setContentPane(paiementPanel = new CreatePanelRP("Paiement", "Fournisseur", frame));
				frame.validate();
				frame.repaint();
				currentPane = paiementPanel;
			} else if (event == rapport[2]) {
				frame.setContentPane(rapportPaiementPanel = new CreateRapportPanelRP("Paiement", "Fournisseur", frame));
				frame.validate();
				frame.repaint();
				currentPane = rapportPaiementPanel;
			} else if (event == create[3]) {
				frame.setContentPane(recuPanel = new CreatePanelRP("Reçu", "Client", frame));
				frame.validate();
				frame.repaint();
				currentPane = recuPanel;
			} else if (event == rapport[3]) {
				frame.setContentPane(rapportRecuPanel= new CreateRapportPanelRP("Reçu", "Client", frame));
				frame.validate();
				frame.repaint();
				currentPane = rapportRecuPanel ;
			} else if (event == create[4]) {
				frame.setContentPane(clientPanel = new CreatePanelCompte("Client", frame));
				frame.validate();
				frame.repaint();
				currentPane = clientPanel;
			} else if (event == rapport[4]) {
				frame.setContentPane(rapportClientPanel = new CreateRapportPanelCompte("Client", "Client", "Vente", "Reçu", frame));
				frame.validate();
				frame.repaint();
				currentPane = rapportClientPanel;
			} else if (event == create[5]) {
				frame.setContentPane(fournisseurPanel = new CreatePanelCompte("Fournisseur", frame));
				frame.validate();
				frame.repaint();
				currentPane = fournisseurPanel;
			} else if (event == rapport[5]) {
				frame.setContentPane(rapportFournisseurPanel = new CreateRapportPanelCompte("Fournisseur", "Fournisseur", "Achat", "Paie", frame));
				frame.validate();
				frame.repaint();
				currentPane = rapportFournisseurPanel;
			} else if (event == create[6]) {
				frame.setContentPane(articlePanel  = new CreatePanelArticle("Article", frame));
				frame.validate();
				frame.repaint();
				currentPane = articlePanel;
			}  else if (event == rapport[6]) {
				frame.setContentPane(rapportArticlePanel  = new CreateRapportPanelArticle());
				frame.validate();
				frame.repaint();
				currentPane = rapportArticlePanel;

			} 
			else if (event == create[7]) {
				frame.setContentPane(categoriePanel = new CreatePanelCategorie("Catégorie", frame));
				frame.validate();
				frame.repaint();
				currentPane = categoriePanel;
			}
			else if (event == quitter) frame.dispose();
			else if (event == version) { 
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM");
				LocalDateTime now = LocalDateTime.now();
				JOptionPane.showMessageDialog(null, (dtf.format(now)+ " " +versionNumber),"Version", 1);
			}	
		}

	}
	public static boolean isName(String name) {
	    return name.matches("^[a-zA-Z_ ]*$");
	}
}