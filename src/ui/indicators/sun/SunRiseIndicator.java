package ui.indicators.sun;

public class SunRiseIndicator extends SunIndicator {
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -8355352341893596519L;
	private static SunRiseIndicator instance = null;
	
	private SunRiseIndicator() {
		super();
	}
	
	public static SunRiseIndicator getInstance() {
		if (instance == null) {
			instance = new SunRiseIndicator();
		}
		return instance;
	}
}
