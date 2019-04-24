package ui.panels.mainPanels;

import java.awt.Color;

import javax.swing.JLabel;

import ui.indicators.weather.OutHumidityIndicator;
import ui.indicators.weather.OutTemperatureIndicator;
import ui.labels.TitleLabel;
import ui.labels.UnitLabel;
import ui.layouts.SidePanelLayout;

public class RightPanel extends  MainPanels  {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1013086328662098180L;
	/**
	 * Constants
	 */
	private static final int WIDTH = 140;
	private static final int HEIGHT = 165;
	private static final String OUT_LABEL_TITLE = "OUT";
	private final static String COLOR_HEX = "#FFFF00";

	public RightPanel() {
		super(WIDTH, HEIGHT);
		final JLabel title = new TitleLabel(OUT_LABEL_TITLE);
		new SidePanelLayout(this)
			.addTitle(title)
			.addTemperatureIndicator(OutTemperatureIndicator.getInstance())
			.addTemperatureUnit(new UnitLabel("\u00b0C", 20, 23, Color.decode(COLOR_HEX)))
			.addHumidityIndicator(OutHumidityIndicator.getInstance())
			.addHumidityUnit(new UnitLabel("%", 10, 18, Color.BLUE));
	}
}
