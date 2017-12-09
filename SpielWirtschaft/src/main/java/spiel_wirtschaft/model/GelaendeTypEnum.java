package spiel_wirtschaft.model;

public enum GelaendeTypEnum {

	WASSER(true), LAND(false);
	boolean wasser;

	private GelaendeTypEnum(boolean wasser) {
		this.wasser = wasser;
	}

	boolean isWasser() {
		return wasser;
	}
}
