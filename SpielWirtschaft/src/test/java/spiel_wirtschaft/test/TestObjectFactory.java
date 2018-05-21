package spiel_wirtschaft.test;

import spiel_wirtschaft.controller.KarteGenerierenCtrl;
import spiel_wirtschaft.model.KartenGenerierungsModus;
import spiel_wirtschaft.model.SpielBE;
import spiel_wirtschaft.model.SpielkarteBE;

public class TestObjectFactory {

	public static SpielBE createBasicSpiel() {
		KarteGenerierenCtrl controller = new KarteGenerierenCtrl();
		SpielkarteBE karte = controller.generiereKarte(10, 10, KartenGenerierungsModus.KONTINENT);
		SpielBE testSpiel = new SpielBE(karte);
		return testSpiel;
	}

}
