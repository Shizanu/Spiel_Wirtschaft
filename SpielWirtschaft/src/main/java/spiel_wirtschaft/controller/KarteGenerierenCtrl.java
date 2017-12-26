package spiel_wirtschaft.controller;

import java.util.Random;

import spiel_wirtschaft.model.GelaendeTypEnum;
import spiel_wirtschaft.model.KartenGenerierungsModus;
import spiel_wirtschaft.model.KartenfeldBE;
import spiel_wirtschaft.model.SpielkarteBE;

public class KarteGenerierenCtrl {

	public SpielkarteBE generiereKarte(int xSize, int ySize, KartenGenerierungsModus modus) {

		SpielkarteBE spielKarte = new SpielkarteBE(xSize, ySize);
		KartenfeldBE[][] kartenfelder = spielKarte.getKartenfelder();
		Random zufallsgenerator = new Random();

		switch (modus) {
		case ZUFAELLIG:
			for (int feldXRichtung = 0; feldXRichtung < xSize; feldXRichtung++) {
				for (int feldYRichtung = 0; feldYRichtung < ySize; feldYRichtung++) {
					GelaendeTypEnum gelaende;
					if (zufallsgenerator.nextBoolean() == true) {
						gelaende = GelaendeTypEnum.WASSER;
					} else {
						gelaende = GelaendeTypEnum.LAND;
					}
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(gelaende);
					;

				}
			}
			break;
		case WASSERREICH:
			for (int feldXRichtung = 0; feldXRichtung < xSize; feldXRichtung++) {
				for (int feldYRichtung = 0; feldYRichtung < ySize; feldYRichtung++) {
					GelaendeTypEnum gelaende;
					if (zufallsgenerator.nextDouble() < 0.75) {
						gelaende = GelaendeTypEnum.WASSER;
					} else {
						gelaende = GelaendeTypEnum.LAND;
					}
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(gelaende);
					;

				}
			}
		case WASSERARM:
			for (int feldXRichtung = 0; feldXRichtung < xSize; feldXRichtung++) {
				for (int feldYRichtung = 0; feldYRichtung < ySize; feldYRichtung++) {
					GelaendeTypEnum gelaende;
					if (zufallsgenerator.nextDouble() < 0.25) {
						gelaende = GelaendeTypEnum.WASSER;
					} else {
						gelaende = GelaendeTypEnum.LAND;
					}
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(gelaende);
					;

				}
			}
		case KONTINENT:
			for (int feldXRichtung = 0; feldXRichtung < xSize; feldXRichtung++) {
				for (int feldYRichtung = 0; feldYRichtung < ySize; feldYRichtung++) {
					GelaendeTypEnum gelaende;
					if (zufallsgenerator.nextBoolean() == true) {
						gelaende = GelaendeTypEnum.WASSER;
					} else {
						gelaende = GelaendeTypEnum.LAND;
					}
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(gelaende);
					;

				}
			}
			break;
		default:
			for (int feldXRichtung = 0; feldXRichtung < xSize; feldXRichtung++) {
				for (int feldYRichtung = 0; feldYRichtung < ySize; feldYRichtung++) {
					GelaendeTypEnum gelaende;
					if (zufallsgenerator.nextBoolean() == true) {
						gelaende = GelaendeTypEnum.WASSER;
					} else {
						gelaende = GelaendeTypEnum.LAND;
					}
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(gelaende);
					;

				}
			}
			break;
		}

		return spielKarte;

	}

}
