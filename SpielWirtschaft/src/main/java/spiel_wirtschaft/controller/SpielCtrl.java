package spiel_wirtschaft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spiel_wirtschaft.model.KartenGenerierungsModus;
import spiel_wirtschaft.model.SpielBE;

@Component
public class SpielCtrl extends AbstractController {

	@Autowired
	NeuesSpielCtrl neuesSpielCtrl;

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

}
