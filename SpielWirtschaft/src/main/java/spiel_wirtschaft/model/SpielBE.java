package spiel_wirtschaft.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class SpielBE extends AbstractBE {

	@XmlElement
	private final SpielkarteBE spielkarte;

	@XmlElement
	private int runde;

	@XmlElement
	private final List<NationBE> nationen;

	public SpielBE(SpielkarteBE spielkarte) {
		super();
		this.spielkarte = spielkarte;
		this.runde = 0;
		this.nationen = new ArrayList<>();
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

	public List<NationBE> getNationen() {
		return nationen;
	}

}
