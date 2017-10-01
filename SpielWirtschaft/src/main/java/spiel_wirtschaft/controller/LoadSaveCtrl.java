package spiel_wirtschaft.controller;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import spiel_wirtschaft.model.City;

public class LoadSaveCtrl extends AbstractController {

	public void save(City city) {
		try {
			File file = new File("../../../Spielstand/Spielstand1.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(City.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(city, file);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public City load() {
		try {
			File file = new File("../../../Spielstand/Spielstand1.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(City.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			City city = (City) jaxbUnmarshaller.unmarshal(file);
			return city;

		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
}
