/*
 * Antonio C. Silvestri
 * Traffic Light Simulator Application
 * Traffic Light Class
 * CSC-112 Intermediate Java
 * 3/23/2021
 */

import javafx.beans.binding.DoubleBinding;
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

	private Circle redCircle, yellowCircle, greenCircle;
	private Controller cp;

	public TrafficLightPane() {
		this.setPrefSize(400, 350);
		this.setStyle("-fx-background-color: lightgrey");

		var centerXBinding = this.widthProperty().divide(2);
		var centerYBinding = this.heightProperty().divide(2);

		var rect = new Rectangle();
		rect.setStroke(Color.BLACK);
		rect.setFill(Color.WHITE);
		rect.setWidth(80);
		rect.setHeight(220);
		rect.layoutXProperty().bind(centerXBinding.subtract(40));
		rect.layoutYProperty().bind(centerYBinding.subtract(110));

//		redCircle = new Circle(RADIUS);
//		redCircle.setCenterX(centerXProp.get());
//		redCircle.setCenterY(centerYProp.get() - 2 * RADIUS - 10);
//		redCircle.setStroke(Color.BLACK);
//
//		yellowCircle = new Circle(RADIUS);
//		yellowCircle.setCenterX(centerXProp.get());
//		yellowCircle.setCenterY(centerYProp.get());
//		yellowCircle.setStroke(Color.BLACK);
//
//		greenCircle = new Circle(RADIUS);
//		greenCircle.setCenterX(centerXProp.get());
//		greenCircle.setCenterY(centerYProp.get() + 2 * RADIUS + 10);
//		greenCircle.setStroke(Color.BLACK);

		// Local Class Definition
		class MyCircle extends Circle {
			MyCircle(DoubleBinding cx, DoubleBinding cy) {
				super(RADIUS);
				this.centerXProperty().bind(cx);
				this.centerYProperty().bind(cy);
				this.setStroke(Color.BLACK);
			}
		}
		
		redCircle = new MyCircle(centerXBinding, centerYBinding.subtract(2 * RADIUS + 10));
		yellowCircle = new MyCircle(centerXBinding, centerYBinding);
		greenCircle = new MyCircle(centerXBinding, centerYBinding.add(2 * RADIUS + 10));
		
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
		getChildren().addAll(rect, redCircle, yellowCircle, greenCircle);
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