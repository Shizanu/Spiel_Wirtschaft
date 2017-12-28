package spiel_wirtschaft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spiel_wirtschaft.model.KartenGenerierungsModus;
import spiel_wirtschaft.model.SpielBE;
import spiel_wirtschaft.model.SpielkarteBE;

/**
 * Beinhaltet alle Logik um ein neues Spiel zu starten.
 * 
 * @author Tobias Rojahn
 *
 */
@Component
public class NeuesSpielCtrl extends AbstractController {

	@Autowired
	KarteGenerierenCtrl karteGenerierenCtrl;

	/**
	 * @return Erzeugt ein Dummy-Spiel um die Oberfläche zu entwickeln.
	 */
	public SpielBE neuesDummySpielStarten(KartenGenerierungsModus kartenGenerierungsModus) {
		SpielkarteBE spielkarte = karteGenerierenCtrl.generiereKarte(10, 10, kartenGenerierungsModus);
		return new SpielBE(spielkarte);
	}
}
