package spiel_wirtschaft.view.spiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import spiel_wirtschaft.controller.SpielCtrl;
import spiel_wirtschaft.model.GelaendeTypEnum;
import spiel_wirtschaft.model.KartenfeldBE;
import spiel_wirtschaft.model.SpielkarteBE;
import spiel_wirtschaft.view.AbstractViewController;

@Component
public class SpielkarteVC extends AbstractViewController {

	private static final int FELD_PIXEL_GROESSE = 50;

	@Autowired
	private SpielCtrl spielCtrl;

	@FXML
	private Canvas canvas;

	@FXML
	private void initialize() {
		SpielkarteBE spielkarte = spielCtrl.getCurrentlyActiveSpiel().getSpielkarte();
		drawSpielkarte(spielkarte);
	}

	private void drawSpielkarte(SpielkarteBE spielkarte) {
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

		for (int xPos = 0; xPos < spielkarte.getxSize(); xPos++) {
			for (int yPos = 0; yPos < spielkarte.getySize(); yPos++) {
				KartenfeldBE kartenfeld = spielkarte.getKartenfelder()[xPos][yPos];
				if (kartenfeld.getGelaendeTyp() == GelaendeTypEnum.LAND) {
					graphicsContext.setFill(Color.GREEN);
				} else {
					graphicsContext.setFill(Color.BLUE);
				}
				graphicsContext.fillRect(xPos * FELD_PIXEL_GROESSE, yPos * FELD_PIXEL_GROESSE, FELD_PIXEL_GROESSE,
						FELD_PIXEL_GROESSE);
			}
		}
	}

}
