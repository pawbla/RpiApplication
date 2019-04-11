package sensors.objects;

public class SunRiseSetSensor implements Sensor {
	
	/**
	 * Variables
	 */
	private String sunRiseTime;
	private String sunSetTime;
	private String dayLengthTime;
	private int statusCode;
	private String date;
	
	/**
	 * POJO class represents Sun rise and sun set information
	 */
	public SunRiseSetSensor() {
		sunRiseTime = "-";
		sunSetTime = "-";
		dayLengthTime = "-";
		statusCode = 0;
		date = "-";
	}

	public String getSunRiseTime() {
		return sunRiseTime;
	}

	public void setSunRiseTime(String sunRiseTime) {
		this.sunRiseTime = sunRiseTime;
	}

	public String getSunSetTime() {
		return sunSetTime;
	}

	public void setSunSetTime(String sunSetTime) {
		this.sunSetTime = sunSetTime;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDayLengthTime() {
		return dayLengthTime;
	}

	public void setDayLengthTime(String dayLengthTime) {
		this.dayLengthTime = dayLengthTime;
	}
}
