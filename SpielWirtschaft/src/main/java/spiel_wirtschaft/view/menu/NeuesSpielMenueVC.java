package spiel_wirtschaft.view.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import spiel_wirtschaft.controller.SpielCtrl;
import spiel_wirtschaft.model.KartenGenerierungsModus;
import spiel_wirtschaft.view.AbstractViewController;
import spiel_wirtschaft.view.PrimaryStageManager;

@Component
public class NeuesSpielMenueVC extends AbstractViewController {

	@Autowired
	private PrimaryStageManager rootLayoutManager;

	@Autowired
	private SpielCtrl spielCtrl;

	@FXML
	private ChoiceBox<KartenGenerierungsModus> kartentypInput;

	@FXML
	private void initialize() {
		ObservableList<KartenGenerierungsModus> items = FXCollections
				.observableArrayList(KartenGenerierungsModus.values());
		kartentypInput.setItems(items);
		kartentypInput.getSelectionModel().selectFirst();
	}

	public void onSpielStartenClicked() {
		spielCtrl.neuesSpielStarten(kartentypInput.getSelectionModel().getSelectedItem());
		rootLayoutManager.showSpiel();
	}

	public void onAbbrechenClicked() {
		rootLayoutManager.showHauptmenue();
	}
}
