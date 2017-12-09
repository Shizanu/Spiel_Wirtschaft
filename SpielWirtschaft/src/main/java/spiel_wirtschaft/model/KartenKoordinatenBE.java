package spiel_wirtschaft.model;

public class KartenKoordinatenBE extends AbstractBE {

	final double xAxisPosition;

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
