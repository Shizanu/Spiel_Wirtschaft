package spiel_wirtschaft.controller;

import spiel_wirtschaft.model.GelaendeTypEnum;
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
	public SpielBE neuesDummySpielStarten() {
		SpielkarteBE spielkarte = new SpielkarteBE(10, 10);
		for (int xPos = 0; xPos < spielkarte.getxSize(); xPos++) {
			for (int yPos = 0; yPos < spielkarte.getySize(); yPos++) {
				GelaendeTypEnum gelaendeTyp;
				if ((xPos + yPos) % 2 == 0) {
					gelaendeTyp = GelaendeTypEnum.LAND;
				} else {
					gelaendeTyp = GelaendeTypEnum.WASSER;
				}
				spielkarte.getKartenfelder()[xPos][yPos].setGelaendeTyp(gelaendeTyp);
			}
		}
		return new SpielBE(spielkarte);
	}
}
