package ui.indicators.airpolution;

public class PM10_Indicator extends AirPolutionIndicator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9175247538788329488L;
	
	private static PM10_Indicator instance = null;
	
	public PM10_Indicator() {
		super();
	}
	
	public static PM10_Indicator getInstance() {
		if (instance == null) {
			instance = new PM10_Indicator();
		}
		return instance;
	}
}
