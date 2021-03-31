/*
 * Antonio C. Silvestri
 * Traffic Light Simulator Application
 * CSC-112 Intermediate Java
 * 3/23/2021
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TrafficLightAppWithInnerClasses extends Application {
	private	TrafficLightPane tlp;
	private	Controller cp;
	private AppMenuBar mb;

	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(new AppGUI());
		primaryStage.setTitle("Traffic Light App With Inner Classes"); 
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show(); 
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	// ***************************
	
	private class AppGUI extends BorderPane {
		AppGUI() {
			final String PANESTYLE = "-fx-font-weight: bold; -fx-font-size: 14pt";
			this.setStyle(PANESTYLE);

			mb = new AppMenuBar();
			tlp = new TrafficLightPane();
			cp = new Controller();
			cp.setSystemRed();
			
			this.setTop(mb);
			this.setCenter(tlp);
			this.setBottom(cp);		
		}

	}

	// ***************************
	
	private class AppMenuBar extends MenuBar {
		RadioMenuItem miRed, miYellow, miGreen;
		
		AppMenuBar() {
			Menu mFile = new Menu("File");
			MenuItem mi1File = new MenuItem("Exit");
			mi1File.setOnAction(e -> System.exit(0));
			mFile.getItems().addAll(mi1File);
			this.getMenus().add(mFile);

			Menu mSet = new Menu("Set Light");
			
		    var tGroup = new ToggleGroup();
			miRed = new RadioMenuItem("Red");
			miRed.setToggleGroup(tGroup);

			miYellow = new RadioMenuItem("Yellow");
			miYellow.setToggleGroup(tGroup);
			
			miGreen = new RadioMenuItem("Green");
			miGreen.setToggleGroup(tGroup);

			miRed.setOnAction(e -> {
				cp.setSystemRed();
			});
			
			miYellow.setOnAction(e -> {
				cp.setSystemYellow();
			});
			
			miGreen.setOnAction(e -> {
				cp.setSystemGreen();
			});

			mSet.getItems().addAll(miRed, miYellow, miGreen);
			this.getMenus().add(mSet);
		}
		
		public void setSelectedState() {
			miRed.setSelected(tlp.getTrafficLightState() == TrafficLightPane.RED);
			miYellow.setSelected(tlp.getTrafficLightState() == TrafficLightPane.YELLOW);
			miGreen.setSelected(tlp.getTrafficLightState() == TrafficLightPane.GREEN);
		}
	}
	
	// ***************************
	
	private class Controller extends HBox {
		private Button btnRed, btnYellow, btnGreen;
		
		Controller() {
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
			btnRed.setDisable(tlp.getTrafficLightState() == TrafficLightPane.RED);
			btnYellow.setDisable(tlp.getTrafficLightState() == TrafficLightPane.YELLOW);
			btnGreen.setDisable(tlp.getTrafficLightState() == TrafficLightPane.GREEN);
		}
	}

	// ***************************
	
	private class TrafficLightPane extends StackPane {
		public static final int RED = 0;
		public static final int YELLOW = 1;
		public static final int GREEN = 2;

		private static final int RADIUS = 30;

		private int state = RED;

		private Circle redCircle, yellowCircle, greenCircle;

		TrafficLightPane() {
			this.setPrefSize(400, 350);
			this.setStyle("-fx-background-color: lightgrey");

			var rect = new Rectangle();
			rect.setStroke(Color.BLACK);
			rect.setFill(Color.WHITE);
			rect.setWidth(80);
			rect.setHeight(220);

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
				cp.setSystemRed();
			});
			yellowCircle.setOnMouseClicked(e -> {
				cp.setSystemYellow();
			});
			greenCircle.setOnMouseClicked(e -> {
				cp.setSystemGreen();
			});	
			
			var group = new Group();
			group.getChildren().addAll(rect, redCircle, yellowCircle, greenCircle);
			
			getChildren().addAll(group);
		}

		public int getTrafficLightState() {
			return state;
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
}
