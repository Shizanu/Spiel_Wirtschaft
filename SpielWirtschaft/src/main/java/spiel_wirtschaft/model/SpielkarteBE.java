package spiel_wirtschaft.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.xml.bind.annotation.XmlElement;

public class SpielkarteBE extends AbstractBE {

	@XmlElement
	private final int xSize;

	@XmlElement
	private final int ySize;

	@XmlElement
	private final KartenfeldBE[][] kartenfelder;

	@XmlElement
	private final List<StadtBE> staedte;

	public SpielkarteBE(int xSize, int ySize) {
		super();
		staedte = new ArrayList<>();
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

	public List<StadtBE> getStaedte() {
		return staedte;
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
