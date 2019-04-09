package ui.indicators.airpolution;

import java.awt.Color;

import javax.swing.SwingConstants;

import ui.indicators.Indicator;

public class AirPolutionPercentIndicator extends Indicator {

	/**
	 * Generated Serial UID
	 */
	private static final long serialVersionUID = 8782147382773222530L;
	/**
	 * Constants
	 */
	private final static int WIDTH = 30;
	private final static int HEIGHT = 30;
	private final static String COLOR_HEX = "#FFFF00";
	private final static Color TEXT_COLOR = Color.decode(COLOR_HEX);
	
	public AirPolutionPercentIndicator() {
		super(WIDTH, HEIGHT, TEXT_COLOR);
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
}