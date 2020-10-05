package connectors.airLyInstallationConnector;

import org.springframework.beans.factory.annotation.Autowired;

import connectors.handler.AbstractHandler;
import connectors.registry.ConnectorsRegistryInterface;

public class AirLyInstallationHandler extends AbstractHandler {
	
	/**
	 * Constants
	 */
	private static final String ADDRESS_KEY = "address";
	private static final String COUNTRY_KEY = "country";
	private static final String CITY_KEY = "city"; 
	private static final String STREET_KEY = "street";
	
	/**
	 * Variables
	 */
	private String country;
	private String city;
	private String street;
	
	@Autowired
	public AirLyInstallationHandler(ConnectorsRegistryInterface registry) {
		super(registry);
		country = "";
		city = "";
		street = "";
	}

/*	@Override
	protected void parser(Response response) throws JSONException {
		JSONObject jsonArray = new JSONObject(response.getBody()).getJSONObject(ADDRESS_KEY);
		country = jsonArray.getString(COUNTRY_KEY);
		city = jsonArray.getString(CITY_KEY);
		street = jsonArray.getString(STREET_KEY);		
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}*/
}
