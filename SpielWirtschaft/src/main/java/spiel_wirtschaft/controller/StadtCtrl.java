package spiel_wirtschaft.controller;

import org.springframework.stereotype.Component;

import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.model.rundendelta.StadtEffekteFuerNation;
import spiel_wirtschaft.model.rundendelta.StadtRundenDelta;

@Component
public class StadtCtrl extends AbstractController implements RundeBeendenEntityCtrl<StadtBE> {

	private static final int STADT_BASIS_WACHSUM_PRO_RUNDE = 10;

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
