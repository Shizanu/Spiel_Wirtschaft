package spiel_wirtschaft.controller;

import spiel_wirtschaft.model.KartenGenerierungsModus;
import spiel_wirtschaft.model.SpielBE;
import spiel_wirtschaft.model.SpielkarteBE;

/**
 * Beinhaltet alle Logik um ein neues Spiel zu starten.
 * 
 * @author Tobias Rojahn
 *
 */
public class NeuesSpielCtrl extends AbstractController {

	/**
	 * @return Erzeugt ein Dummy-Spiel um die Oberfläche zu entwickeln.
	 */
	public SpielBE neuesDummySpielStarten(KartenGenerierungsModus kartenGenerierungsModus) {
		KarteGenerierenCtrl karteGenerierenCtrl = new KarteGenerierenCtrl();
		SpielkarteBE spielkarte = karteGenerierenCtrl.generiereKarte(10, 10, kartenGenerierungsModus);
		return new SpielBE(spielkarte);
	}
}
