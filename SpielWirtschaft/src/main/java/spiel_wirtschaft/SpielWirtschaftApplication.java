package spiel_wirtschaft;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;
import spiel_wirtschaft.view.PrimaryStageManager;
import spiel_wirtschaft.view.ViewFactory;

/**
 * Application launcher class. This class must be placed in the root-package of
 * the application for 2 reasons: <br/>
 * -SpringBoot searches for beans in all sub-packages.<br/>
 * -Matching of Views to View-Controller in {@link ViewFactory} is based on
 * this.
 * 
 * @author Tobias Rojahn
 *
 */
@SpringBootApplication
public class SpielWirtschaftApplication extends Application {

	private ConfigurableApplicationContext context;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(SpielWirtschaftApplication.class);
		context = builder.run(getParameters().getRaw().toArray(new String[0]));
	}

	@Override
	public void start(Stage primaryStage) {
		System.out.println("Application Starting");
		ViewFactory viewFactory = context.getBean(ViewFactory.class);
		viewFactory.setContext(context);
		PrimaryStageManager primaryStageManager = context.getBean(PrimaryStageManager.class);
		primaryStageManager.initUserInterface(primaryStage);
	}

}
