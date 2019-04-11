package ui.indicators.sun;

import javax.swing.SwingConstants;

public class DayLengthIndicator extends SunIndicator {
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -8355352341893596519L;
	private static DayLengthIndicator instance = null;
	
	private DayLengthIndicator() {
		super();
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public static DayLengthIndicator getInstance() {
		if (instance == null) {
			instance = new DayLengthIndicator();
		}
		return instance;
	}
}
