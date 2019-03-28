package ui.indicators.airpolution;

public class PM10Percent_Indicator extends AirPolutionPercentIndicator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9175247538788329488L;
	
	private static PM10Percent_Indicator instance = null;
	
	public PM10Percent_Indicator() {
		super();
	}
	
	public static PM10Percent_Indicator getInstance() {
		if (instance == null) {
			instance = new PM10Percent_Indicator();
		}
		return instance;
	}
}
