package spiel_wirtschaft.view;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import spiel_wirtschaft.model.GelaendeTypEnum;
import spiel_wirtschaft.model.KartenfeldBE;
import spiel_wirtschaft.model.SpielkarteBE;

@Component
public class SpielkarteVC extends AbstractViewController {

	private static final int FELD_PIXEL_GROESSE = 50;

	@FXML
	private Canvas canvas;

	private SpielkarteBE spielkarte;

	@FXML
	private void initialize() {
		// TODO TRO: Karte kann hier noch nicht gezeichnet werden, weil die Variable
		// noch nicht gesetzt, was dazu führt, dass die logik am Setter hängt. Dafür
		// brauchen wir noch eine bessere Lösung.
	}

	private void drawSpielkarte() {
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

	public SpielkarteBE getSpielkarte() {
		return spielkarte;
	}

	public void setSpielkarte(SpielkarteBE spielkarte) {
		this.spielkarte = spielkarte;
		drawSpielkarte();
	}

}
