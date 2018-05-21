package spiel_wirtschaft.model;

import javax.xml.bind.annotation.XmlElement;

public class KartenfeldBE extends AbstractBE {

	@XmlElement
	private GelaendeTypEnum gelaendeTyp;

	@XmlElement
	private int xPos;

	@XmlElement
	private int yPos;

	public KartenfeldBE() {
		super();
	}

	public void init(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public final GelaendeTypEnum gelaendeTypProperty() {
		return this.gelaendeTyp;
	}

	public final GelaendeTypEnum getGelaendeTyp() {
		return this.gelaendeTyp;
	}

	public final void setGelaendeTyp(final GelaendeTypEnum gelaendeTyp) {
		this.gelaendeTyp = gelaendeTyp;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

}
