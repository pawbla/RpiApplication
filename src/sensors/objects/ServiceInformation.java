package sensors.objects;

import org.springframework.stereotype.Component;

/**
 * POJO class to handle Service information like country, city, street
 * @author blach
 *
 */
@Component
public class ServiceInformation {

	/**
	 * Variables
	 */
	private String country;
	private String city;
	private String street;
	private String name;
	
	public ServiceInformation() {
		this.country = "";
		this.street = "";
		this.city = "";
		this.name = name;
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
