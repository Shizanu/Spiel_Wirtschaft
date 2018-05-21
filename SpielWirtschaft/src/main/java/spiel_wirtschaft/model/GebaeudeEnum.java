package spiel_wirtschaft.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import spiel_wirtschaft.model.effects.StadtEffektFuerNation;
import spiel_wirtschaft.model.effects.StadtIsolatedEffect;

public enum GebaeudeEnum implements Gebaeude {

	TEMPEL("Tempel", 1, Collections.emptyList(), "+5 Einwohner pro Runde",
			(stadt, stadtRundenDelta) -> stadtRundenDelta.einwohnerzahlChange += 5, StadtEffektFuerNation.NONE),
	MARKTPLATZ("Marktplatz", 1, Collections.emptyList(), "+1 Geld pro Runde", StadtIsolatedEffect.NONE, (stadt,
			stadtEffekteFuerNation) -> stadtEffekteFuerNation.geldEinnahmen = stadtEffekteFuerNation.geldEinnahmen
					.add(new BigDecimal("1"))),
	SCHMIED("Schmied", 1, Collections.emptyList(), "Verbesserung berittener Einheiten",
			(stadt, stadtRundenDelta) -> stadtRundenDelta.einwohnerzahlChange += 5, StadtEffektFuerNation.NONE);

	private final String gebaeudeName;
	private final BigDecimal geldKosten;
	private final List<Pair<Ware, Long>> warenKosten;
	private final String vorteileDisplayText;
	private final StadtIsolatedEffect stadtIsolatedEffect;
	private final StadtEffektFuerNation stadtEffektFuerNation;

	private GebaeudeEnum(String gebaeudeName, int kosten, List<Pair<Ware, Long>> warenKosten, String vorteile,
			StadtIsolatedEffect stadtIsolatedEffect, StadtEffektFuerNation stadtEffektFuerNation) {
		this.gebaeudeName = gebaeudeName;
		this.geldKosten = new BigDecimal(kosten);
		this.warenKosten = warenKosten;
		this.vorteileDisplayText = vorteile;
		this.stadtIsolatedEffect = stadtIsolatedEffect;
		this.stadtEffektFuerNation = stadtEffektFuerNation;

	}

	@Override
	public String getGebaeudeName() {
		return gebaeudeName;
	}

	@Override
	public BigDecimal getGeldKosten() {
		return geldKosten;
	}

	@Override
	public String getVorteileDisplayText() {
		return vorteileDisplayText;
	}

	@Override
	public StadtIsolatedEffect getStadtIsolatedEffect() {
		return stadtIsolatedEffect;
	}

	@Override
	public StadtEffektFuerNation getStadtEffektFuerNation() {
		return stadtEffektFuerNation;
	}

	@Override
	public List<Pair<Ware, Long>> getWarenKosten() {
		return warenKosten;
	}

}
