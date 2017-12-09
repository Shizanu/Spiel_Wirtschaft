package spiel_wirtschaft.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class KartenfeldBE extends AbstractBE {

	private final ObjectProperty<GelaendeTypEnum> gelaendeTyp;

	public KartenfeldBE() {
		super();
		this.gelaendeTyp = new SimpleObjectProperty<>();
	}

	public final ObjectProperty<GelaendeTypEnum> gelaendeTypProperty() {
		return this.gelaendeTyp;
	}

	public final GelaendeTypEnum getGelaendeTyp() {
		return this.gelaendeTypProperty().get();
	}

	public final void setGelaendeTyp(final GelaendeTypEnum gelaendeTyp) {
		this.gelaendeTypProperty().set(gelaendeTyp);
	}

}
