package spiel_wirtschaft.model;

import java.math.BigDecimal;

import spiel_wirtschaft.model.effects.StadtEffektFuerNation;
import spiel_wirtschaft.model.effects.StadtIsolatedEffect;

public enum GebaeudeEnum {

	TEMPEL("Tempel", 1, "+5 Einwohner pro Runde",
			(stadt, stadtRundenDelta) -> stadtRundenDelta.einwohnerzahlChange += 5, StadtEffektFuerNation.NONE),
	MARKTPLATZ("Marktplatz", 1, "+1 Geld pro Runde", StadtIsolatedEffect.NONE, (stadt,
			stadtEffekteFuerNation) -> stadtEffekteFuerNation.geldEinnahmen = stadtEffekteFuerNation.geldEinnahmen
					.add(new BigDecimal("1"))),
	SCHMIED("Schmied", 1, "Verbesserung berittener Einheiten",
			(stadt, stadtRundenDelta) -> stadtRundenDelta.einwohnerzahlChange += 5, StadtEffektFuerNation.NONE);

	private final String gebaeudeName;
	private final int kosten;
	private final String vorteileDisplayText;
	private final StadtIsolatedEffect stadtIsolatedEffect;
	private final StadtEffektFuerNation stadtEffektFuerNation;

	private GebaeudeEnum(String gebaeudeName, int kosten, String vorteile, StadtIsolatedEffect stadtIsolatedEffect,
			StadtEffektFuerNation stadtEffektFuerNation) {
		this.gebaeudeName = gebaeudeName;
		this.kosten = kosten;
		this.vorteileDisplayText = vorteile;
		this.stadtIsolatedEffect = stadtIsolatedEffect;
		this.stadtEffektFuerNation = stadtEffektFuerNation;
	}

	public String getGebaeudeName() {
		return gebaeudeName;
	}

	public int getKosten() {
		return kosten;
	}

	public String getVorteileDisplayText() {
		return vorteileDisplayText;
	}

	public StadtIsolatedEffect getStadtIsolatedEffect() {
		return stadtIsolatedEffect;
	}

	public StadtEffektFuerNation getStadtEffektFuerNation() {
		return stadtEffektFuerNation;
	}

}
