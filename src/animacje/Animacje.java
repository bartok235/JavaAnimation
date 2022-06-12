package animacje;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Animacje extends JFrame {	
	public Animacje()
	{
		super("Fraktale");
		setSize(1000, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container kontener = getContentPane();
		kontener.setLayout(new GridLayout(2, 5));
		int[] priorities = new int[10];
		for (int i = 0; i < 10; i++) {
			priorities[i] = InputValidator.safeInput("W¹tek " + (i + 1) + " priorytet\n1 - MAKSYMALNY\n2 - ŒREDNI\n3 - MINIMALNY", 1, 3);
			if (priorities[i] == 1) {
				priorities[i] = 10;
			}
			else if (priorities[i] == 2) {
				priorities[i] = 5;
			}
			else if (priorities[i] == 3) {
				priorities[i] = 1;
			}
		}
		for (int i = 0; i < 10; i++) {
			kontener.add(new TFraktal(20, getWidth() / 5, getHeight() / 2, priorities[i]));
		}
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Animacje a = new Animacje();
	}

}
