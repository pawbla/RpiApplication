package ui.indicators.sun;

public class SunSetIndicator extends SunIndicator {
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -4611508594145492212L;
	private static SunSetIndicator instance = null;
	
	private SunSetIndicator() {
		super();
	}
	
	public static SunSetIndicator getInstance() {
		if (instance == null) {
			instance = new SunSetIndicator();
		}
		return instance;
	}
}
