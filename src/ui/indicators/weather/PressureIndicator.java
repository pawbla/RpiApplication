package ui.indicators.weather;

import java.awt.Color;

import javax.swing.SwingConstants;

import ui.indicators.Indicator;

public class PressureIndicator extends Indicator {
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -3885284763507339313L;
	/**
	 * Constants
	 */
	private final static int WIDTH = 80;
	private final static int HEIGHT = 50;
	private final static String COLOR_HEX = "#FFFF00";
	private final static Color TEXT_COLOR = Color.decode(COLOR_HEX);
	
	private static PressureIndicator instance = null;

	private PressureIndicator() {
		super(WIDTH, HEIGHT, TEXT_COLOR);
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setHorizontalAlignment(SwingConstants.RIGHT);
	}
	
	public static PressureIndicator getInstance() {
		if (instance == null) {
			instance = new PressureIndicator();
		}
		return instance;
	}

}
