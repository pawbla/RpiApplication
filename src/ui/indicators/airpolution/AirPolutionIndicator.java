package ui.indicators.airpolution;

import java.awt.Color;

import javax.swing.SwingConstants;

import ui.indicators.Indicator;

public class AirPolutionIndicator extends Indicator {

	/**
	 * Generated Serial UID
	 */
	private static final long serialVersionUID = 8782147382773222530L;
	/**
	 * Constants
	 */
	private final static int WIDTH = 20;
	private final static int HEIGHT = 32;
	private final static String COLOR_HEX = "#FFFF00";
	private final static Color TEXT_COLOR = Color.decode(COLOR_HEX);
	
	public AirPolutionIndicator() {
		super(WIDTH, HEIGHT, TEXT_COLOR);
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
}