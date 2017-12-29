package spiel_wirtschaft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spiel_wirtschaft.model.KartenGenerierungsModus;
import spiel_wirtschaft.model.SpielBE;
import spiel_wirtschaft.model.StadtBE;

@Component
public class SpielCtrl extends AbstractController {

	@Autowired
	private NeuesSpielCtrl neuesSpielCtrl;

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
		List<StadtBE> staedte = currentlyActiveSpiel.getSpielkarte().getStaedte();
		for (StadtBE stadt : staedte) {
			stadt.setEinwohnerzahl(stadt.getEinwohnerzahl() + 10);
		}
	}

}
