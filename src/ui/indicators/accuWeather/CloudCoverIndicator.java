package ui.indicators.accuWeather;

public class CloudCoverIndicator extends SouthPanelIndicator {

	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = 3528054396327581585L;

	private static CloudCoverIndicator instance = null;
	
	private CloudCoverIndicator() {
		super();
	}
	
	public static CloudCoverIndicator getInstance() {
		if (instance == null) {
			instance = new CloudCoverIndicator();
		}
		return instance;
	}

}
