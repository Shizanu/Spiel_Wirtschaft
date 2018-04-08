package spiel_wirtschaft.model.effects;

import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.model.rundendelta.StadtRundenDelta;

public interface StadtIsolatedEffect {

	public static final StadtIsolatedEffect NONE = (stadt, delta) -> {
	};

	public void apply(StadtBE stadt, StadtRundenDelta stadtRundenDelta);
}
