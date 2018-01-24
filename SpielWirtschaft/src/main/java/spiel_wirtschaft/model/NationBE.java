package spiel_wirtschaft.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NationBE extends AbstractBE {

	private final List<StadtBE> staedte;

	private long geld;

	private Map<WarenEnum, Long> waren;

	public NationBE() {
		super();
		staedte = new ArrayList<>();
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
