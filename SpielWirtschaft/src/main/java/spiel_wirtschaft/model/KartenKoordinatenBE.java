package spiel_wirtschaft.model;

import javax.xml.bind.annotation.XmlElement;

public class KartenKoordinatenBE extends AbstractBE {

	@XmlElement
	double xAxisPosition;

	@XmlElement
	double yAxisPosition;

	public double getxAxisPosition() {
		return xAxisPosition;
	}

	public double getyAxisPosition() {
		return yAxisPosition;
	}

	public KartenKoordinatenBE() {
		super();
		;
	}

	public void init(double xAxisPosition, double yAxisPosition) {
		this.xAxisPosition = xAxisPosition;
		this.yAxisPosition = yAxisPosition;
	}

}
