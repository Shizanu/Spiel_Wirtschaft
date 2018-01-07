package spiel_wirtschaft.view.spiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import spiel_wirtschaft.controller.SpielCtrl;
import spiel_wirtschaft.model.GelaendeTypEnum;
import spiel_wirtschaft.model.KartenfeldBE;
import spiel_wirtschaft.model.SpielkarteBE;
import spiel_wirtschaft.view.AbstractViewController;

@Component
public class SpielkarteVC extends AbstractViewController {

	@Autowired
	private SpielCtrl spielCtrl;

	@FXML
	private StackPane paneAroundCanvas;

	private ResizableCanvas canvas;

	@FXML
	private void initialize() {
		canvas = new ResizableCanvas();
		paneAroundCanvas.getChildren().add(canvas);

		// Redraw canvas when size changes.
		canvas.widthProperty().addListener(evt -> drawSpielkarte());
		canvas.heightProperty().addListener(evt -> drawSpielkarte());

		canvas.widthProperty().bind(paneAroundCanvas.widthProperty());
		canvas.heightProperty().bind(paneAroundCanvas.heightProperty());

		drawSpielkarte();
	}

	private void drawSpielkarte() {
		SpielkarteBE spielkarte = spielCtrl.getCurrentlyActiveSpiel().getSpielkarte();

		int canvasHeight = (int) canvas.getHeight();
		int canvasWidth = (int) canvas.getWidth();
		LOG.debug("Drawing Karte on canvas with width: " + canvasWidth + " and height: " + canvasHeight);
		if (canvasWidth == 0 || canvasHeight == 0) {
			LOG.debug("Karte not drawn, because width or height is 0.");
			return;
		}

		int sizeOfSmallerCanvasAxis = (int) Math.min(canvasWidth, canvasHeight);
		int sizeOfLargerMapAxis = Math.max(spielkarte.getxSize(), spielkarte.getySize());
		int feldPixelSize = sizeOfSmallerCanvasAxis / sizeOfLargerMapAxis;

		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		graphicsContext.clearRect(0, 0, canvasWidth, canvasHeight);

		for (int xPos = 0; xPos < spielkarte.getxSize(); xPos++) {
			for (int yPos = 0; yPos < spielkarte.getySize(); yPos++) {
				KartenfeldBE kartenfeld = spielkarte.getKartenfelder()[xPos][yPos];
				if (kartenfeld.getGelaendeTyp() == GelaendeTypEnum.LAND) {
					graphicsContext.setFill(Color.GREEN);
				} else {
					graphicsContext.setFill(Color.BLUE);
				}
				graphicsContext.fillRect(xPos * feldPixelSize, yPos * feldPixelSize, feldPixelSize, feldPixelSize);
			}
		}
	}

	class ResizableCanvas extends Canvas {

		public ResizableCanvas() {

		}

		@Override
		public boolean isResizable() {
			return true;
		}

		@Override
		public double prefWidth(double height) {
			return getWidth();
		}

		@Override
		public double prefHeight(double width) {
			return getHeight();
		}
	}

}
