package spiel_wirtschaft.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import spiel_wirtschaft.main.Main;
import spiel_wirtschaft.model.KartenGenerierungsModus;

public class NeuesSpielMenueVC extends AbstractViewController {

	@FXML
	private ChoiceBox<KartenGenerierungsModus> kartentypInput;

	private Main main;

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	private void initialize() {
		ObservableList<KartenGenerierungsModus> items = FXCollections
				.observableArrayList(KartenGenerierungsModus.values());
		kartentypInput.setItems(items);
		kartentypInput.getSelectionModel().selectFirst();
	}

	public void onSpielStartenClicked() {
		main.neuesSpielStarten(kartentypInput.getSelectionModel().getSelectedItem());
	}

	public void onAbbrechenClicked() {
		main.showHauptmenue();
	}
}
