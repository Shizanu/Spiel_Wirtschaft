package spiel_wirtschaft.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import spiel_wirtschaft.controller.NeuesSpielCtrl;
import spiel_wirtschaft.model.SpielBE;
import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.view.HauptmenueVC;
import spiel_wirtschaft.view.SpielkarteVC;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<StadtBE> staedte = FXCollections.observableArrayList();

	private SpielBE currentlyActiveSpiel;

	public Main() {
		super();
		staedte.add(new StadtBE("Berlin", 100L));
		staedte.add(new StadtBE("Hamburg", 80L));

		currentlyActiveSpiel = new NeuesSpielCtrl().neuesDummySpielStarten();
	}

	public ObservableList<StadtBE> getStaedte() {
		return staedte;
	}

	public void setStaedte(ObservableList<StadtBE> staedte) {
		this.staedte = staedte;
	}

	@Override
	public void start(Stage primaryStage) {
		System.out.println("Application Starting");
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Wirtschaft by Kuschelbären Spielfabrik");

		initRootLayout();

		showHauptmenue();

	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			// TODO TRO: Refactoring safe reference to resource
			loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void showHauptmenue() {
		try {
			FXMLLoader loader = new FXMLLoader();
			// TODO TRO: Refactoring safe reference to resource
			loader.setLocation(Main.class.getResource("../view/Hauptmenue.fxml"));
			Node hauptmenuePage = (Node) loader.load();
			rootLayout.setCenter(hauptmenuePage);

			HauptmenueVC controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void showSpiel() {
		showDefaultLeftPanel();
		showKarte();
		showDefaultRightPanel();
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	private void showDefaultLeftPanel() {
		try {
			FXMLLoader loader = new FXMLLoader();
			// TODO TRO: Refactoring safe reference to resource
			loader.setLocation(Main.class.getResource("../view/DefaultLeftPanel.fxml"));
			AnchorPane leftPanelPage = (AnchorPane) loader.load();
			rootLayout.setLeft(leftPanelPage);

			// TODO TRO: Controller
			// StadtOverviewVC stadtOverviewVC = loader.getController();
			// stadtOverviewVC.setMain(this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void showKarte() {
		try {
			FXMLLoader loader = new FXMLLoader();
			// TODO TRO: Refactoring safe reference to resource
			// TODO TRO: Sinnvolle Fehlermeldung wenn Resource nicht gefunden (aktuell
			// Absturz beim laden wegen "Location is not set"
			loader.setLocation(Main.class.getResource("../view/Spielkarte.fxml"));
			AnchorPane spielkartePage = (AnchorPane) loader.load();

			rootLayout.setCenter(spielkartePage);

			// Daten füllen
			SpielkarteVC controller = loader.getController();
			controller.setSpielkarte(currentlyActiveSpiel.getSpielkarte());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	private void showDefaultRightPanel() {
		try {
			FXMLLoader loader = new FXMLLoader();
			// TODO TRO: Refactoring safe reference to resource
			loader.setLocation(Main.class.getResource("../view/DefaultRightPanel.fxml"));
			AnchorPane rightPanelPage = (AnchorPane) loader.load();
			rootLayout.setRight(rightPanelPage);

			// TODO TRO: Controller
			// StadtOverviewVC stadtOverviewVC = loader.getController();
			// stadtOverviewVC.setMain(this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
