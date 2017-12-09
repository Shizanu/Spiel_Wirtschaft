package spiel_wirtschaft.model;

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
				kartenfelder[xPos][yPos] = new KartenfeldBE();
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

}
