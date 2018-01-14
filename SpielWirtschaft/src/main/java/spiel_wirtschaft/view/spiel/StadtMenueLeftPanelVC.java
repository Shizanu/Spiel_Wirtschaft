package spiel_wirtschaft.view.spiel;

import org.springframework.stereotype.Component;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
	private TableView<verfuegbareGebaeudeRow> verfuegbareGebaeudeTable;

	@FXML
	private TableColumn<verfuegbareGebaeudeRow, String> gebaeudeNameColumn;

	@FXML
	private TableColumn<verfuegbareGebaeudeRow, Integer> gebaeudeKostenColumn;

	@FXML
	private TableColumn<verfuegbareGebaeudeRow, String> gebaeudeVorteileColumn;

	private ObservableList<verfuegbareGebaeudeRow> gebaeudeTableData = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		gebaeudeNameColumn.setCellValueFactory(row -> row.getValue().displayGebaeudeName);
		gebaeudeKostenColumn.setCellValueFactory(row -> row.getValue().displayGebaeudeKosten.asObject());
		gebaeudeVorteileColumn.setCellValueFactory(row -> row.getValue().displayGebaeudeVorteile);
		gebaeudeTableData.clear();
		for (GebaeudeEnum gebaeude : GebaeudeEnum.values()) {
			gebaeudeTableData.add(new verfuegbareGebaeudeRow(gebaeude));
		}
		verfuegbareGebaeudeTable.setItems(gebaeudeTableData);
		// TODO: analog zu DefaultRightPanelVC static Klasse erzeugen. Da ValueFactory
		// StringProperty erwartet
	}

	public void initializeShowStadt(StadtBE stadt) {
		LOG.debug(stadt.getStadtname());
		stadtNameLabel.setText(stadt.getStadtname());
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
			displayGebaeudeKosten = new SimpleIntegerProperty(gebaeudeEnum.getKosten());
			displayGebaeudeVorteile = new SimpleStringProperty(gebaeudeEnum.getVorteile());
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

}
