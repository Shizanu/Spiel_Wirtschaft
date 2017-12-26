package spiel_wirtschaft.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import spiel_wirtschaft.controller.NeuesSpielCtrl;
import spiel_wirtschaft.model.KartenGenerierungsModus;
import spiel_wirtschaft.model.SpielBE;
import spiel_wirtschaft.view.HauptmenueVC;
import spiel_wirtschaft.view.NeuesSpielMenueVC;
import spiel_wirtschaft.view.SpielkarteVC;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private SpielBE currentlyActiveSpiel;

	public Main() {
		super();

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

	public void showNeuesSpielMenue() {
		try {
			FXMLLoader loader = new FXMLLoader();
			// TODO TRO: Refactoring safe reference to resource
			loader.setLocation(Main.class.getResource("../view/NeuesSpielMenue.fxml"));
			Node neuesSpielMenuePage = (Node) loader.load();
			rootLayout.setCenter(neuesSpielMenuePage);

			NeuesSpielMenueVC controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void neuesSpielStarten(KartenGenerierungsModus kartentyp) {
		currentlyActiveSpiel = new NeuesSpielCtrl().neuesDummySpielStarten(kartentyp);
		showSpiel();
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
