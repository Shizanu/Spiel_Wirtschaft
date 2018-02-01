package spiel_wirtschaft.controller;

import org.springframework.stereotype.Component;

import spiel_wirtschaft.model.GebaeudeEnum;
import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.model.rundendelta.StadtRundenDelta;

@Component
public class StadtCtrl extends AbstractController implements RundeBeendenEntityCtrl<StadtBE> {

	@Override
	public void computeRundenDelta(StadtBE stadt) {
		StadtRundenDelta delta = new StadtRundenDelta();
		delta.setEinwohnerzahlChange(10);
		for (GebaeudeEnum gebaeude : stadt.getGebauteGebaeude()) {
			gebaeude.getEffekt().apply(stadt, delta);
		}
		stadt.setRundenDelta(delta);
	}

	@Override
	public void applyRundenDelta(StadtBE stadt) {
		StadtRundenDelta delta = stadt.getRundenDelta();
		stadt.setEinwohnerzahl(stadt.getEinwohnerzahl() + delta.getEinwohnerzahlChange());
		stadt.setRundenDelta(null);
	}
}
