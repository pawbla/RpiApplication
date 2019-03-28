package ui.indicators.airpolution;

import java.awt.Color;

import javax.swing.SwingConstants;

import ui.indicators.Indicator;

public class CAQiIndicator extends Indicator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6259247692185229049L;
	
	private static CAQiIndicator instance = null;
	/**
	 * Constants
	 */
	private final static int WIDTH = 80;
	private final static int HEIGHT = 45;
	private final static String COLOR_HEX = "#FFFF00";
	private final static Color TEXT_COLOR = Color.decode(COLOR_HEX);
	
	public CAQiIndicator() {
		super(WIDTH, HEIGHT, TEXT_COLOR);
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public static CAQiIndicator getInstance() {
		if (instance == null) {
			instance = new CAQiIndicator();
		}
		return instance;
	}
}
