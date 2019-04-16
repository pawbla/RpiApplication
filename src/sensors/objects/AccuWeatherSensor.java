package sensors.objects;

/**
 * POJO class represents Wind Sensor object
 * Powered by AccuWeather
 */
public class AccuWeatherSensor implements Sensor {

	/**
	 * Variables
	 */
	/* General */
	private String weatherText;
	private int weatherIcon;
	/* Wind */
	private String direction;
	private int degrees;
	private String speed;
	/* UV indexes and visibility */
	private int uvIndex;
	private String uvIndexText;
	private String visibility;
	/* Cloud cover and ceiling */
	private int cloudCover;
	private int ceiling;
	/* Pressure */
	private int value;
	private String tendencyText;
	private String tendencyCode;
	/* Common */
	private int statusCode;
	private String date;
	
	public AccuWeatherSensor() {
		direction = "-";
		degrees = 0;
		speed = "-";
		
		weatherText = "-";
		weatherIcon = 0;
		uvIndex = 0;
		uvIndexText = "-";
		visibility = "-";
		
		cloudCover = 0;
		ceiling = 0;
		
		value = 0;
		tendencyText = "-";
		tendencyCode = "-";
		
		this.statusCode = 0;
		this.date = "-";
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getDegrees() {
		return degrees;
	}

	public void setDegrees(int degrees) {
		this.degrees = degrees;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	@Override
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public int getStatusCode() {
		return statusCode;
	}

	@Override
	public void setDate(String date) {
		this.date = date;	
	}

	@Override
	public String getDate() {
		return date;
	}

	public String getWeatherText() {
		return weatherText;
	}

	public void setWeatherText(String weatherText) {
		this.weatherText = weatherText;
	}

	public int getWeatherIcon() {
		return weatherIcon;
	}

	public void setWeatherIcon(int weatherIcon) {
		this.weatherIcon = weatherIcon;
	}

	public int getUvIndex() {
		return uvIndex;
	}

	public void setUvIndex(int uvIndex) {
		this.uvIndex = uvIndex;
	}

	public String getUvIndexText() {
		return uvIndexText;
	}

	public void setUvIndexText(String uvIndexText) {
		this.uvIndexText = uvIndexText;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public int getCloudCover() {
		return cloudCover;
	}

	public void setCloudCover(int cloudCover) {
		this.cloudCover = cloudCover;
	}

	public int getCeiling() {
		return ceiling;
	}

	public void setCeiling(int ceiling) {
		this.ceiling = ceiling;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getTendencyText() {
		return tendencyText;
	}

	public void setTendencyText(String tendencyText) {
		this.tendencyText = tendencyText;
	}

	public String getTendencyCode() {
		return tendencyCode;
	}

	public void setTendencyCode(String tendencyCode) {
		this.tendencyCode = tendencyCode;
	}	
}
