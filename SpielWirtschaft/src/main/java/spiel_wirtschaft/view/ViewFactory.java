package spiel_wirtschaft.view;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import spiel_wirtschaft.SpielWirtschaftApplication;

@Component
public class ViewFactory {

	private ConfigurableApplicationContext context;

	/**
	 * Sets the SpringApplicationContext. Only to be used by Application-Class
	 * during initialization.
	 * 
	 * @param context
	 */
	public void setContext(ConfigurableApplicationContext context) {
		this.context = context;
	}

	public <T extends AbstractViewController> T loadView(Class<T> viewControllerClass) {
		FXMLLoader loader = new FXMLLoader();
		String controllerName = viewControllerClass.getName();
		if (!controllerName.endsWith("VC")) {
			throw new IllegalArgumentException("View Controller class not ending on 'VC'");
		}
		String applicationPackage = SpielWirtschaftApplication.class.getName().substring(0,
				SpielWirtschaftApplication.class.getName().lastIndexOf(".") + 1);
		String withoutApplicationPackage = StringUtils.remove(controllerName, applicationPackage);
		String viewFileName = withoutApplicationPackage.replace(".", "/").substring(0,
				withoutApplicationPackage.length() - 2) + ".fxml";
		URL resource = SpielWirtschaftApplication.class.getResource(viewFileName);
		if (resource == null) {
			throw new IllegalArgumentException("Attempting to load view " + viewFileName
					+ " but no matching fxml file for " + viewControllerClass + " found in same package.");
		}
		loader.setLocation(resource);
		loader.setControllerFactory(context::getBean);
		try {
			Node root = (Node) loader.load();
			T controller = loader.getController();
			if (controller == null) {
				throw new IllegalArgumentException(
						"No controller loaded, this is usually because the controller was not linked in .fxml file.");
			}
			controller.setRoot(root);
			return controller;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
