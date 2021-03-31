/*
 * Antonio C. Silvestri
 * Traffic Light Simulator Application
 * CSC-112 Intermediate Java
 * 3/23/2021
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrafficLightApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(new AppGUI());
		primaryStage.setTitle("Traffic Light App With External Classes V2.0"); 
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show(); 
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
