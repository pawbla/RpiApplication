package sensors.objects;

import org.springframework.stereotype.Component;

/**
 * POJO class to handle Service information like country, city, street
 * @author blach
 *
 */
@Component
public class ServiceInformation implements Sensor {

	/**
	 * Variables
	 */
	private String country;
	private String city;
	private String street;
	private String name;
	private int statusCode;
	private String date;
	
	public ServiceInformation() {
		this.country = "";
		this.street = "";
		this.city = "";
		this.name = name;
		this.statusCode = 0;
		this.date = "";
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
