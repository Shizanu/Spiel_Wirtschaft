package spiel_wirtschaft.model.rundendelta;

import java.math.BigDecimal;
import java.util.Map;

import spiel_wirtschaft.model.WarenEnum;

public class StadtEffekteFuerNation {

	/**
	 * Einnahmen in der Stadt pro Runde
	 */
	public BigDecimal geldEinnahmen = new BigDecimal("0");
	/**
	 * Ausgaben in der Stadt je Runde (als negative Zahl)
	 */
	public BigDecimal geldAusgaben = new BigDecimal("0");

	/**
	 * In der Stadt produzierte Waren pro Runde
	 */
	public Map<WarenEnum, Long> warenEinnahmen = WarenEnum.createWarenAnzahlMap();

	/**
	 * In der Stadt verbrauchte Waren pro Runde
	 */
	public Map<WarenEnum, Long> warenAusgaben = WarenEnum.createWarenAnzahlMap();

	public void increaseWarenEinnahme(WarenEnum ware, long value) {
		warenEinnahmen.put(ware, warenEinnahmen.get(ware) + value);
	}

	public void increaseWarenAusgabe(WarenEnum ware, long value) {
		warenAusgaben.put(ware, warenAusgaben.get(ware) + value);
	}
}
