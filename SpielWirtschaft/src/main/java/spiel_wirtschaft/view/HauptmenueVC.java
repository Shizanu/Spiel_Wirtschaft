package spiel_wirtschaft.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;

@Component
public class HauptmenueVC extends AbstractViewController {

	@Autowired
	private PrimaryStageManager rootLayoutManager;

	@FXML
	private void initialize() {
		// Nothing to do
	}

	public void onNeuesSpielClicked() {
		rootLayoutManager.showNeuesSpielMenue();
	}

	public void onSpielFortsetzenClicked() {
		// TODO TRO: implement
	}

	public void onSpielLadenClicked() {
		// TODO TRO: implement
	}

}
