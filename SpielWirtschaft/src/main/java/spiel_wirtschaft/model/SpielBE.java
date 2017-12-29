package spiel_wirtschaft.model;

import javax.xml.bind.annotation.XmlElement;

public class SpielBE extends AbstractBE {

	@XmlElement
	private final SpielkarteBE spielkarte;

	public SpielBE(SpielkarteBE spielkarte) {
		super();
		this.spielkarte = spielkarte;

	}

	public SpielkarteBE getSpielkarte() {
		return spielkarte;
	}

}
