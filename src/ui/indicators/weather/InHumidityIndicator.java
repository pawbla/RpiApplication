package ui.indicators.weather;

public class InHumidityIndicator extends HumidityIndicator {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -980452341774738404L;
	private static  InHumidityIndicator instance = null;
	
	private InHumidityIndicator() {
		super();
	}
	
	public static InHumidityIndicator getInstance() {
		if (instance == null) {
			instance = new InHumidityIndicator();
		}
		return instance;
	}
}
