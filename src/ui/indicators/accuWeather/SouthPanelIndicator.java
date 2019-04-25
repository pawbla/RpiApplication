package ui.indicators.accuWeather;

import java.awt.Color;

import javax.swing.SwingConstants;

import ui.indicators.Indicator;

public class SouthPanelIndicator extends Indicator {
	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = 3528054396327581585L;
	
	/**
	 * Constants
	 */
	private final static int WIDTH = 18;
	private final static int HEIGHT = 30;
	private final static Color TEXT_COLOR = Color.YELLOW;

	public SouthPanelIndicator() {
		super(WIDTH, HEIGHT, TEXT_COLOR);
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setHorizontalAlignment(SwingConstants.RIGHT);
	}
}
