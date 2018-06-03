package spiel_wirtschaft.view.spiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import spiel_wirtschaft.controller.StadtCtrl;
import spiel_wirtschaft.model.Gebaeude;
import spiel_wirtschaft.model.GebaeudeEnum;
import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.view.AbstractViewController;
import spiel_wirtschaft.view.PrimaryStageManager;

@Component
public class StadtMenueLeftPanelVC extends AbstractViewController {

	@FXML
	private Button kaufenButton;

	@FXML
	private Label stadtNameLabel;

	@FXML
	private TableView<verfuegbareGebaeudeRow> verfuegbareGebaeudeTable;

	@FXML
	private TableColumn<verfuegbareGebaeudeRow, String> gebaeudeNameColumn;

	@FXML
	private TableColumn<verfuegbareGebaeudeRow, Integer> gebaeudeKostenColumn;

	@FXML
	private TableColumn<verfuegbareGebaeudeRow, String> gebaeudeVorteileColumn;

	private ObservableList<verfuegbareGebaeudeRow> gebaeudeTableData = FXCollections.observableArrayList();

	@FXML
	private TableView<vorhandeneGebaeudeRow> vorhandeneGebaeudeTable;

	@FXML
	private TableColumn<vorhandeneGebaeudeRow, String> vorhandeneGebaeudeNameColumn;

	@FXML
	private TableColumn<vorhandeneGebaeudeRow, String> vorhandeneGebaeudeVorteileColumn;

	@Autowired
	private StadtCtrl stadtCtrl;

	@Autowired
	private PrimaryStageManager primaryStageManager;

	private ObservableList<vorhandeneGebaeudeRow> vorhandeneGebaeudeTableData = FXCollections.observableArrayList();

	private StadtBE stadt;

	@FXML
	private void initialize() {
		kaufenButton.setDisable(true);
		gebaeudeNameColumn.setCellValueFactory(row -> row.getValue().displayGebaeudeName);
		gebaeudeKostenColumn.setCellValueFactory(row -> row.getValue().displayGebaeudeKosten.asObject());
		gebaeudeVorteileColumn.setCellValueFactory(row -> row.getValue().displayGebaeudeVorteile);

	}

	public void initializeShowStadt(StadtBE stadt) {
		LOG.debug(stadt.getStadtname());
		stadtNameLabel.setText(stadt.getStadtname());
		this.stadt = stadt;

		fillVerfuegbareGebaeude(stadt);

		fillVorhandeneGebaeude(stadt);
	}

	private void fillVerfuegbareGebaeude(StadtBE stadt) {
		gebaeudeTableData.clear();
		for (GebaeudeEnum gebaeude : GebaeudeEnum.values()) {
			if (!stadt.getGebauteGebaeude().contains(gebaeude)) {
				gebaeudeTableData.add(new verfuegbareGebaeudeRow(gebaeude));
			}
		}
		verfuegbareGebaeudeTable.setItems(gebaeudeTableData);
		verfuegbareGebaeudeTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> verfuegbaresGebaeudeAusgewaehlt(newValue));
	}

	private void fillVorhandeneGebaeude(StadtBE stadt) {
		vorhandeneGebaeudeNameColumn.setCellValueFactory(row -> row.getValue().displayGebaeudeName);
		vorhandeneGebaeudeVorteileColumn.setCellValueFactory(row -> row.getValue().displayGebaeudeVorteile);
		vorhandeneGebaeudeTableData.clear();
		if (!stadt.getGebauteGebaeude().isEmpty()) {
			for (Gebaeude gebaeude : stadt.getGebauteGebaeude()) {
				vorhandeneGebaeudeTableData.add(new vorhandeneGebaeudeRow(gebaeude));
			}
			vorhandeneGebaeudeTable.setItems(vorhandeneGebaeudeTableData);
		}
	}

	private void verfuegbaresGebaeudeAusgewaehlt(verfuegbareGebaeudeRow selektiertesGebaeude) {
		if (selektiertesGebaeude == null) {
			kaufenButton.setDisable(true);
		} else {
			boolean kannGekauftWerden = stadtCtrl.gebaeudeKannGebautWerden(stadt, selektiertesGebaeude.gebaeudeEnum);
			kaufenButton.setDisable(!kannGekauftWerden);
		}
	}

	public void onKaufenClicked() {
		verfuegbareGebaeudeRow aktuellesGebaeudeRow = verfuegbareGebaeudeTable.getSelectionModel().getSelectedItem();
		kaufenButton.setDisable(true);
		stadtCtrl.gebaudeBauen(stadt, aktuellesGebaeudeRow.gebaeudeEnum);
		initializeShowStadt(stadt);
		primaryStageManager.showDefaultRightPanel(); // TODO TRO: Introduce proper UI data refresh mechanism
	}

	private static class verfuegbareGebaeudeRow {

		private GebaeudeEnum gebaeudeEnum;

		private final StringProperty displayGebaeudeName;

		private final IntegerProperty displayGebaeudeKosten;

		private final StringProperty displayGebaeudeVorteile;

		public verfuegbareGebaeudeRow(GebaeudeEnum gebaeudeEnum) {
			super();
			this.gebaeudeEnum = gebaeudeEnum;
			displayGebaeudeName = new SimpleStringProperty(gebaeudeEnum.getGebaeudeName());
			// TODO TRO: Darstellung von BigDecimal in Oberfläche
			displayGebaeudeKosten = new SimpleIntegerProperty(gebaeudeEnum.getGeldKosten().intValue());
			displayGebaeudeVorteile = new SimpleStringProperty(gebaeudeEnum.getVorteileDisplayText());
		}

		public final StringProperty displayGebaeudeNameProperty() {
			return this.displayGebaeudeName;
		}

		public final String getDisplayGebaeudeName() {
			return this.displayGebaeudeNameProperty().get();
		}

		public final void setDisplayGebaeudeName(final String displayGebaeudeName) {
			this.displayGebaeudeNameProperty().set(displayGebaeudeName);
		}

		public final IntegerProperty displayGebaeudeKostenProperty() {
			return this.displayGebaeudeKosten;
		}

		public final Integer getDisplayGebaeudeKosten() {
			return this.displayGebaeudeKostenProperty().get();
		}

		public final void setDisplayGebaeudeKosten(final Integer displayGebaeudeKosten) {
			this.displayGebaeudeKostenProperty().set(displayGebaeudeKosten);
		}

		public final StringProperty displayGebaeudeVorteileProperty() {
			return this.displayGebaeudeVorteile;
		}

		public final String getDisplayGebaeudeVorteile() {
			return this.displayGebaeudeVorteileProperty().get();
		}

		public final void setDisplayGebaeudeVorteile(final String displayGebaeudeVorteile) {
			this.displayGebaeudeVorteileProperty().set(displayGebaeudeVorteile);
		}

	}

	private static class vorhandeneGebaeudeRow {

		private Gebaeude gebaeudeEnum;

		private final StringProperty displayGebaeudeName;

		private final StringProperty displayGebaeudeVorteile;

		public vorhandeneGebaeudeRow(Gebaeude gebaeudeEnum) {
			super();
			this.gebaeudeEnum = gebaeudeEnum;
			displayGebaeudeName = new SimpleStringProperty(gebaeudeEnum.getGebaeudeName());
			displayGebaeudeVorteile = new SimpleStringProperty(gebaeudeEnum.getVorteileDisplayText());
		}

		public final StringProperty displayGebaeudeNameProperty() {
			return this.displayGebaeudeName;
		}

		public final String getDisplayGebaeudeName() {
			return this.displayGebaeudeNameProperty().get();
		}

		public final void setDisplayGebaeudeName(final String displayGebaeudeName) {
			this.displayGebaeudeNameProperty().set(displayGebaeudeName);
		}

		public final StringProperty displayGebaeudeVorteileProperty() {
			return this.displayGebaeudeVorteile;
		}

		public final String getDisplayGebaeudeVorteile() {
			return this.displayGebaeudeVorteileProperty().get();
		}

		public final void setDisplayGebaeudeVorteile(final String displayGebaeudeVorteile) {
			this.displayGebaeudeVorteileProperty().set(displayGebaeudeVorteile);
		}

	}

}
