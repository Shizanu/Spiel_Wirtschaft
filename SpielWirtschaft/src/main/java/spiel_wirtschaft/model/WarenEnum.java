package spiel_wirtschaft.model;

import java.util.HashMap;
import java.util.Map;

public enum WarenEnum implements Ware {
	// TODO WarenEnum package private machen und alle das interface benutzen lassen, damit Waren auch über config
	// spezifiziert werden können? -> Festes Model und config in unterschiedliche packages?

	NAHRUNG, BAUMATERIAL, METALLE, WERKZEUGE;

	public static Map<WarenEnum, Long> createWarenAnzahlMap() {
		Map<WarenEnum, Long> warenMap = new HashMap<>();
		for (WarenEnum ware : WarenEnum.values()) {
			warenMap.put(ware, 0L);
		}
		return warenMap;
	}
}
