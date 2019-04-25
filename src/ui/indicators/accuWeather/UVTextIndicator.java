package ui.indicators.accuWeather;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

import ui.indicators.Indicator;

public class UVTextIndicator extends Indicator {

	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = 3528054396327581585L;
	
	/**
	 * Constants
	 */
	private final static int WIDTH = 25;
	private final static int HEIGHT = 25;
	private final static Color COLOR = Color.YELLOW;

	private static UVTextIndicator instance = null;
	
	private UVTextIndicator() {
		super(WIDTH, HEIGHT, COLOR);
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setHorizontalAlignment(SwingConstants.RIGHT);
		this.setFont(new Font(Font.DIALOG, Font.TRUETYPE_FONT, HEIGHT - 5));
	}
	
	public static UVTextIndicator getInstance() {
		if (instance == null) {
			instance = new UVTextIndicator();
		}
		return instance;
	}
}
