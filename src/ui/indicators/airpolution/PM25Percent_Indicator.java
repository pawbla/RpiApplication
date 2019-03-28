package ui.indicators.airpolution;

public class PM25Percent_Indicator extends AirPolutionPercentIndicator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9175247538788329488L;
	
	private static PM25Percent_Indicator instance = null;
	
	public PM25Percent_Indicator() {
		super();
	}
	
	public static PM25Percent_Indicator getInstance() {
		if (instance == null) {
			instance = new PM25Percent_Indicator();
		}
		return instance;
	}
}
