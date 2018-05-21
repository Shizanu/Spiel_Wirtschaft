package spiel_wirtschaft.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spiel_wirtschaft.model.NationBE;
import spiel_wirtschaft.model.rundendelta.NationRundenDelta;

@Component
public class NationCtrl extends AbstractController implements RundeBeendenEntityCtrl<NationBE> {

	private static final BigDecimal GELD_BASIS_EINNAHMEN_PRO_RUNDE = new BigDecimal("2");

	@Autowired
	private StadtCtrl stadtCtrl;

	@Override
	public void computeRundenDelta(NationBE nation) {
		nation.getStaedte().forEach((stadt) -> stadtCtrl.computeEffekteFuerNation(stadt));

		NationRundenDelta nationRundenDelta = new NationRundenDelta();

		nationRundenDelta.geldChange = GELD_BASIS_EINNAHMEN_PRO_RUNDE;
		nation.getStaedte().forEach((stadt) -> nationRundenDelta.geldChange = nationRundenDelta.geldChange
				.add(stadt.getEffekteFuerNation().geldEinnahmen));
		nation.getStaedte().forEach((stadt) -> nationRundenDelta.geldChange = nationRundenDelta.geldChange
				.add(stadt.getEffekteFuerNation().geldAusgaben));

		nation.setRundenDelta(nationRundenDelta);
	}

	@Override
	public void applyRundenDelta(NationBE nation) {
		NationRundenDelta delta = nation.getRundenDelta();
		nation.setGeld(nation.getGeld().add(delta.geldChange));
		nation.setRundenDelta(null);
	}

}
