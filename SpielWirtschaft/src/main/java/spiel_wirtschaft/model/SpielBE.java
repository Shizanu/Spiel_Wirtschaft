package spiel_wirtschaft.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SpielBE extends AbstractBE {

	@XmlElement
	private final SpielkarteBE spielkarte;

	@XmlElement
	private int runde = 0;

	@XmlElement
	private final List<NationBE> nationen = new ArrayList<>();

	public SpielBE() {
		super();
		this.spielkarte = null;
	}

	public SpielBE(SpielkarteBE spielkarte) {
		super();
		this.spielkarte = spielkarte;
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
