package spiel_wirtschaft.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

import spiel_wirtschaft.model.rundendelta.NationRundenDelta;

public class NationBE extends AbstractBE {

	private transient NationRundenDelta rundenDelta;

	@XmlElement
	private final List<StadtBE> staedte;

	@XmlElement
	private long geld;

	@XmlElement
	private Map<WarenEnum, Long> waren;

	public NationBE() {
		super();
		staedte = new ArrayList<>();
	}

	public NationRundenDelta getRundenDelta() {
		return rundenDelta;
	}

	public void setRundenDelta(NationRundenDelta rundenDelta) {
		this.rundenDelta = rundenDelta;
	}

	public List<StadtBE> getStaedte() {
		return staedte;
	}

	public long getGeld() {
		return geld;
	}

	public void setGeld(long geld) {
		this.geld = geld;
	}

	public Map<WarenEnum, Long> getWaren() {
		return waren;
	}

	public void setWaren(Map<WarenEnum, Long> waren) {
		this.waren = waren;
	}

}
