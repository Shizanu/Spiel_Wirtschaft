package spiel_wirtschaft.view;

import javafx.fxml.FXML;
import spiel_wirtschaft.main.Main;

public class HauptmenueVC extends AbstractViewController {

	private Main main;

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	private void initialize() {
		// Nothing to do
	}

	public void onNeuesSpielClicked() {
		main.showNeuesSpielMenue();
	}

	public void onSpielFortsetzenClicked() {
		// TODO TRO: implement
	}

	public void onSpielLadenClicked() {
		// TODO TRO: implement
	}

}
