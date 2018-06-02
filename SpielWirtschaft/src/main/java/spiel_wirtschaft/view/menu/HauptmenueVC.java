package spiel_wirtschaft.view.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import spiel_wirtschaft.controller.LoadSaveCtrl;
import spiel_wirtschaft.controller.SpielCtrl;
import spiel_wirtschaft.model.SpielBE;
import spiel_wirtschaft.view.AbstractViewController;
import spiel_wirtschaft.view.PrimaryStageManager;
import spiel_wirtschaft.view.ViewFactory;

@Component
public class HauptmenueVC extends AbstractViewController {

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

	public void onNeuesSpielClicked() {
		rootLayoutManager.setCenterNode(viewFactory.loadView(NeuesSpielMenueVC.class));
	}

	public void onSpielFortsetzenClicked() {
		SpielBE spiel = loadSaveCtrl.load();
		spielCtrl.setCurrentlyActiveSpiel(spiel);
		rootLayoutManager.showSpiel();
	}

	public void onSpielLadenClicked() {
		// TODO ANNE: implement
	}
}
