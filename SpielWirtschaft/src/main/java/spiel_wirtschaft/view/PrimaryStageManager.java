package spiel_wirtschaft.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Component
public class PrimaryStageManager {

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Autowired
	private ViewFactory viewFactory;

	public void initUserInterface(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Wirtschaft by Kuschelbären Spielfabrik");

		initRootLayout();

		showHauptmenue();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		RootLayoutVC rootLayoutVC = viewFactory.loadView(RootLayoutVC.class);
		rootLayout = (BorderPane) rootLayoutVC.getRoot();

		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	public void showHauptmenue() {
		HauptmenueVC hauptmenueVC = viewFactory.loadView(HauptmenueVC.class);
		Node hauptmenuePage = hauptmenueVC.getRoot();
		rootLayout.setCenter(hauptmenuePage);
	}

	public void showNeuesSpielMenue() {
		Node neuesSpielMenuePage = viewFactory.loadView(NeuesSpielMenueVC.class).getRoot();
		rootLayout.setCenter(neuesSpielMenuePage);
	}

	public void showSpiel() {
		showDefaultLeftPanel();
		showKarte();
		showDefaultRightPanel();
	}

	private void showDefaultLeftPanel() {
		Node leftPanelPage = viewFactory.loadView(DefaultLeftPanelVC.class).getRoot();
		rootLayout.setLeft(leftPanelPage);

	}

	private void showKarte() {
		SpielkarteVC controller = viewFactory.loadView(SpielkarteVC.class);
		Node spielkartePage = controller.getRoot();
		rootLayout.setCenter(spielkartePage);
	}

	private void showDefaultRightPanel() {
		Node rightPanelPage = viewFactory.loadView(DefaultRightPanelVC.class).getRoot();
		rootLayout.setRight(rightPanelPage);
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
