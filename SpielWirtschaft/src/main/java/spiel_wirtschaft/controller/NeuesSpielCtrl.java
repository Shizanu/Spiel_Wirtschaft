package spiel_wirtschaft.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spiel_wirtschaft.model.KartenGenerierungsModus;
import spiel_wirtschaft.model.NationBE;
import spiel_wirtschaft.model.SpielBE;
import spiel_wirtschaft.model.SpielkarteBE;
import spiel_wirtschaft.model.StadtBE;

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
		SpielBE spiel = new SpielBE(spielkarte);

		// TODO Mehrere Nationen erstellen. Städte korrekt über Ctrl gründen und verknüpfen.
		NationBE nation = new NationBE();
		nation.setGeld(new BigDecimal(100));
		spiel.getNationen().add(nation);

		List<StadtBE> staedteAusSpielkarteGenerieren = spielkarte.getStaedte();
		assert staedteAusSpielkarteGenerieren.size() == 1;
		StadtBE stadt = staedteAusSpielkarteGenerieren.get(0);
		stadt.setNation(nation);
		nation.getStaedte().add(stadt);

		return spiel;
	}
}
