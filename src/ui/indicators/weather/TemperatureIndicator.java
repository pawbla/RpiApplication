package ui.indicators.weather;

import java.awt.Color;

import javax.swing.SwingConstants;

import ui.indicators.Indicator;

public class TemperatureIndicator extends Indicator {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -6799333512783893582L;
	
	/**
	 * Constants
	 */
	private final static int WIDTH = 80;
	private final static int HEIGHT = 78;
	private final static String COLOR_HEX = "#FFFF00";
	private final static Color TEXT_COLOR = Color.decode(COLOR_HEX);

	public TemperatureIndicator() {
		super(WIDTH, HEIGHT, TEXT_COLOR);
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setHorizontalAlignment(SwingConstants.RIGHT);
	}

}
