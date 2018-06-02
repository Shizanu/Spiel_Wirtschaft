package spiel_wirtschaft.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spiel_wirtschaft.controller.LoadSaveCtrl;
import spiel_wirtschaft.controller.SpielCtrl;

@Component
public class RootLayoutVC extends AbstractViewController {

	@Autowired
	private SpielCtrl aktuellesSpiel;

	@Autowired
	private LoadSaveCtrl loadSaveCtrl;

	public void onSchließenClicked() {
		// TODO: Warnmeldung
		System.exit(0);
	}

	// TODO: Option ausblenden, wenn noch kein Spiel gestartet
	public void onSpeichernClicked() {
		loadSaveCtrl.save(aktuellesSpiel.getCurrentlyActiveSpiel());

	}

	// TODO: Option ausblenden, wenn noch kein Spiel gestartet
	public void onSpeichernUndSchließenClicked() {
		// TODO Warnmeldung

		System.exit(0);
	}
}
