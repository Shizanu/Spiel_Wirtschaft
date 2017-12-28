package spiel_wirtschaft.model;

import java.util.LinkedList;
import java.util.Queue;

public class SpielkarteBE extends AbstractBE {

	private final int xSize;
	private final int ySize;

	private final KartenfeldBE[][] kartenfelder;

	public SpielkarteBE(int xSize, int ySize) {
		super();
		this.xSize = xSize;
		this.ySize = ySize;
		kartenfelder = new KartenfeldBE[xSize][ySize];
		for (int xPos = 0; xPos < xSize; xPos++) {
			for (int yPos = 0; yPos < ySize; yPos++) {
				kartenfelder[xPos][yPos] = new KartenfeldBE(xPos, yPos);
			}
		}
	}

	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}

	public KartenfeldBE[][] getKartenfelder() {
		return kartenfelder;
	}

	public Queue<KartenfeldBE> getNachbarfelder(KartenfeldBE aktuellesFeld) {
		Queue<KartenfeldBE> nachbarn = new LinkedList<KartenfeldBE>();
		int xKoordinate = aktuellesFeld.getxPos();
		int yKoordinate = aktuellesFeld.getyPos();
		if (xKoordinate - 1 > 0) {
			if (yKoordinate - 1 > 0) {
				nachbarn.add(kartenfelder[xKoordinate - 1][yKoordinate - 1]);
			}
			if (yKoordinate + 1 < this.ySize) {
				nachbarn.add(kartenfelder[xKoordinate - 1][yKoordinate + 1]);
			}
			nachbarn.add(kartenfelder[xKoordinate - 1][yKoordinate]);
		}
		if (xKoordinate + 1 < this.xSize) {
			if (yKoordinate - 1 > 0) {
				nachbarn.add(kartenfelder[xKoordinate + 1][yKoordinate - 1]);
			}
			if (yKoordinate + 1 < this.ySize) {
				nachbarn.add(kartenfelder[xKoordinate + 1][yKoordinate + 1]);
			}
			nachbarn.add(kartenfelder[xKoordinate + 1][yKoordinate]);
		}
		if (yKoordinate - 1 > 0) {
			nachbarn.add(kartenfelder[xKoordinate][yKoordinate - 1]);
		}
		if (yKoordinate + 1 < this.ySize) {
			nachbarn.add(kartenfelder[xKoordinate][yKoordinate + 1]);
		}
		return nachbarn;
	}

}
