package spiel_wirtschaft.model;

import spiel_wirtschaft.model.effects.StadtIsolatedEffect;

public enum GebaeudeEnum {

	TEMPEL("Tempel", 1, "glückliche Bevölkerung", (stadt, delta) -> delta.einwohnerzahlChange += 5),
	MARKTPLATZ("Marktplatz", 1, "Wirtschaftswachstum innerhalb der Stadt",
			(stadt, delta) -> delta.einwohnerzahlChange += 5),
	SCHMIED("Schmied", 1, "Verbesserung berittener Einheiten", (stadt, delta) -> delta.einwohnerzahlChange += 5);

	private final String gebaeudeName;
	private final int kosten;
	private final String vorteileDisplayText;
	private final StadtIsolatedEffect effekt;

	private GebaeudeEnum(String gebaeudeName, int kosten, String vorteile, StadtIsolatedEffect effekt) {
		this.gebaeudeName = gebaeudeName;
		this.kosten = kosten;
		this.vorteileDisplayText = vorteile;
		this.effekt = effekt;
	}

	public String getGebaeudeName() {
		return gebaeudeName;
	}

	public String getVorteile() {
		return vorteileDisplayText;
	}

	public int getKosten() {
		return kosten;
	}

	public StadtIsolatedEffect getEffekt() {
		return effekt;
	}

}
