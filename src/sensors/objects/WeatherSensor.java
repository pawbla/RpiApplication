package sensors.objects;

//POJO
public class WeatherSensor {
	
	private String temperature;
	private String humidity;
	private String pressure;
	private String poweredBy;
	private int statusCode;
	private String date;
	
	/**
	 * POJO class represents Weather Sensor object
	 */
	public WeatherSensor() { 
		statusCode = 0;
		poweredBy = "";
		temperature = "-";
		humidity = "-";
		pressure = "-";
		date = "-";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getPoweredBy() {
		return poweredBy;
	}

	public void setPoweredBy(String poweredBy) {
		this.poweredBy = poweredBy;
	}
	
	
}
