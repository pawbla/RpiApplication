package ui.indicators.weather;

public class OutHumidityIndicator extends HumidityIndicator {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7652518034714390732L;
	
	private static OutHumidityIndicator instance = null;
	
	private OutHumidityIndicator() {
		super();
	}
	
	public static OutHumidityIndicator getInstance() {
		if (instance == null) {
			instance = new OutHumidityIndicator();
		}
		return instance;
	}
}
