import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class AppMenuBar extends MenuBar {
	private TrafficLightPane tlp;
	private Controller cp;
	private RadioMenuItem miRed, miYellow, miGreen;

	AppMenuBar(TrafficLightPane tlp) {
		this.tlp = tlp;
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
		miRed.setSelected(this.tlp.getTrafficLightState() == TrafficLightPane.RED);
		miYellow.setSelected(this.tlp.getTrafficLightState() == TrafficLightPane.YELLOW);
		miGreen.setSelected(this.tlp.getTrafficLightState() == TrafficLightPane.GREEN);
	}
	
	public void setController(Controller cp) {
		this.cp = cp;
	}
}
