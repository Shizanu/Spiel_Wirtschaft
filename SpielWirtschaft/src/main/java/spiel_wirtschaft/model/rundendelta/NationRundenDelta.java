package spiel_wirtschaft.model.rundendelta;

import java.math.BigDecimal;
import java.util.Map;

import spiel_wirtschaft.model.WarenEnum;

/**
 * Repräsentiert alle Änderungen an einer Nation die im Zuge des "Runde beendens" geschehen.
 *
 */
public class NationRundenDelta {

	public BigDecimal geldChange = new BigDecimal("0");

	public Map<WarenEnum, Long> warenChange = WarenEnum.createWarenAnzahlMap();

}
