package ui.indicators.accuWeather;

import java.awt.Color;

import javax.swing.SwingConstants;

public class UVIndexIndicator extends SouthPanelIndicator {

	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = 3528054396327581585L;
	
	private static final String VIOLET_HEX = "#B803FF";

	private static UVIndexIndicator instance = null;
	
	private UVIndexIndicator() {
		super();
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public static UVIndexIndicator getInstance() {
		if (instance == null) {
			instance = new UVIndexIndicator();
		}
		return instance;
	}
	
	
	public void setText(int value) {
		this.setText(Integer.toString(value));
		this.setForeground(value);
	}

	
	private void setForeground(int value) {
		Color color = Color.GREEN;
		if (value >= 3 && value <= 5) {
			color = Color.YELLOW;
		} else if (value >= 6 && value <= 7 ) {
			color = Color.ORANGE;
		} else if (value >= 8 && value <= 10 ) {
			color = Color.RED;
		} else if (value >= 11 ) {
			color = Color.decode(VIOLET_HEX);
		}
		this.setForeground(color);
	}
}
