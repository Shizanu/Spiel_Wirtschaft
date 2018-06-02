package spiel_wirtschaft.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

import spiel_wirtschaft.model.rundendelta.NationRundenDelta;

public class NationBE extends AbstractBE {

	private transient NationRundenDelta rundenDelta;

	@XmlElement
	private final List<StadtBE> staedte;

	@XmlElement
	private BigDecimal geld;

	@XmlElement
	private Map<WarenEnum, Long> waren;

	public NationBE() {
		super();
		staedte = new ArrayList<>();
		geld = BigDecimal.ZERO;
		waren = new HashMap<>();
		for (WarenEnum ware : WarenEnum.values()) {
			waren.put(ware, 0L);
		}
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

	// TODO Stadt zu Nation hinzufügen und umgekehrt -> ueber ctrl -> Städte gründen
	// über NationCtrl

	public BigDecimal getGeld() {
		return geld;
	}

	public void setGeld(BigDecimal geld) {
		this.geld = geld;
	}

	public Map<WarenEnum, Long> getWaren() {
		return waren;
	}

	public long getVerfuegbareMenge(WarenEnum ware) {
		return waren.get(ware);
	}

	public void subtrahiereVonWarenmenge(WarenEnum ware, long menge) {
		waren.put(ware, waren.get(ware) - menge);
	}

	public void setWaren(Map<WarenEnum, Long> waren) {
		this.waren = waren;
	}

}
