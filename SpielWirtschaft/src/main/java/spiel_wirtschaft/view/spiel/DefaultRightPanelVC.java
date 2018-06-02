package spiel_wirtschaft.view.spiel;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import spiel_wirtschaft.controller.NationCtrl;
import spiel_wirtschaft.controller.SpielCtrl;
import spiel_wirtschaft.model.NationBE;
import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.util.FormatAsStringUtil;
import spiel_wirtschaft.view.AbstractViewController;
import spiel_wirtschaft.view.PrimaryStageManager;
import spiel_wirtschaft.view.ViewFactory;

@Component
public class DefaultRightPanelVC extends AbstractViewController {

	@FXML
	private Label aktuelleRundeLabel;

	@FXML
	private Button rundeBeendenBtn;

	@FXML
	private Label geldLabel;

	@FXML
	private TableView<StadtUebersichtRow> stadtUebersichtTable;

	@FXML
	private TableColumn<StadtUebersichtRow, String> stadtNameColumn;

	@FXML
	private TableColumn<StadtUebersichtRow, String> einwohnerzahlColumn;

	@Autowired
	private SpielCtrl spielCtrl;

	@Autowired
	private NationCtrl nationCtrl;

	@Autowired
	private PrimaryStageManager primaryStageManager;

	@Autowired
	private ViewFactory viewFactory;

	private ObservableList<StadtUebersichtRow> stadtUebersichtData = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		fillAktuelleRundeLabel();
		fillGeldLabel();

		fillStadtUebersichtTable();
	}

	private void fillAktuelleRundeLabel() {
		int aktuelleRunde = spielCtrl.getCurrentlyActiveSpiel().getRunde();
		aktuelleRundeLabel.setText("Aktuelle Runde: " + aktuelleRunde);
	}

	private void fillGeldLabel() {
		NationBE nationDesSpielers = nationCtrl.getNationDesSpielers();
		BigDecimal geld = nationDesSpielers.getGeld();
		String geldDisplayText = FormatAsStringUtil.formatWith2DecimalPlaces(geld);
		geldLabel.setText("Geld: " + geldDisplayText);
	}

	private void fillStadtUebersichtTable() {
		stadtNameColumn.setCellValueFactory(row -> row.getValue().displayName);
		einwohnerzahlColumn.setCellValueFactory(row -> row.getValue().displayEinwohnerzahl);

		stadtUebersichtData.clear();
		List<StadtBE> staedte = spielCtrl.getCurrentlyActiveSpiel().getSpielkarte().getStaedte();
		for (StadtBE stadt : staedte) {
			stadtUebersichtData.add(new StadtUebersichtRow(stadt));
		}
		stadtUebersichtTable.setItems(stadtUebersichtData);

		stadtUebersichtTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showStadtMenueLeftPanel(newValue));
	}

	private void showStadtMenueLeftPanel(StadtUebersichtRow stadtUebersichtRow) {
		if (stadtUebersichtRow != null && stadtUebersichtRow.stadtBE != null) {
			StadtMenueLeftPanelVC stadtMenue = viewFactory.loadView(StadtMenueLeftPanelVC.class);
			primaryStageManager.setLeftPanelNode(stadtMenue);
			stadtMenue.initializeShowStadt(stadtUebersichtRow.stadtBE);
		} else {
			primaryStageManager.setLeftPanelNode(viewFactory.loadView(DefaultLeftPanelVC.class));
		}
	}

	public void onRundeBeendenClicked() {
		spielCtrl.rundeBeenden();
		primaryStageManager.showSpiel(); // TODO TRO: Introduce proper UI data refresh mechanism
	}

	private static class StadtUebersichtRow {

		private StadtBE stadtBE;

		private final StringProperty displayName;

		private final StringProperty displayEinwohnerzahl;

		public StadtUebersichtRow(StadtBE stadtBE) {
			super();
			this.stadtBE = stadtBE;
			displayName = new SimpleStringProperty(stadtBE.getStadtname());
			displayEinwohnerzahl = new SimpleStringProperty(String.valueOf(stadtBE.getEinwohnerzahl()));
		}

		public final StringProperty displayNameProperty() {
			return this.displayName;
		}

		public final String getDisplayName() {
			return this.displayNameProperty().get();
		}

		public final void setDisplayName(final String displayName) {
			this.displayNameProperty().set(displayName);
		}

		public final StringProperty displayEinwohnerzahlProperty() {
			return this.displayEinwohnerzahl;
		}

		public final String getDisplayEinwohnerzahl() {
			return this.displayEinwohnerzahlProperty().get();
		}

		public final void setDisplayEinwohnerzahl(final String displayEinwohnerzahl) {
			this.displayEinwohnerzahlProperty().set(displayEinwohnerzahl);
		}

	}

}
