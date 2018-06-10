package spiel_wirtschaft.controller;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import spiel_wirtschaft.model.SpielBE;

@Component
public class LoadSaveCtrl extends AbstractController {

	private Stage loadSaveStage;

	public boolean gameExists() {
		return new File("../../../Spielstand/Spielstand1.xml").isFile();
	}

	public void save(SpielBE spiel) {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Spiel speichern");
			File file = fileChooser.showSaveDialog(loadSaveStage);
			if (file != null) {
				JAXBContext jaxbContext = JAXBContext.newInstance(SpielBE.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(spiel, file);
			}
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public SpielBE load() {
		try {
			// TODO: make filename editable
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Existierendes Spiel öffnen");
			fileChooser.setInitialDirectory(new File("../../../Spielstand"));
			File file = fileChooser.showOpenDialog(loadSaveStage);
			JAXBContext jaxbContext = JAXBContext.newInstance(SpielBE.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SpielBE spiel = (SpielBE) jaxbUnmarshaller.unmarshal(file);
			return spiel;

		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
}
