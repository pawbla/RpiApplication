package ui.panels.mainPanels;

import javax.swing.JLabel;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ui.indicators.weather.InHumidityIndicator;
import ui.indicators.weather.InTemperatureIndicator;
import ui.labels.TitleLabel;
import ui.labels.UnitLabel;
import ui.layouts.SidePanelLayout;

public class LeftPanel extends  MainPanels  {
	
	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constants
	 */
	private static final int WIDTH = 140;
	private static final int HEIGHT = 210;
	private static final String IN_LABEL_TITLE = "IN";
	private final static String COLOR_HEX = "#FFFF00"; 

	public LeftPanel() {
		super(WIDTH, HEIGHT);
		final JLabel title = new TitleLabel(IN_LABEL_TITLE);
		new SidePanelLayout(this)
			.addTitle(title)
			.addTemperatureIndicator(InTemperatureIndicator.getInstance())
			.addTemperatureUnit(new UnitLabel("\u00b0C", 20, 23, Color.decode(COLOR_HEX)))
			.addHumidityIndicator(InHumidityIndicator.getInstance())
			.addHumidityUnit(new UnitLabel("%", 10, 18, Color.BLUE));
	}
}
