package ui.indicators.accuWeather;

import java.awt.Color;

import javax.swing.SwingConstants;

import ui.indicators.Indicator;

public class WindSpeedIndicator extends Indicator {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7296902699938931151L;
	
	/**
	 * Constants
	 */
	private final static int WIDTH = 20;
	private final static int HEIGHT = 25;
	private final static Color COLOR = Color.YELLOW;
	
	private static WindSpeedIndicator instance = null;
	
	private WindSpeedIndicator() {
		super(WIDTH, HEIGHT, COLOR);
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public static WindSpeedIndicator getInstance() {
		if (instance == null) {
			instance = new WindSpeedIndicator();
		}
		return instance;
	}
}
