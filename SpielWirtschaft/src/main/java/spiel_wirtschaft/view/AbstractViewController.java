package spiel_wirtschaft.view;

import javafx.scene.Node;
import spiel_wirtschaft.controller.AbstractController;

public class AbstractViewController extends AbstractController {

	/**
	 * Root of UI-Tree associated with this view-controller.
	 */
	protected Node root;

	public AbstractViewController() {
		super();
	}

	public Node getRoot() {
		return root;
	}

	void setRoot(Node root) {
		this.root = root;
	}

}
