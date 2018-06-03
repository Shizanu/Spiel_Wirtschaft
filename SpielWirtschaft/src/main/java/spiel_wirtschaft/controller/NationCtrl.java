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
import spiel_wirtschaft.model.rundendelta.StadtEffekteFuerNation;

@Component
public class NationCtrl extends AbstractController implements RundeBeendenEntityCtrl<NationBE> {

	private static final BigDecimal GELD_BASIS_EINNAHMEN_PRO_RUNDE = new BigDecimal("2");

	@Autowired
	private StadtCtrl stadtCtrl;

	@Autowired
	private SpielCtrl spielCtrl;

	@Override
	public void computeRundenDelta(NationBE nation) {
		nation.getStaedte().forEach((stadt) -> stadtCtrl.computeEffekteFuerNation(stadt));

		NationRundenDelta nationRundenDelta = new NationRundenDelta();

		nationRundenDelta.geldChange = GELD_BASIS_EINNAHMEN_PRO_RUNDE;
		nation.getStaedte().forEach(
				(stadt) -> applyStadtEffekteFuerNationToRundenDelta(nationRundenDelta, (stadt.getEffekteFuerNation())));

		nation.setRundenDelta(nationRundenDelta);
	}

	public void applyStadtEffekteFuerNationToRundenDelta(NationRundenDelta rundenDelta,
			StadtEffekteFuerNation effekteFuerNation) {
		rundenDelta.geldChange = rundenDelta.geldChange.add(effekteFuerNation.geldEinnahmen)
				.subtract(effekteFuerNation.geldAusgaben);
		for (WarenEnum ware : WarenEnum.values()) {
			long oldValue = rundenDelta.warenChange.get(ware);
			long newValue = oldValue + effekteFuerNation.warenEinnahmen.get(ware)
					- effekteFuerNation.warenAusgaben.get(ware);
			rundenDelta.warenChange.put(ware, newValue);
		}
	}

	@Override
	public void applyRundenDelta(NationBE nation) {
		NationRundenDelta delta = nation.getRundenDelta();
		nation.setGeld(nation.getGeld().add(delta.geldChange));
		for (WarenEnum ware : WarenEnum.values()) {
			nation.getWaren().put(ware, nation.getWaren().get(ware) + delta.warenChange.get(ware));
		}
		nation.setRundenDelta(null);
	}

	// TODO Nicht mehr davon ausgehen, dass es nur eine Nation gibt. Nation des
	// aktiven spielers sollte dem NationCtrl nicht übergeben werden.
	public NationBE getNationDesSpielers() {
		return spielCtrl.getCurrentlyActiveSpiel().getNationen().get(0);
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
