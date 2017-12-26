package spiel_wirtschaft.model;

public enum KartenGenerierungsModus {
	KONTINENT("Kontinent"), ZUFAELLIG("Zufällig"), WASSERREICH("Wasserreich"), WASSERARM("Wasserarm");

	private final String displayName;

	private KartenGenerierungsModus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
