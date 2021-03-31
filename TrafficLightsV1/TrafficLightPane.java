/*
 * Antonio C. Silvestri
 * Traffic Light Simulator Application
 * Traffic Light Class
 * CSC-112 Intermediate Java
 * 4/6/2020
 */

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TrafficLightPane extends Pane {
	public static final int RED = 0;
	public static final int YELLOW = 1;
	public static final int GREEN = 2;
	
	private static final int RADIUS = 30;

	private int state = RED;
	
	public int getTrafficLightState() {
		return state;
	}
	
	public TrafficLightPane() {
		this.setPrefSize(300, 350);
		this.paint();
	}

	private void paint() {
		this.getChildren().clear();
		this.setStyle("-fx-background-color: lightgrey");

		Rectangle rect = new Rectangle();
		rect.setStroke(Color.BLACK);
		rect.setFill(Color.WHITE);
		rect.setX(110);
		rect.setY(40);
		rect.setHeight(220);
		rect.setWidth(80);
			
		Circle redCircle = new Circle(RADIUS);
		redCircle.setCenterX(150);
		redCircle.setCenterY(80);
		redCircle.setStroke(Color.BLACK);
		redCircle.setFill(this.state == RED ? Color.RED : Color.WHITE);
			
		Circle yellowCircle = new Circle(RADIUS);
		yellowCircle.setCenterX(150);
		yellowCircle.setCenterY(150);
		yellowCircle.setStroke(Color.BLACK);
		yellowCircle.setFill(this.state == YELLOW ? Color.YELLOW : Color.WHITE);
		
		Circle greenCircle = new Circle(RADIUS);	
		greenCircle.setCenterX(150);
		greenCircle.setCenterY(220);
		greenCircle.setStroke(Color.BLACK);
		greenCircle.setFill(this.state == GREEN ? Color.GREEN : Color.WHITE);

		getChildren().addAll(rect, redCircle, yellowCircle, greenCircle);
	}

	public void turnRed() {
		this.state = RED;
		paint();
	}

	public void turnYellow() {
		this.state = YELLOW;
		paint();
	}

	public void turnGreen() {
		this.state = GREEN;
		paint();
	}

	
}