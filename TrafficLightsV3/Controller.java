/*
 * Antonio C. Silvestri
 * Traffic Light Simulator Application
 * Control Buttons Class
 * CSC-112 Intermediate Java
 * 3/23/2021
 */

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Controller extends HBox {
	private TrafficLightPane tlp;
	private AppMenuBar mb;
	private Button btnRed, btnYellow, btnGreen;
	
	public Controller(TrafficLightPane tlp, AppMenuBar mb) {
		this.tlp = tlp;
		this.mb = mb;
	
		this.setSpacing(25);
		this.setAlignment(Pos.CENTER);
		
		btnRed = new Button("Red");
		btnRed.setStyle("-fx-background-color: red; -fx-text-fill: black;");
		btnRed.setOnAction(e -> this.setSystemRed());
		
		btnYellow = new Button("Yellow");
		btnYellow.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
		btnYellow.setOnAction(e -> this.setSystemYellow());
		
		btnGreen = new Button("Green");
		btnGreen.setStyle("-fx-background-color: green; -fx-text-fill: black;");
		btnGreen.setOnAction(e -> this.setSystemGreen());
		
		this.getChildren().addAll(btnRed, btnYellow, btnGreen);
	}
	
	public void setSystemRed() {
		tlp.turnRed();
		this.setDisabledButtonStates();
		mb.setSelectedState();
	}
	
	public void setSystemYellow() {
		tlp.turnYellow();
		this.setDisabledButtonStates();
		mb.setSelectedState();
	}
	
	public void setSystemGreen() {
		tlp.turnGreen();
		this.setDisabledButtonStates();
		mb.setSelectedState();
	}

	public void setDisabledButtonStates() {
		btnRed.setDisable(this.tlp.getTrafficLightState() == TrafficLightPane.RED);
		btnYellow.setDisable(this.tlp.getTrafficLightState() == TrafficLightPane.YELLOW);
		btnGreen.setDisable(this.tlp.getTrafficLightState() == TrafficLightPane.GREEN);
	}

}
