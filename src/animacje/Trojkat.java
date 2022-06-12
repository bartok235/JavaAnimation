package animacje;

public class Trojkat {
	private int[] punktyX;
	private int[] punktyY;
	
	Trojkat(int[] punktyX, int[] punktyY) {
		this.punktyX = punktyX;
		this.punktyY = punktyY;
	}
	
	public int[] getPunktyX() {
		return punktyX;
	}
	
	public void setPunktyX(int[] punktyX) {
		this.punktyX = punktyX;
	}
	
	public int[] getPunktyY() {
		return punktyY;
	}
	
	public void setPunktyY(int[] punktyY) {
		this.punktyY = punktyY;
	}
}
