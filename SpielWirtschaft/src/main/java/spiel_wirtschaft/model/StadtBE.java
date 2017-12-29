package spiel_wirtschaft.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StadtBE extends AbstractBE {

	@XmlElement
	private final String einwohnerzahl;

	@XmlElement
	private final String stadtname;

	@XmlElement
	private final KartenKoordinatenBE position;

	public StadtBE(String einwohnerzahl, String stadtname, KartenKoordinatenBE position) {
		super();
		this.einwohnerzahl = einwohnerzahl;
		this.stadtname = stadtname;
		this.position = position;
	}

	public String getEinwohnerzahl() {
		return einwohnerzahl;
	}

	public String getStadtname() {
		return stadtname;
	}

	public KartenKoordinatenBE getPosition() {
		return position;
	}

}
