package spiel_wirtschaft.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import spiel_wirtschaft.model.KartenGenerierungsModus;

@Component
public class NeuesSpielMenueVC extends AbstractViewController {

	@Autowired
	private RootLayoutManager rootLayoutManager;

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
		rootLayoutManager.neuesSpielStarten(kartentypInput.getSelectionModel().getSelectedItem());
	}

	public void onAbbrechenClicked() {
		rootLayoutManager.showHauptmenue();
	}
}
