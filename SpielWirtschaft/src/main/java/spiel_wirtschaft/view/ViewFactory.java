package spiel_wirtschaft.view;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import spiel_wirtschaft.SpielWirtschaftApplication;

@Component
public class ViewFactory {

	private ConfigurableApplicationContext context;

	public <T extends AbstractViewController> T loadView(Class<T> viewControllerClass) {
		FXMLLoader loader = new FXMLLoader();
		// TODO TRO: Refactoring safe reference to resource
		String viewFilePath = "view/Hauptmenue.fxml";
		loader.setLocation(SpielWirtschaftApplication.class.getResource(viewFilePath));
		loader.setControllerFactory(context::getBean);
		try {
			Node hauptmenuePage = (Node) loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return null;
		// TODO TRO: continue
	}

}
