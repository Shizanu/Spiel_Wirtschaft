package spiel_wirtschaft.view;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spiel_wirtschaft.model.StadtBE;

public class StadtEditDialogVC {

	@FXML
	private TextField stadtnameField;

	@FXML
	private TextField einwohnerzahlField;

	private Stage dialogStage;
	private StadtBE stadtToEdit;

	private boolean speichernClicked = false;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setStadtToEdit(StadtBE stadtToEdit) {
		this.stadtToEdit = stadtToEdit;

		stadtnameField.setText(stadtToEdit.getStadtname());
		einwohnerzahlField.setText(String.valueOf(stadtToEdit.getEinwohnerzahl()));
	}

	public boolean isSpeichernClicked() {
		return speichernClicked;
	}

	@FXML
	private void handleSpeichern() {
		if (isInputValid()) {
			stadtToEdit.setStadtname(stadtnameField.getText());
			stadtToEdit.setEinwohnerzahl(Long.parseLong(einwohnerzahlField.getText()));

			speichernClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	// TODO TRO: Introduce proper validation architecture. Validation should happen
	// on entities in backend.
	private boolean isInputValid() {
		String errorMessage = "";
		if (StringUtils.isBlank(stadtnameField.getText())) {
			errorMessage += "Stadtname muss gefüllt sein.\n";
		}
		if (!NumberUtils.isCreatable(einwohnerzahlField.getText())
				|| NumberUtils.createLong(einwohnerzahlField.getText()) <= 0) {
			errorMessage += "Einwohnerzahl muss eine Zahl größer 0 sein.\n";
		}
		if (StringUtils.isBlank(errorMessage)) {
			return true;
		} else {
			// TODO TRO: Refactor display of alerts into superclass for VCs that knows its
			// stage.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Ungültige Werte");
			alert.setHeaderText("Bitte korrigieren Sie die Eingaben.");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

}
