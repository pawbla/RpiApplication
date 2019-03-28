package ui.indicators.airpolution;

public class PM25_Indicator extends AirPolutionIndicator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9175247538788329488L;
	
	private static PM25_Indicator instance = null;
	
	public PM25_Indicator() {
		super();
	}
	
	public static PM25_Indicator getInstance() {
		if (instance == null) {
			instance = new PM25_Indicator();
		}
		return instance;
	}
}
