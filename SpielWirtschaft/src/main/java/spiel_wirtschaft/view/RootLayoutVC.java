package spiel_wirtschaft.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import spiel_wirtschaft.controller.LoadSaveCtrl;
import spiel_wirtschaft.controller.SpielCtrl;

@Component
public class RootLayoutVC extends AbstractViewController {

	private Stage popUpStage;
	private AnchorPane popUpLayout;

	@Autowired
	private SpielCtrl aktuellesSpiel;

	@Autowired
	private LoadSaveCtrl loadSaveCtrl;

	@Autowired
	private ViewFactory viewFactory;

	public void onSchließenClicked() {
		// TODO
		PopUpWarningVC popUpWarningVC = viewFactory.loadView(PopUpWarningVC.class);
		popUpLayout = (AnchorPane) popUpWarningVC.getRoot();

		Scene scene = new Scene(popUpLayout);
		popUpStage = new Stage();
		popUpStage.setScene(scene);
		popUpStage.setMinWidth(300);
		popUpStage.show();
		// TODO: Warnmeldung

	}

	// TODO: Option ausblenden, wenn noch kein Spiel gestartet
	public void onSpeichernClicked() {
		loadSaveCtrl.save(aktuellesSpiel.getCurrentlyActiveSpiel());
	}

	// TODO: Option ausblenden, wenn noch kein Spiel gestartet
	public void onSpeichernUndSchließenClicked() {
		// TODO Warnmeldung

		System.exit(0);
	}
}
