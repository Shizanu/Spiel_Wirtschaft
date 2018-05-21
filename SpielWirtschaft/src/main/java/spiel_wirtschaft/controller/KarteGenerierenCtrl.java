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
public class KarteGenerierenCtrl extends AbstractController {

	// TODO: Move random to general class
	private Random random;

	public KarteGenerierenCtrl() {
		super();
		long seed = new Random().nextLong();
		this.random = new Random(seed);
		LOG.debug("Initialized Random with seed: " + seed); // TODO TRO: Use proper logging
	}

	public SpielkarteBE generiereKarte(int xSize, int ySize, KartenGenerierungsModus modus) {
		SpielkarteBE spielKarte = new SpielkarteBE();
		spielKarte.init(xSize, ySize);
		KartenfeldBE[][] kartenfelder = spielKarte.getKartenfelder();

		switch (modus) {
		case ZUFAELLIG:
			for (int feldXRichtung = 0; feldXRichtung < xSize; feldXRichtung++) {
				for (int feldYRichtung = 0; feldYRichtung < ySize; feldYRichtung++) {
					GelaendeTypEnum gelaende;
					if (random.nextBoolean() == true) {
						gelaende = GelaendeTypEnum.WASSER;
					} else {
						gelaende = GelaendeTypEnum.LAND;
					}
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(gelaende);
				}
			}
			addStartStadt(spielKarte, "Kuschelwuschel");
			break;
		case WASSERREICH:
			for (int feldXRichtung = 0; feldXRichtung < xSize; feldXRichtung++) {
				for (int feldYRichtung = 0; feldYRichtung < ySize; feldYRichtung++) {
					GelaendeTypEnum gelaende;
					if (random.nextDouble() < 0.8) {
						gelaende = GelaendeTypEnum.WASSER;
					} else {
						gelaende = GelaendeTypEnum.LAND;
					}
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(gelaende);
				}
			}
			addStartStadt(spielKarte, "Kuschelwuschel");
			break;
		case WASSERARM:
			for (int feldXRichtung = 0; feldXRichtung < xSize; feldXRichtung++) {
				for (int feldYRichtung = 0; feldYRichtung < ySize; feldYRichtung++) {
					GelaendeTypEnum gelaende;
					if (random.nextDouble() < 0.2) {
						gelaende = GelaendeTypEnum.WASSER;
					} else {
						gelaende = GelaendeTypEnum.LAND;
					}
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(gelaende);
				}
			}
			addStartStadt(spielKarte, "Kuschelwuschel");
			break;
		case KONTINENT:
			for (int feldXRichtung = 0; feldXRichtung < xSize; feldXRichtung++) {
				for (int feldYRichtung = 0; feldYRichtung < ySize; feldYRichtung++) {
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(GelaendeTypEnum.WASSER);
				}
			}
			kontinenteErstellen(spielKarte);
			addStartStadt(spielKarte, "Kuschelwuschel");
			break;
		default:
			for (int feldXRichtung = 0; feldXRichtung < xSize; feldXRichtung++) {
				for (int feldYRichtung = 0; feldYRichtung < ySize; feldYRichtung++) {
					GelaendeTypEnum gelaende;
					if ((feldXRichtung + feldYRichtung) % 2 == 0) {
						gelaende = GelaendeTypEnum.WASSER;
					} else {
						gelaende = GelaendeTypEnum.LAND;
					}
					kartenfelder[feldXRichtung][feldYRichtung].setGelaendeTyp(gelaende);
				}
			}
			addStartStadt(spielKarte, "Kuschelwuschel");
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
			int posX = random.nextInt(groesseX);
			int posY = random.nextInt(groesseY);
			KartenfeldBE aktuellesFeld = kartenfelder[posX][posY];
			aktuellesFeld.setGelaendeTyp(GelaendeTypEnum.LAND);
			Queue<KartenfeldBE> nachbarn = karte.getNachbarfelder(aktuellesFeld);
			while (!nachbarn.isEmpty()) {
				landmassenWarteschlange.add(nachbarn.poll());
			}
		}

		int tmp = (int) Math.floor(groesseY * groesseX * (random.nextDouble() / 2 + 0.05));
		// landmassenWarteschlange.add(kartenfelder[0][0]);
		while (zaehlerLandmasse < tmp && !landmassenWarteschlange.isEmpty()) {
			Collections.shuffle(landmassenWarteschlange, random);
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

	private void addStartStadt(SpielkarteBE karte, String stadtName) {
		boolean stadtNotSet = true;
		while (stadtNotSet) {
			int posX = random.nextInt(karte.getKartenfelder().length);
			int posY = random.nextInt(karte.getKartenfelder().length);
			if (karte.getKartenfelder()[posX][posY].getGelaendeTyp().equals(GelaendeTypEnum.LAND)) {
				KartenKoordinatenBE kartenkoordinaten = new KartenKoordinatenBE();
				kartenkoordinaten.init(posX + 0.5, posY + 0.5);
				StadtBE startStadt = new StadtBE(10, stadtName, kartenkoordinaten);
				karte.getStaedte().add(startStadt);
				stadtNotSet = false;
			}
		}
	}
}
