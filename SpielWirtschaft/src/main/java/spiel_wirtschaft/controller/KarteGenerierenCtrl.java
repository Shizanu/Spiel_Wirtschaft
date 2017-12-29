package spiel_wirtschaft.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.springframework.stereotype.Component;

import spiel_wirtschaft.model.GelaendeTypEnum;
import spiel_wirtschaft.model.KartenGenerierungsModus;
import spiel_wirtschaft.model.KartenKoordinatenBE;
import spiel_wirtschaft.model.KartenfeldBE;
import spiel_wirtschaft.model.SpielkarteBE;
import spiel_wirtschaft.model.StadtBE;

@Component
public class KarteGenerierenCtrl {
	Random zufallsgenerator = new Random(10);

	public SpielkarteBE generiereKarte(int xSize, int ySize, KartenGenerierungsModus modus) {
		SpielkarteBE spielKarte = new SpielkarteBE(xSize, ySize);
		KartenfeldBE[][] kartenfelder = spielKarte.getKartenfelder();

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
					if (zufallsgenerator.nextDouble() < 0.8) {
						gelaende = GelaendeTypEnum.WASSER;
					} else {
						gelaende = GelaendeTypEnum.LAND;
					}
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(gelaende);
					;

				}
			}
			break;
		case WASSERARM:
			for (int feldXRichtung = 0; feldXRichtung < xSize; feldXRichtung++) {
				for (int feldYRichtung = 0; feldYRichtung < ySize; feldYRichtung++) {
					GelaendeTypEnum gelaende;
					if (zufallsgenerator.nextDouble() < 0.2) {
						gelaende = GelaendeTypEnum.WASSER;
					} else {
						gelaende = GelaendeTypEnum.LAND;
					}
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(gelaende);
					;

				}
			}
			break;
		case KONTINENT:
			for (int feldXRichtung = 0; feldXRichtung < xSize; feldXRichtung++) {
				for (int feldYRichtung = 0; feldYRichtung < ySize; feldYRichtung++) {
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(GelaendeTypEnum.WASSER);
				}
			}
			kontinenteErstellen(spielKarte);
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

	private void kontinenteErstellen(SpielkarteBE karte) {
		int zaehlerLandmasse = 0;
		KartenfeldBE[][] kartenfelder = karte.getKartenfelder();
		int groesseX = kartenfelder.length;
		int groesseY = kartenfelder[0].length;
		List<KartenfeldBE> landmassenWarteschlange = new LinkedList<KartenfeldBE>();
		int anzahlKontinente = 1;
		for (int verteileKontinente = 0; verteileKontinente < anzahlKontinente; verteileKontinente++) {
			int posX = zufallsgenerator.nextInt(groesseX);
			int posY = zufallsgenerator.nextInt(groesseY);
			KartenfeldBE aktuellesFeld = kartenfelder[posX][posY];
			aktuellesFeld.setGelaendeTyp(GelaendeTypEnum.LAND);
			KartenKoordinatenBE stadtKoordinaten = new KartenKoordinatenBE(posX, posY);
			StadtBE startStadt = new StadtBE(10, "Kuschelwuschel", stadtKoordinaten);
			karte.getStaedte().add(startStadt);
			Queue<KartenfeldBE> nachbarn = karte.getNachbarfelder(aktuellesFeld);
			while (!nachbarn.isEmpty()) {
				landmassenWarteschlange.add(nachbarn.poll());
			}
		}

		int tmp = (int) Math.floor(groesseY * groesseX * (zufallsgenerator.nextDouble() / 2 + 0.05));
		// landmassenWarteschlange.add(kartenfelder[0][0]);
		while (zaehlerLandmasse < tmp && !landmassenWarteschlange.isEmpty()) {
			Collections.shuffle(landmassenWarteschlange);
			KartenfeldBE aktuellesFeld = landmassenWarteschlange.get(0);
			if (aktuellesFeld.getGelaendeTyp().equals(GelaendeTypEnum.WASSER)) {
				aktuellesFeld.setGelaendeTyp(GelaendeTypEnum.LAND);
				Queue<KartenfeldBE> nachbarn = karte.getNachbarfelder(aktuellesFeld);
				while (!nachbarn.isEmpty()) {
					landmassenWarteschlange.add(nachbarn.poll());
				}
				zaehlerLandmasse++;
			}

		}
	}
}
