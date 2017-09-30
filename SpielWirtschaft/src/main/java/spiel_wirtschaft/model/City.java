package spiel_wirtschaft.model;

public class City extends AbstractModel {

	private long population;

	private String name;

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
