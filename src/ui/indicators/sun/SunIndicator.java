package ui.indicators.sun;

import java.awt.Color;

import javax.swing.SwingConstants;

import ui.indicators.Indicator;

public class SunIndicator extends Indicator {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7561480598027712439L;
	/**
	 * Constants
	 */
	private final static int WIDTH = 20;
	private final static int HEIGHT = 30;
	private final static String COLOR_HEX = "#FFFF00";
	private final static Color TEXT_COLOR = Color.decode(COLOR_HEX);
	
	public SunIndicator() {
		super(WIDTH, HEIGHT, TEXT_COLOR);
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setHorizontalAlignment(SwingConstants.RIGHT);
	}
}
