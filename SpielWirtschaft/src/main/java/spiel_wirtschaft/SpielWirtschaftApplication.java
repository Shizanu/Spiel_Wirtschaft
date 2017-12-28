package spiel_wirtschaft;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;
import spiel_wirtschaft.view.RootLayoutManager;

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
		RootLayoutManager rootLayoutManager = context.getBean(RootLayoutManager.class);
		rootLayoutManager.initUserInterface(primaryStage, context);
	}

}
