package ui.indicators.airpolution;

public class PM1_Indicator extends AirPolutionIndicator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9175247538788329488L;
	
	private static PM1_Indicator instance = null;
	
	public PM1_Indicator() {
		super();
	}
	
	public static PM1_Indicator getInstance() {
		if (instance == null) {
			instance = new PM1_Indicator();
		}
		return instance;
	}
}
