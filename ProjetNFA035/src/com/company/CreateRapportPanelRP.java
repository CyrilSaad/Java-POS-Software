package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;


public class CreateRapportPanelRP extends JPanel {
	CreateRapportPanelRP(String s, String ss) {

		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Rapport " + s + "s",
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
		JTextField tf1 = new JTextField(10);
		JTextField tf2 = new JTextField(10);
		datePane.add(tf1);
		datePane.add(tf2);
		gridPane.add(datePane);

		JPanel boxPane = new JPanel();
		boxPane.setLayout(new GridLayout(2, 2, 10, 10));
		JLabel client = new JLabel(s, SwingConstants.CENTER);
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		JComboBox cb = new JComboBox(model);

		JButton quitterBut = new JButton("Quitter");
		JButton okBut = new JButton("OK");
		// cb.setPreferredSize(new Dimension(30,28));
		boxPane.add(client);
		boxPane.add(cb);
		gridPane.add(boxPane);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		JPanel panel2 = new JPanel();
		JPanel radioPane = new JPanel();
		JLabel modePaie = new JLabel("Mode Paie");
		panel2.add(modePaie);
		panel.add(panel2);
		panel.add(okBut);
		JRadioButton cash = new JRadioButton("Cash");
		JRadioButton cheque = new JRadioButton("Cheque");
		JRadioButton transfert = new JRadioButton("Transfert");
		ButtonGroup bg = new ButtonGroup();
		bg.add(cash); bg.add(cheque); bg.add(transfert);
		radioPane.add(cash);
		radioPane.add(cheque);
		radioPane.add(transfert);
		panel.add(radioPane);
		panel.add(quitterBut);

		northPan.add(gridPane);
		northPan.add(panel);

		// tablePan
		String[] columns = { "No " + s, "Date " + s, ss, "Montant " + s, "Mode Paie" };
		DefaultTableModel tabmod = new DefaultTableModel();
		for (int i = 0; i < columns.length; i++)
			tabmod.addColumn(columns[i]);
		JTable table = new JTable(tabmod);
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.setFillsViewportHeight(true);
		table.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		table.setShowGrid(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.blue);
		JScrollPane scrollPane = new JScrollPane(table);

		// end
		this.add(northPan, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);


	
	}

}
