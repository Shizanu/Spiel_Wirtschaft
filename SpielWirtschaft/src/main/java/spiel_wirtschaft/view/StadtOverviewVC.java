package spiel_wirtschaft.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import spiel_wirtschaft.main.Main;
import spiel_wirtschaft.model.StadtBE;

public class StadtOverviewVC {
	@FXML
	private TableView<StadtBE> stadtTable;
	@FXML
	private TableColumn<StadtBE, String> nameColumn;
	@FXML
	private TableColumn<StadtBE, Number> einwohnerzahlColumn;

	@FXML
	private Label nameLabel;
	@FXML
	private Label einwohnerzahlLabel;

	private Main main;

	public StadtOverviewVC() {
		super();
	}

	@FXML
	private void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().stadtnameProperty());
		einwohnerzahlColumn.setCellValueFactory(cellData -> cellData.getValue().einwohnerzahlProperty());

		showStadtDetails(null);

		stadtTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showStadtDetails(newValue));
	}

	public void setMain(Main main) {
		this.main = main;

		stadtTable.setItems(this.main.getStaedte());
	}

	private void showStadtDetails(StadtBE stadt) {
		if (stadt != null) {
			nameLabel.setText(stadt.getStadtname());
			einwohnerzahlLabel.setText(String.valueOf(stadt.getEinwohnerzahl()));
		} else {
			nameLabel.setText("");
			einwohnerzahlLabel.setText("");
		}
	}

	@FXML
	private void handleWachsen() {
		StadtBE selectedStadt = stadtTable.getSelectionModel().getSelectedItem();
		if (selectedStadt == null) {
			showNoStadtSelectedAlert();
			return;
		}
		selectedStadt.setEinwohnerzahl(selectedStadt.getEinwohnerzahl() + 10);
		showStadtDetails(selectedStadt);
	}

	@FXML
	private void handleSchrumpfen() {
		StadtBE selectedStadt = stadtTable.getSelectionModel().getSelectedItem();
		if (selectedStadt == null) {
			showNoStadtSelectedAlert();
			return;
		}
		selectedStadt.setEinwohnerzahl(selectedStadt.getEinwohnerzahl() - 10);
		showStadtDetails(selectedStadt);
	}

	private void showNoStadtSelectedAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(main.getPrimaryStage());
		alert.setTitle("Keine Auswahl");
		alert.setHeaderText("Keine Stadt ausgewählt");
		alert.setContentText("Bitte wählen Sie zuerst eine Stadt aus.");
		alert.showAndWait();
	}

}
