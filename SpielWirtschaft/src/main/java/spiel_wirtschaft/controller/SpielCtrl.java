package spiel_wirtschaft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spiel_wirtschaft.model.AbstractBE;
import spiel_wirtschaft.model.KartenGenerierungsModus;
import spiel_wirtschaft.model.NationBE;
import spiel_wirtschaft.model.SpielBE;
import spiel_wirtschaft.model.StadtBE;

@Component
public class SpielCtrl extends AbstractController {

	@Autowired
	private NeuesSpielCtrl neuesSpielCtrl;

	@Autowired
	private StadtCtrl stadtCtrl;

	@Autowired
	private NationCtrl nationCtrl;

	private SpielBE currentlyActiveSpiel;

	public SpielBE getCurrentlyActiveSpiel() {
		return currentlyActiveSpiel;
	}

	public void setCurrentlyActiveSpiel(SpielBE currentlyActiveSpiel) {
		this.currentlyActiveSpiel = currentlyActiveSpiel;
	}

	public void neuesSpielStarten(KartenGenerierungsModus kartentyp) {
		currentlyActiveSpiel = neuesSpielCtrl.neuesDummySpielStarten(kartentyp);
	}

	public void rundeBeenden() {
		currentlyActiveSpiel.incrementRunde();

		RundeBeendenFunction computeDeltaPhase = new RundeBeendenFunction() {

			@Override
			public <T extends AbstractBE> void applyPhase(RundeBeendenEntityCtrl<T> ctrl, T entity) {
				ctrl.computeRundenDelta(entity);

			}
		};
		processRundeBeendenPhase(computeDeltaPhase);

		RundeBeendenFunction applyDeltaPhase = new RundeBeendenFunction() {

			@Override
			public <T extends AbstractBE> void applyPhase(RundeBeendenEntityCtrl<T> ctrl, T entity) {
				ctrl.applyRundenDelta(entity);

			}
		};
		processRundeBeendenPhase(applyDeltaPhase);

	}

	public void processRundeBeendenPhase(RundeBeendenFunction phase) {
		List<NationBE> nationen = currentlyActiveSpiel.getNationen();
		for (NationBE nation : nationen) {
			phase.applyPhase(nationCtrl, nation);
		}
		List<StadtBE> staedte = currentlyActiveSpiel.getSpielkarte().getStaedte();
		for (StadtBE stadt : staedte) {
			phase.applyPhase(stadtCtrl, stadt);
		}
	}

	private interface RundeBeendenFunction {

		public <T extends AbstractBE> void applyPhase(RundeBeendenEntityCtrl<T> ctrl, T entity);
	}

}
