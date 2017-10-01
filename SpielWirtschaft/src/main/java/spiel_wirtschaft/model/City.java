package spiel_wirtschaft.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class City extends AbstractModel {

	private long population;

	private String name;

	public long getPopulation() {
		return population;
	}

	@XmlElement
	public void setPopulation(long population) {
		this.population = population;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

}
