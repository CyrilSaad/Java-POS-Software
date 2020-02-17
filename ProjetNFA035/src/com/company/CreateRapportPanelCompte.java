package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class CreateRapportPanelCompte extends JPanel {
	CreateRapportPanelCompte(String s, String ss, String label1, String label2) {

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

		boxPane.add(client);
		boxPane.add(cb);
		gridPane.add(boxPane);
		
		JPanel parentPan = new JPanel();
		parentPan.setLayout(new GridLayout(1, 3));
		
		JLabel comboLabel = new JLabel("Ville", SwingConstants.CENTER);
		JComboBox combobox = new JComboBox();
		DefaultComboBoxModel cbmod = new DefaultComboBoxModel();
		combobox.setModel(cbmod);
		JPanel comboPane = new JPanel(); comboPane.setLayout(new GridLayout(2,1));
		comboPane.add(comboLabel); comboPane.add(combobox);
		parentPan.add(comboPane);
		
		JPanel radioPan = new JPanel();
		radioPan.setLayout(new GridLayout(2,1));
		JRadioButton venteBut = new JRadioButton(label1);
		JRadioButton recuBut = new JRadioButton(label2);
		ButtonGroup bg = new ButtonGroup(); bg.add(venteBut);	 bg.add(recuBut);
		JPanel tempan = new JPanel(); JLabel emp = new JLabel("     ");
		tempan.add(venteBut); tempan.add(recuBut);
		radioPan.add(emp);	radioPan.add(tempan);
		parentPan.add(radioPan);
		
		JPanel buttonPan = new JPanel(); buttonPan.setLayout(new GridLayout(2,1));
		JButton okButton = new JButton("OK");	JButton quitterButton = new JButton("Quitter"); 
		
		buttonPan.add(okButton);
		buttonPan.add(quitterButton);
		parentPan.add(buttonPan);
									
		northPan.add(gridPane);
		northPan.add(parentPan);

		// tablePan
		String[] columns = { "No Trs", "Descrip Trs", "Date Trs", ss, "Montant "};
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
