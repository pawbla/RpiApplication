package sensors.objects;

/**
 * POJO class represents Air Polution Sensor object
 */
public class AirPolutionSensor implements Sensor {

	/**
	 * Variables
	 */
	private String caqi;
	private String caqiColor;
	private String pm1;
	private String pm10;
	private String pm10percent;
	private String pm25;
	private String pm25percent;
	private int statusCode;
	private String date;
	
	public AirPolutionSensor() {
		this.caqi = "";
		this.caqiColor = "";
		this.pm1 = "";
		this.pm10 = "";
		this.pm10percent = "";
		this.pm25 = "";
		this.pm25percent = "";
		this.statusCode = 0;
		this.date = "";
	}

	public String getCaqi() {
		return caqi;
	}

	public void setCaqi(String caqi) {
		this.caqi = caqi;
	}

	public String getCaqiColor() {
		return caqiColor;
	}

	public void setCaqiColor(String caqiColor) {
		this.caqiColor = caqiColor;
	}

	public String getPm1() {
		return pm1;
	}

	public void setPm1(String pm1) {
		this.pm1 = pm1;
	}

	public String getPm10() {
		return pm10;
	}

	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}

	public String getPm10percent() {
		return pm10percent;
	}

	public void setPm10percent(String pm10percent) {
		this.pm10percent = pm10percent;
	}

	public String getPm25() {
		return pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	public String getPm25percent() {
		return pm25percent;
	}

	public void setPm25percent(String pm25percent) {
		this.pm25percent = pm25percent;
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
}
