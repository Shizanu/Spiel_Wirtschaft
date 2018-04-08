package spiel_wirtschaft.controller;

import spiel_wirtschaft.model.AbstractBE;

public interface RundeBeendenEntityCtrl<T extends AbstractBE> {

	public void computeRundenDelta(T entity);

	public void applyRundenDelta(T entity);

}