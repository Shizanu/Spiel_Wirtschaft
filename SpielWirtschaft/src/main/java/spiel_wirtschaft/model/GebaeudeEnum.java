package spiel_wirtschaft.model;

public enum GebaeudeEnum {

	TEMPEL("Tempel", 1, "glückliche Bevölkerung"), MARKTPLATZ("Marktplatz", 1,
			"Wirtschaftswachstum innerhalb der Stadt"), SCHMIED("Schmied", 1, "Verbesserung berittener Einheiten");

	private final String gebaeudeName;
	private final int kosten;
	private final String vorteile;

	private GebaeudeEnum(String gebaeudeName, int kosten, String vorteile) {
		this.gebaeudeName = gebaeudeName;
		this.kosten = kosten;
		this.vorteile = vorteile;
	}

	public String getGebaeudeName() {
		return gebaeudeName;
	}

	public String getVorteile() {
		return vorteile;
	}

	public int getKosten() {
		return kosten;
	}
}
