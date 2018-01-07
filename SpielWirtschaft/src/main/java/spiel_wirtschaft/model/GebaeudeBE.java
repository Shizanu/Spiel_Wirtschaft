package spiel_wirtschaft.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GebaeudeBE extends AbstractBE {

	@XmlElement
	private String gebaeudeName;

	public GebaeudeBE() {
		super();
	}
}
