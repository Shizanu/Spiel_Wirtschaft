package spiel_wirtschaft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spiel_wirtschaft.model.Gebaeude;
import spiel_wirtschaft.model.GebaeudeEnum;
import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.model.rundendelta.StadtEffekteFuerNation;
import spiel_wirtschaft.model.rundendelta.StadtRundenDelta;

@Component
public class StadtCtrl extends AbstractController implements RundeBeendenEntityCtrl<StadtBE> {

	private static final int STADT_BASIS_WACHSUM_PRO_RUNDE = 10;

	@Autowired
	private NationCtrl nationCtrl;

	public boolean gebaeudeKannGebautWerden(StadtBE stadt, Gebaeude gebaeude) {
		return nationCtrl.kannBezahltWerden(stadt.getNation(), gebaeude.getGeldKosten(), gebaeude.getWarenKosten())
				&& !stadt.getGebauteGebaeude().contains(gebaeude);
	}

	public void gebaudeBauen(StadtBE stadt, Gebaeude gebaeude) {
		nationCtrl.bezahlen(stadt.getNation(), gebaeude.getGeldKosten(), gebaeude.getWarenKosten());
		// TODO TRO: StadtBE sollte das Interface für Gebäude benutzen
		stadt.addGebaeude((GebaeudeEnum) gebaeude);
	}

	@Override
	public void computeRundenDelta(StadtBE stadt) {
		StadtRundenDelta stadtRundenDelta = new StadtRundenDelta();
		stadtRundenDelta.einwohnerzahlChange = STADT_BASIS_WACHSUM_PRO_RUNDE;

		stadt.getGebauteGebaeude()
				.forEach((gebaeude) -> gebaeude.getStadtIsolatedEffect().apply(stadt, stadtRundenDelta));

		stadt.setRundenDelta(stadtRundenDelta);
	}

	@Override
	public void applyRundenDelta(StadtBE stadt) {
		StadtRundenDelta delta = stadt.getRundenDelta();
		stadt.setEinwohnerzahl(stadt.getEinwohnerzahl() + delta.einwohnerzahlChange);
		stadt.setRundenDelta(null);
	}

	public void computeEffekteFuerNation(StadtBE stadt) {
		StadtEffekteFuerNation stadtEffekteFuerNation = new StadtEffekteFuerNation();
		stadt.getGebauteGebaeude()
				.forEach((gebaeude) -> gebaeude.getStadtEffektFuerNation().apply(stadt, stadtEffekteFuerNation));
		stadt.setEffekteFuerNation(stadtEffekteFuerNation);
	}
}
