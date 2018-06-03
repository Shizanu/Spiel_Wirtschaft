package spiel_wirtschaft.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import spiel_wirtschaft.controller.LoadSaveCtrl;
import spiel_wirtschaft.controller.SpielCtrl;
import spiel_wirtschaft.model.SpielBE;

@Component
public class PopUpWarningVC extends AbstractViewController {

	@Autowired
	private PrimaryStageManager rootLayoutManager;

	@Autowired
	private LoadSaveCtrl loadSaveCtrl;

	@Autowired
	private SpielCtrl spielCtrl;

	@Autowired
	private ViewFactory viewFactory;

	@FXML
	private void initialize() {
		// Nothing to do
	}

	public void onSpielBeendenClicked() {
		Platform.exit();
	}

	public void onAbbrechenClicked() {
		SpielBE spiel = loadSaveCtrl.load();
		spielCtrl.setCurrentlyActiveSpiel(spiel);
		rootLayoutManager.showSpiel();
	}

}
