/*
 * Antonio C. Silvestri
 * Traffic Light Simulator Application
 * Traffic Light Class
 * CSC-112 Intermediate Java
 * 3/23/2021
 */

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TrafficLightPane extends StackPane {
	public static final int RED = 0;
	public static final int YELLOW = 1;
	public static final int GREEN = 2;

	private static final int RADIUS = 30;

	private int state = RED;

	private Circle redCircle, yellowCircle, greenCircle;
	private Controller cp;

	public TrafficLightPane() {
		this.setPrefSize(400, 350);
		this.setStyle("-fx-background-color: lightgrey");

		var rect = new Rectangle();
		rect.setStroke(Color.BLACK);
		rect.setFill(Color.WHITE);
		rect.setWidth(80);
		rect.setHeight(220);

//		redCircle = new Circle(RADIUS);
//		redCircle.setCenterX(40);
//		redCircle.setCenterY(110 - 2 * RADIUS - 10);
//		redCircle.setStroke(Color.BLACK);
//
//		yellowCircle = new Circle(RADIUS);
//		yellowCircle.setCenterX(40);
//		yellowCircle.setCenterY(110);
//		yellowCircle.setStroke(Color.BLACK);
//
//		greenCircle = new Circle(RADIUS);
//		greenCircle.setCenterX(40);
//		greenCircle.setCenterY(110 + 2 * RADIUS + 10);
//		greenCircle.setStroke(Color.BLACK);

		class MyCircle extends Circle {
			MyCircle(double cx, double cy) {
				super(RADIUS);
				this.setCenterX(cx);
				this.setCenterY(cy);
				this.setStroke(Color.BLACK);
			}
		}
		
		redCircle = new MyCircle(40, 110 - 2 * RADIUS - 10);
		yellowCircle = new MyCircle(40, 110);
		greenCircle = new MyCircle(40, 110 + 2 * RADIUS + 10);
		
		redCircle.setOnMouseClicked(e -> {
			if (this.cp != null) {
				cp.setSystemRed();
			}
		});
		yellowCircle.setOnMouseClicked(e -> {
			if (this.cp != null) {
				cp.setSystemYellow();
			}
		});
		greenCircle.setOnMouseClicked(e -> {
			if (this.cp != null) {
				cp.setSystemGreen();
			}
		});	
		
		var group = new Group();
		group.getChildren().addAll(rect, redCircle, yellowCircle, greenCircle);
		
		this.getChildren().addAll(group);
	}

	public int getTrafficLightState() {
		return state;
	}
	
	public void setController(Controller cp) {
		this.cp = cp;
	}

	private void paint() {
		redCircle.setFill(this.state == RED ? Color.RED : Color.WHITE);
		yellowCircle.setFill(this.state == YELLOW ? Color.YELLOW : Color.WHITE);
		greenCircle.setFill(this.state == GREEN ? Color.GREEN : Color.WHITE);
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