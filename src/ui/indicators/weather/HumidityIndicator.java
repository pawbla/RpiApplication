package ui.indicators.weather;

import java.awt.Color;

import javax.swing.SwingConstants;

import ui.indicators.Indicator;

public class HumidityIndicator extends Indicator {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3740926325200073364L;
	/**
	 * Constants
	 */
	private final static int WIDTH = 80;
	private final static int HEIGHT = 50;
	private final static Color TEXT_COLOR = Color.BLUE;

	public HumidityIndicator() {
		super(WIDTH, HEIGHT, TEXT_COLOR);
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setHorizontalAlignment(SwingConstants.RIGHT);
	}
}
