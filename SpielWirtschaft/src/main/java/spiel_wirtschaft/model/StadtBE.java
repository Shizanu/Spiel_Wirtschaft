package spiel_wirtschaft.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

import spiel_wirtschaft.model.rundendelta.StadtRundenDelta;

public class StadtBE extends AbstractBE {

	private transient StadtRundenDelta rundenDelta;

	@XmlElement
	private int einwohnerzahl;

	@XmlElement
	private String stadtname;

	@XmlElement
	private final KartenKoordinatenBE position;

	@XmlElement
	private final ArrayList<GebaeudeEnum> gebauteGebaeude;

	public StadtBE(int einwohnerzahl, String stadtname, KartenKoordinatenBE position) {
		super();
		this.einwohnerzahl = einwohnerzahl;
		this.stadtname = stadtname;
		this.position = position;
		this.gebauteGebaeude = new ArrayList<GebaeudeEnum>();
	}

	public StadtRundenDelta getRundenDelta() {
		return rundenDelta;
	}

	public void setRundenDelta(StadtRundenDelta rundenDelta) {
		this.rundenDelta = rundenDelta;
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

	public ArrayList<GebaeudeEnum> getGebauteGebaeude() {
		return gebauteGebaeude;
	}

	public void addGebaeude(GebaeudeEnum gebaeude) {
		gebauteGebaeude.add(gebaeude);
	}

}
