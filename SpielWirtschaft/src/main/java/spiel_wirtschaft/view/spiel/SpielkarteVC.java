package spiel_wirtschaft.view.spiel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import spiel_wirtschaft.controller.SpielCtrl;
import spiel_wirtschaft.model.GelaendeTypEnum;
import spiel_wirtschaft.model.KartenfeldBE;
import spiel_wirtschaft.model.SpielBE;
import spiel_wirtschaft.model.SpielkarteBE;
import spiel_wirtschaft.model.StadtBE;
import spiel_wirtschaft.view.AbstractViewController;
import spiel_wirtschaft.view.PrimaryStageManager;
import spiel_wirtschaft.view.ViewFactory;

@Component
public class SpielkarteVC extends AbstractViewController {

	@FXML
	private AnchorPane paneAroundCanvas;

	@Autowired
	private SpielCtrl spielCtrl;

	@Autowired
	private PrimaryStageManager primaryStageManager;

	@Autowired
	private ViewFactory viewFactory;

	private ResizableCanvas canvas;

	private Image cityIcon;

	private double feldPixelSize;
	private double stadtIconSize;

	private Circle selectedStadtMarkerCircle;

	/**
	 * We need to keep our own list of all the nodes that need to be removed from the AnchorPane when redrawing, because
	 * we cannot blindly remove all children. That would remove our canvas among others.
	 */
	private List<Node> nodesToRemoveOnRedraw;

	@FXML
	private void initialize() {
		canvas = new ResizableCanvas();
		paneAroundCanvas.getChildren().add(canvas);
		nodesToRemoveOnRedraw = new ArrayList<>();

		// Redraw canvas when size changes.
		canvas.widthProperty().addListener(evt -> drawSpielkarte());
		canvas.heightProperty().addListener(evt -> drawSpielkarte());

		canvas.widthProperty().bind(paneAroundCanvas.widthProperty());
		canvas.heightProperty().bind(paneAroundCanvas.heightProperty());

		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onMapClicked(event));

		// Load cityIcon only once to avoid performance problems
		cityIcon = new Image(getClass().getResourceAsStream("stadt_icon.png"));

		drawSpielkarte();
	}

	private void onMapClicked(MouseEvent event) {
		LOG.debug("Empty space on map clicked. Clearing any selection.");
		if (selectedStadtMarkerCircle != null) {
			paneAroundCanvas.getChildren().remove(selectedStadtMarkerCircle);
			selectedStadtMarkerCircle = null;
		}

		primaryStageManager.setLeftPanelNode(viewFactory.loadView(DefaultLeftPanelVC.class));
		event.consume();
	}

	// TODO improve positioning code

	private void drawSpielkarte() {
		SpielBE currentlyActiveSpiel = spielCtrl.getCurrentlyActiveSpiel();
		SpielkarteBE spielkarte = currentlyActiveSpiel.getSpielkarte();

		double canvasHeight = canvas.getHeight();
		double canvasWidth = canvas.getWidth();
		LOG.debug("Drawing Karte on canvas with width: " + canvasWidth + " and height: " + canvasHeight);
		if (canvasWidth == 0 || canvasHeight == 0) {
			LOG.debug("Karte not drawn, because width or height is 0.");
			return;
		}

		double sizeOfSmallerCanvasAxis = Math.min(canvasWidth, canvasHeight);
		double sizeOfLargerMapAxis = Math.max(spielkarte.getxSize(), spielkarte.getySize());
		feldPixelSize = sizeOfSmallerCanvasAxis / sizeOfLargerMapAxis;

		GraphicsContext graphicsContext = clearCanvas(canvasHeight, canvasWidth);

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

		stadtIconSize = feldPixelSize / 4;
		List<StadtBE> staedte = spielkarte.getStaedte();
		for (StadtBE stadt : staedte) {
			LOG.debug("Drawing Stadt at " + stadt.getPosition());
			double stadtIconXPos = stadt.getPosition().getxAxisPosition() * feldPixelSize - stadtIconSize / 2.0;
			double stadtIconYPos = stadt.getPosition().getyAxisPosition() * feldPixelSize - stadtIconSize / 2.0;

			ImageView stadtImageView = new ImageView(cityIcon);
			stadtImageView.setFitWidth(stadtIconSize);
			stadtImageView.setFitHeight(stadtIconSize);
			stadtImageView.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> onStadtClicked(stadt, event));
			addToPane(stadtIconXPos, stadtIconYPos, stadtImageView);

			Label stadtnameLabel = new Label(stadt.getStadtname());
			stadtnameLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onStadtClicked(stadt, event));
			addToPane(stadtIconXPos, stadtIconYPos + stadtIconSize * 1.1, stadtnameLabel);
			// TODO Center label below stadt
		}

	}

	private GraphicsContext clearCanvas(double canvasHeight, double canvasWidth) {
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		graphicsContext.clearRect(0, 0, canvasWidth, canvasHeight);
		paneAroundCanvas.getChildren().removeAll(nodesToRemoveOnRedraw);
		nodesToRemoveOnRedraw.clear();
		return graphicsContext;
	}

	private void onStadtClicked(StadtBE stadt, MouseEvent event) {
		LOG.debug("Stadt clicked " + stadt.getStadtname());
		double radius = stadtIconSize * 0.6;
		selectedStadtMarkerCircle = new Circle(radius);
		selectedStadtMarkerCircle.setStrokeWidth(stadtIconSize / 20.0);
		selectedStadtMarkerCircle.setFill(Color.TRANSPARENT);
		selectedStadtMarkerCircle.setStroke(Color.BLACK);
		addToPane(stadt.getPosition().getxAxisPosition() * feldPixelSize - radius,
				stadt.getPosition().getyAxisPosition() * feldPixelSize - radius, selectedStadtMarkerCircle);

		StadtMenueLeftPanelVC stadtMenue = viewFactory.loadView(StadtMenueLeftPanelVC.class);
		primaryStageManager.setLeftPanelNode(stadtMenue);
		stadtMenue.initializeShowStadt(stadt);

		event.consume();
	}

	private void addToPane(double xPos, double yPos, Node node) {
		paneAroundCanvas.getChildren().add(node);
		nodesToRemoveOnRedraw.add(node);
		AnchorPane.setLeftAnchor(node, xPos);
		AnchorPane.setTopAnchor(node, yPos);
	}

	class ResizableCanvas extends Canvas {

		@Override
		public double minHeight(double width) {
			return 400;
		}

		@Override
		public double maxHeight(double width) {
			return Double.MAX_VALUE;
		}

		@Override
		public double prefHeight(double width) {
			return minHeight(width);
		}

		@Override
		public double minWidth(double height) {
			return 400;
		}

		@Override
		public double prefWidth(double height) {
			return minWidth(height);
		}

		@Override
		public double maxWidth(double height) {
			return Double.MAX_VALUE;
		}

		@Override
		public boolean isResizable() {
			return true;
		}

		@Override
		public void resize(double width, double height) {

		}
	}

}
