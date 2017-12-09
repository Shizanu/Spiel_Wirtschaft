package spiel_wirtschaft.model;

public class SpielBE extends AbstractBE {

	private final SpielkarteBE spielkarte;

	public SpielBE(SpielkarteBE spielkarte) {
		super();
		this.spielkarte = spielkarte;
	}

	public SpielkarteBE getSpielkarte() {
		return spielkarte;
	}

}
