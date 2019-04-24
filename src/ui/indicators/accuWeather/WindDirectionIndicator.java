package ui.indicators.accuWeather;

import java.awt.Color;

import javax.swing.SwingConstants;

import ui.indicators.Indicator;

public class WindDirectionIndicator extends Indicator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1984773646222746678L;
	/**
	 * Constants
	 */
	private final static int WIDTH = 20;
	private final static int HEIGHT = 23;
	private final static Color COLOR = Color.YELLOW;
	
	private static WindDirectionIndicator instance = null;
	
	private WindDirectionIndicator() {
		super(WIDTH, HEIGHT, COLOR);
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public static WindDirectionIndicator getInstance() {
		if (instance == null) {
			instance = new WindDirectionIndicator();
		}
		return instance;
	}
}
