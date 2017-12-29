package spiel_wirtschaft.model;

import javax.xml.bind.annotation.XmlElement;

public class KartenKoordinatenBE extends AbstractBE {

	@XmlElement
	final double xAxisPosition;

	@XmlElement
	final double yAxisPosition;

	public double getxAxisPosition() {
		return xAxisPosition;
	}

	public double getyAxisPosition() {
		return yAxisPosition;
	}

	public KartenKoordinatenBE(double xAxisPosition, double yAxisPosition) {
		super();
		this.xAxisPosition = xAxisPosition;
		this.yAxisPosition = yAxisPosition;
	}

}
