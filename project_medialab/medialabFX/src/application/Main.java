package application;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start (Stage primaryStage) throws Exception {
		//start on WelcomePage
		new WelcomePage(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}