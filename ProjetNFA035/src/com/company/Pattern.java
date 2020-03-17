package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

	public static boolean isDate(String s) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        // Input to be parsed should strictly follow the defined date format
        // above.
        format.setLenient(false);

        try {
            format.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
	}
}
