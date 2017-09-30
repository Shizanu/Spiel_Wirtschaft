package spiel_wirtschaft.model;

public abstract class AbstractModel {

	private final long ID;

	public AbstractModel() {
		super();
		ID = 1;
	}

	public long getID() {
		return ID;
	}

}
