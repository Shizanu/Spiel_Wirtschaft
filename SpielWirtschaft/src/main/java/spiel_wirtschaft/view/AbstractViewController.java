package spiel_wirtschaft.view;

import javafx.scene.Node;

public class AbstractViewController {

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
