package spiel_wirtschaft.view.spiel;

import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import spiel_wirtschaft.model.GebaeudeEnum;
import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.view.AbstractViewController;

@Component
public class StadtMenueLeftPanelVC extends AbstractViewController {

	@FXML
	private Label stadtNameLabel;

	@FXML
	private TableView<GebaeudeEnum> stadtUebersichtTable;

	@FXML
	private TableColumn<GebaeudeEnum, String> gebaeudeNameColumn;

	@FXML
	private TableColumn<GebaeudeEnum, Integer> kostenColumn;

	@FXML
	private TableColumn<GebaeudeEnum, String> vorteileColumn;

	private ObservableList<GebaeudeEnum> gebaeudeTableData = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		for (GebaeudeEnum gebaeude : GebaeudeEnum.values()) {
			gebaeudeTableData.add(gebaeude);
		}

		// TODO: analog zu DefaultRightPanelVC static Klasse erzeugen. Da ValueFactory
		// StringProperty erwartet
		// gebaeudeNameColumn.setCellValueFactory(row ->
		// row.getValue().getGebaeudeName());
		// kostenColumn.setCellFactory(row -> row.getValue().getGebaeudeKosten());
	}

	public void initializeShowStadt(StadtBE stadt) {
		LOG.debug(stadt.getStadtname());
		stadtNameLabel.setText(stadt.getStadtname());
	}
}
