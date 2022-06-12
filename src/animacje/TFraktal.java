package animacje;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class TFraktal extends JPanel implements Runnable{
	private Thread watek;
	private int pauza;
	private ArrayList<Trojkat> trojkaty;
	private int maxLiczbaTrojkatow = 243;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, getWidth(), getHeight());
		for (Trojkat t : trojkaty) {
			g.fillPolygon(t.getPunktyX(), t.getPunktyY(), 3);
		}
	}
	
	public TFraktal(int pauza, int width, int height, int priority) {
		setPreferredSize(new Dimension(width, height));
		this.pauza = pauza;
		watek = new Thread(this);
		trojkaty = new ArrayList<Trojkat>();
		trojkaty.add(new Trojkat(new int[]{0, width / 2, width}, new int[]{height, 0, height}));
		watek.setPriority(priority);
		watek.start();
	}
	
	public void run() {
		while (true) {
			try {
				watek.sleep(pauza);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			ArrayList<Trojkat> nowa = new ArrayList<Trojkat>();
			if (trojkaty.size() == maxLiczbaTrojkatow) {
				try {
					watek.sleep(20 * pauza);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				nowa.add(trojkaty.get(trojkaty.size() - 1));
				trojkaty = nowa;
			}
			else if (trojkaty.get(0).getPunktyX()[0] > 0) {
				Trojkat t = trojkaty.get(0);
				int[] X = t.getPunktyX();
				int[] Y = t.getPunktyY();
				
				int predkoscY = 2 * (X[0] != 1 ? (getHeight() - Y[0]) / (X[0] - 1) : 0);
				
				X[0] -= 2;
				X[2] += 2;
				Y[0] += predkoscY;
				Y[2] += predkoscY;
				
				t.setPunktyX(X);
				t.setPunktyY(Y);
			}
			else if (trojkaty.size() < maxLiczbaTrojkatow) {
				try {
					watek.sleep(20 * pauza);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				for (Trojkat t : trojkaty) {
					int[] punktyX = t.getPunktyX();
					int[] punktyY = t.getPunktyY();
					int[] pX1 = {punktyX[0], (punktyX[0] + punktyX[1]) / 2, punktyX[1]};
					int[] pY1 = {punktyY[0], (punktyY[1] + punktyY[0]) / 2, punktyY[2]};
					nowa.add(new Trojkat(pX1, pY1));
					int[] pX2 = {punktyX[1], (punktyX[1] + punktyX[2]) / 2, punktyX[2]};
					int[] pY2 = {punktyY[0], (punktyY[1] + punktyY[0]) / 2, punktyY[2]};
					nowa.add(new Trojkat(pX2, pY2));
					int[] pX3 = {(punktyX[0] + punktyX[1]) / 2, punktyX[1], (punktyX[1] + punktyX[2]) / 2};
					int[] pY3 = {(punktyY[1] + punktyY[0]) / 2, punktyY[1], (punktyY[1] + punktyY[2]) / 2};
					nowa.add(new Trojkat(pX3, pY3));
				}
				trojkaty = nowa;
			}
			repaint();
		}
	}
}
