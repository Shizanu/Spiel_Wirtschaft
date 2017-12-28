package spiel_wirtschaft.view;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import spiel_wirtschaft.SpielWirtschaftApplication;
import spiel_wirtschaft.controller.NeuesSpielCtrl;
import spiel_wirtschaft.model.KartenGenerierungsModus;
import spiel_wirtschaft.model.SpielBE;

@Component
public class RootLayoutManager {

	private ConfigurableApplicationContext context;

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Autowired
	private NeuesSpielCtrl neuesSpielCtrl;

	private SpielBE currentlyActiveSpiel;

	public void initUserInterface(Stage primaryStage, ConfigurableApplicationContext context) {
		this.primaryStage = primaryStage;
		this.context = context;
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
			// TODO TRO: Create fxml factory
			loader.setLocation(SpielWirtschaftApplication.class.getResource("view/RootLayout.fxml"));
			loader.setControllerFactory(context::getBean);
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
			loader.setLocation(SpielWirtschaftApplication.class.getResource("view/Hauptmenue.fxml"));
			loader.setControllerFactory(context::getBean);
			Node hauptmenuePage = (Node) loader.load();
			rootLayout.setCenter(hauptmenuePage);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void showNeuesSpielMenue() {
		try {
			FXMLLoader loader = new FXMLLoader();
			// TODO TRO: Refactoring safe reference to resource
			loader.setLocation(SpielWirtschaftApplication.class.getResource("view/NeuesSpielMenue.fxml"));
			loader.setControllerFactory(context::getBean);
			Node neuesSpielMenuePage = (Node) loader.load();
			rootLayout.setCenter(neuesSpielMenuePage);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void neuesSpielStarten(KartenGenerierungsModus kartentyp) {
		currentlyActiveSpiel = neuesSpielCtrl.neuesDummySpielStarten(kartentyp);
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
			loader.setLocation(SpielWirtschaftApplication.class.getResource("view/DefaultLeftPanel.fxml"));
			loader.setControllerFactory(context::getBean);
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
			loader.setLocation(SpielWirtschaftApplication.class.getResource("view/Spielkarte.fxml"));
			loader.setControllerFactory(context::getBean);
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
			loader.setLocation(SpielWirtschaftApplication.class.getResource("view/DefaultRightPanel.fxml"));
			loader.setControllerFactory(context::getBean);
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

}
