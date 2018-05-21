package spiel_wirtschaft.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import spiel_wirtschaft.model.NationBE;
import spiel_wirtschaft.model.Ware;
import spiel_wirtschaft.model.WarenEnum;
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

	public void bezahlen(NationBE nation, BigDecimal geldKosten, List<Pair<Ware, Long>> warenKosten) {
		Assert.isTrue(kannBezahltWerden(nation, geldKosten, warenKosten), "Muss vorher geprüft werden.");
		nation.setGeld(nation.getGeld().subtract(geldKosten));
		for (Pair<Ware, Long> kostenEintrag : warenKosten) {
			// TODO TRO: Use Interface fuer Ware -> benötigt ID oder so
			nation.subtrahiereVonWarenmenge((WarenEnum) kostenEintrag.getLeft(), kostenEintrag.getRight());
		}
	}

	public boolean kannBezahltWerden(NationBE nation, BigDecimal geldKosten, List<Pair<Ware, Long>> warenKosten) {
		if (nation.getGeld().compareTo(geldKosten) < 0) {
			return false;
		}
		for (Pair<Ware, Long> kostenEintrag : warenKosten) {
			Ware ware = kostenEintrag.getKey();
			long benoetigteMenge = kostenEintrag.getRight();
			// TODO TRO: Use Interface fuer Ware -> benötigt ID oder so
			if (nation.getVerfuegbareMenge((WarenEnum) ware) < benoetigteMenge) {
				return false;
			}
		}
		return true;
	}

}
