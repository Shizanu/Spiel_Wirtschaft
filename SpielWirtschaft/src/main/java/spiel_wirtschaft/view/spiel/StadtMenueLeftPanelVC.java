package spiel_wirtschaft.view.spiel;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.view.AbstractViewController;

@Component
public class StadtMenueLeftPanelVC extends AbstractViewController {

	@FXML
	private String stadtName;

	@FXML
	private void initialize() {
	}

	@FXML
	public void initializeShowStadt(StadtBE stadt) {
		stadtName = stadt.getStadtname();
	}
}
