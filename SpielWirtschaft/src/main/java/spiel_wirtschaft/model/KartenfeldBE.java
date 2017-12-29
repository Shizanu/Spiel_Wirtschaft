package spiel_wirtschaft.model;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class KartenfeldBE extends AbstractBE {

	@XmlElement
	private final ObjectProperty<GelaendeTypEnum> gelaendeTyp;

	@XmlElement
	private final int xPos;

	@XmlElement
	private final int yPos;

	public KartenfeldBE(int xPos, int yPos) {
		super();
		gelaendeTyp = new SimpleObjectProperty<>();
		this.xPos = xPos;
		this.yPos = yPos;
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

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

}
