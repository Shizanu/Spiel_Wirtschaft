package spiel_wirtschaft.model;

import javax.xml.bind.annotation.XmlElement;

public class StadtBE extends AbstractBE {

	@XmlElement
	private int einwohnerzahl;

	@XmlElement
	private String stadtname;

	@XmlElement
	private final KartenKoordinatenBE position;

	public StadtBE(int einwohnerzahl, String stadtname, KartenKoordinatenBE position) {
		super();
		this.einwohnerzahl = einwohnerzahl;
		this.stadtname = stadtname;
		this.position = position;
	}

	public int getEinwohnerzahl() {
		return einwohnerzahl;
	}

	public String getStadtname() {
		return stadtname;
	}

	public KartenKoordinatenBE getPosition() {
		return position;
	}

	public void setEinwohnerzahl(int einwohnerzahl) {
		this.einwohnerzahl = einwohnerzahl;
	}

	public void setStadtname(String stadtname) {
		this.stadtname = stadtname;
	}

}
