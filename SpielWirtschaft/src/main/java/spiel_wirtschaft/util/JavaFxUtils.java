package spiel_wirtschaft.util;

import javafx.beans.binding.Bindings;
import javafx.scene.control.TableView;

/**
 * Util class for anything related to managing JavaFx UI elements
 *
 */
public class JavaFxUtils {

	private static final int DEFAULT_TEXT_COLUMN_HEIGHT = 25;

	private JavaFxUtils() {
		super();
		// util class not instantiable!
	}

	public static void bindTableHeightToColumns(TableView<?> tableView) {
		tableView.setFixedCellSize(DEFAULT_TEXT_COLUMN_HEIGHT);
		tableView.prefHeightProperty()
				.bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(1.01)));
	}

}
