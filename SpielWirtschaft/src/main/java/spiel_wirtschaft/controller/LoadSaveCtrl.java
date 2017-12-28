package spiel_wirtschaft.controller;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import spiel_wirtschaft.model.StadtBE;

@Component
public class LoadSaveCtrl extends AbstractController {

	public void save(StadtBE city) {
		try {
			File file = new File("../../../Spielstand/Spielstand1.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(StadtBE.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(city, file);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public StadtBE load() {
		try {
			File file = new File("../../../Spielstand/Spielstand1.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(StadtBE.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StadtBE city = (StadtBE) jaxbUnmarshaller.unmarshal(file);
			return city;

		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
}
