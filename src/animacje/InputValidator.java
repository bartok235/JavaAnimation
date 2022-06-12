package animacje;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class InputValidator {
	public static int safeInput(String message, int lowBound, int upBound) {
		try {
			String result = JOptionPane.showInputDialog(null, message);
			if (result == null) {
				System.exit(0);
			}
			int choice = Integer.parseInt(result);
			if (choice >= lowBound && choice <= upBound) {
				return choice;
			} else {
				JOptionPane.showMessageDialog(null, "Błędna wartość, spróbuj jeszcze raz!");
				safeInput(message, lowBound, upBound);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Błędna wartość, spróbuj jeszcze raz!");
			safeInput(message, lowBound, upBound);
		}
		
		return -1;
	}
}
