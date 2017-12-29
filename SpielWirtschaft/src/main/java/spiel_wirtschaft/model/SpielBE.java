package spiel_wirtschaft.model;

import javax.xml.bind.annotation.XmlElement;

public class SpielBE extends AbstractBE {

	@XmlElement
	private final SpielkarteBE spielkarte;

	@XmlElement
	private int runde;

	public SpielBE(SpielkarteBE spielkarte) {
		super();
		this.spielkarte = spielkarte;
		this.runde = 0;
	}

	public SpielkarteBE getSpielkarte() {
		return spielkarte;
	}

	public int getRunde() {
		return runde;
	}

	public void incrementRunde() {
		this.runde++;
	}

}
