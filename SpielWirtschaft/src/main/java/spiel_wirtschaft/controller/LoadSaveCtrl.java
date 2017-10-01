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
			File file = new File("C:\\Dateien\\Spiel_Wirtschaft\\Spielstand\\Spielstand1.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(City.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(city, file);
			jaxbMarshaller.marshal(city, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public City load() {

		try {

			File file = new File("C:\\\\Dateien\\\\Spiel_Wirtschaft\\\\Spielstand\\\\Spielstand1.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(City.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			City city = (City) jaxbUnmarshaller.unmarshal(file);
			System.out.println(city);
			return city;

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}
