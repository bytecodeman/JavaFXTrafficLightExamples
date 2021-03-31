/*
 * Antonio C. Silvestri
 * Traffic Light Simulator Application
 * CSC-112 Intermediate Java
 * 3/23/2021
 */

import javafx.scene.layout.BorderPane;

public class AppGUI extends BorderPane {
	private TrafficLightPane tlp;
	private Controller cp;
	private AppMenuBar mb;

	public AppGUI() {
		final String PANESTYLE = "-fx-font-weight: bold; -fx-font-size: 14pt";
		this.setStyle(PANESTYLE);

		tlp = new TrafficLightPane();
		mb = new AppMenuBar(tlp);
		cp = new Controller(tlp, mb);
		mb.setController(cp);
		cp.setSystemRed();
		
		this.setTop(mb);
		this.setCenter(tlp);
		this.setBottom(cp);
	}

}
