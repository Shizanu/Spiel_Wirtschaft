package spiel_wirtschaft.view;

import javafx.fxml.FXML;
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
	}

	public void setMain(Main main) {
		this.main = main;

		stadtTable.setItems(this.main.getStaedte());
	}

}
