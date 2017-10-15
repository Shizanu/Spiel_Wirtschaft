package spiel_wirtschaft.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.view.StadtEditDialogVC;
import spiel_wirtschaft.view.StadtOverviewVC;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<StadtBE> staedte = FXCollections.observableArrayList();

	public Main() {
		super();
		staedte.add(new StadtBE("Berlin", 100L));
		staedte.add(new StadtBE("Hamburg", 80L));
	}

	public ObservableList<StadtBE> getStaedte() {
		return staedte;
	}

	@Override
	public void start(Stage primaryStage) {
		System.out.println("Application Starting");
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Wirtschaft by Kuschelbären Spielfabrik");

		initRootLayout();

		showStadtOverview();
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
			primaryStage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showStadtOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			// TODO TRO: Refactoring safe reference to resource
			loader.setLocation(Main.class.getResource("../view/StadtOverview.fxml"));
			AnchorPane stadtOverview = (AnchorPane) loader.load();
			rootLayout.setCenter(stadtOverview);

			StadtOverviewVC stadtOverviewVC = loader.getController();
			stadtOverviewVC.setMain(this);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean showStadtEditDialog(StadtBE stadtToEdit) {
		try {
			FXMLLoader loader = new FXMLLoader();
			// TODO TRO: Refactoring safe reference to resource
			// TODO TRO: Sinnvolle Fehlermeldung wenn Resource nicht gefunden (aktuell
			// Absturz beim laden wegen "Location is not set"
			loader.setLocation(Main.class.getResource("../view/StadtEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Stadt bearbeiten");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			StadtEditDialogVC controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setStadtToEdit(stadtToEdit);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isSpeichernClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
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
