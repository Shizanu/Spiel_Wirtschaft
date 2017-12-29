package spiel_wirtschaft.view.spiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import spiel_wirtschaft.controller.SpielCtrl;
import spiel_wirtschaft.view.AbstractViewController;
import spiel_wirtschaft.view.PrimaryStageManager;

@Component
public class DefaultRightPanelVC extends AbstractViewController {

	@FXML
	private Label aktuelleRundeLabel;

	@FXML
	private Button rundeBeendenBtn;

	@Autowired
	private SpielCtrl spielCtrl;

	@Autowired
	private PrimaryStageManager primaryStageManager;

	@FXML
	private void initialize() {
		int aktuelleRunde = spielCtrl.getCurrentlyActiveSpiel().getRunde();
		aktuelleRundeLabel.setText("Aktuelle Runde: " + aktuelleRunde);
	}

	public void onRundeBeendenClicked() {
		spielCtrl.rundeBeenden();
		primaryStageManager.showSpiel(); // TODO TRO: Introduce proper UI data refresh mechanism
	}

}
