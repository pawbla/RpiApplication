package ui.indicators.weather;

public class InTemperatureIndicator extends TemperatureIndicator {

	/**
	 *  Serial ID
	 */
	private static final long serialVersionUID = -4911951278485793569L;
	private static InTemperatureIndicator instance = null;
	
	private InTemperatureIndicator() {
		super();
	}
	
	public static InTemperatureIndicator getInstance() {
		if (instance == null) {
			instance = new InTemperatureIndicator();
		}
		return instance;
	}
}
