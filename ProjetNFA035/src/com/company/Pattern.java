package com.company;

import javax.swing.JOptionPane;

public class Pattern {
	public static boolean isDouble(String number) {
			
				try {
				double d = Double.parseDouble(number);
				return true;
						} catch (NumberFormatException ex) {
							return false;
								}
		
	
	}
	
	
	}

