package spiel_wirtschaft.model;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import spiel_wirtschaft.model.effects.StadtEffektFuerNation;
import spiel_wirtschaft.model.effects.StadtIsolatedEffect;

public interface Gebaeude {

	public String getGebaeudeName();

	public BigDecimal getGeldKosten();

	public List<Pair<Ware, Long>> getWarenKosten();

	public String getVorteileDisplayText();

	public StadtIsolatedEffect getStadtIsolatedEffect();

	public StadtEffektFuerNation getStadtEffektFuerNation();

}