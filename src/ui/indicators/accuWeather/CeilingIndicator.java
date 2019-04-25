package ui.indicators.accuWeather;

public class CeilingIndicator extends SouthPanelIndicator {

	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = 3528054396327581585L;

	private static CeilingIndicator instance = null;
	
	private CeilingIndicator() {
		super();
	}
	
	public static CeilingIndicator getInstance() {
		if (instance == null) {
			instance = new CeilingIndicator();
		}
		return instance;
	}

}
