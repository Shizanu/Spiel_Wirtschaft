package spiel_wirtschaft.model.effects;

import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.model.rundendelta.StadtEffekteFuerNation;

public interface StadtEffektFuerNation {

	public static final StadtEffektFuerNation NONE = (stadt, stadtEffekteFuerNation) -> {
	};

	public void apply(StadtBE stadt, StadtEffekteFuerNation stadtEffekteFuerNation);
}
