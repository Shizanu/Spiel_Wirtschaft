package spiel_wirtschaft.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlRootElement
public class StadtBE extends AbstractBE {

	@XmlElement
	private final LongProperty einwohnerzahl;

	@XmlElement
	private final StringProperty stadtname;

	public StadtBE() {
		super();
		einwohnerzahl = new SimpleLongProperty();
		stadtname = new SimpleStringProperty();
	}

	public StadtBE(String stadtname, long einwohnerzahl) {
		this();
		setEinwohnerzahl(einwohnerzahl);
		setStadtname(stadtname);
	}

	public final LongProperty einwohnerzahlProperty() {
		return this.einwohnerzahl;
	}

	public final long getEinwohnerzahl() {
		return this.einwohnerzahlProperty().get();
	}

	public final void setEinwohnerzahl(final long einwohnerzahl) {
		this.einwohnerzahlProperty().set(einwohnerzahl);
	}

	public final StringProperty stadtnameProperty() {
		return this.stadtname;
	}

	public final String getStadtname() {
		return this.stadtnameProperty().get();
	}

	public final void setStadtname(final String stadtname) {
		this.stadtnameProperty().set(stadtname);
	}

}
