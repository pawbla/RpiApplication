package ui.indicators.accuWeather;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

import ui.indicators.Indicator;

public class WeatherTextIndicator extends Indicator {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -5330907585115983509L;
	
	/**
	 * Constants
	 */
	private final static int WIDTH = 20;
	private final static int HEIGHT = 18;
	private final static Color COLOR = Color.YELLOW;
	
	private static WeatherTextIndicator instance = null;

	private WeatherTextIndicator() {
		super(WIDTH, HEIGHT, COLOR);
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font(Font.DIALOG, Font.TRUETYPE_FONT, HEIGHT - 3));
	}
	
	public static WeatherTextIndicator getInstance() {
		if (instance == null) {
			instance = new WeatherTextIndicator();
		}
		return instance;
	}
}
