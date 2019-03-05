package ui.indicators.weather;

public class OutTemperatureIndicator extends TemperatureIndicator {
	
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 479825265568238585L;
	private static OutTemperatureIndicator instance = null;
	
	private OutTemperatureIndicator() {
		super();
	}
	
	public static OutTemperatureIndicator getInstance() {
		if (instance == null) {
			instance = new OutTemperatureIndicator();
		}
		return instance;
	}
}
