package spiel_wirtschaft.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

import spiel_wirtschaft.model.rundendelta.StadtEffekteFuerNation;
import spiel_wirtschaft.model.rundendelta.StadtRundenDelta;

public class StadtBE extends AbstractBE {

	private transient StadtRundenDelta rundenDelta;
	private transient StadtEffekteFuerNation effekteFuerNation;

	// TODO: make two directional relationship for storing possible
	// @XmlElement
	private transient NationBE nation;

	@XmlElement
	private int einwohnerzahl;

	@XmlElement
	private String stadtname;

	@XmlElement
	private final KartenKoordinatenBE position;

	@XmlElement
	private final ArrayList<GebaeudeEnum> gebauteGebaeude = new ArrayList<GebaeudeEnum>();

	public StadtBE() {
		super();
		position = null;
	}

	public StadtBE(int einwohnerzahl, String stadtname, KartenKoordinatenBE position) {
		super();
		this.einwohnerzahl = einwohnerzahl;
		this.stadtname = stadtname;
		this.position = position;
	}

	public NationBE getNation() {
		return nation;
	}

	public void setNation(NationBE nation) {
		this.nation = nation;
	}

	public StadtRundenDelta getRundenDelta() {
		return rundenDelta;
	}

	public void setRundenDelta(StadtRundenDelta rundenDelta) {
		this.rundenDelta = rundenDelta;
	}

	public StadtEffekteFuerNation getEffekteFuerNation() {
		return effekteFuerNation;
	}

	public void setEffekteFuerNation(StadtEffekteFuerNation effekteFuerNation) {
		this.effekteFuerNation = effekteFuerNation;
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
