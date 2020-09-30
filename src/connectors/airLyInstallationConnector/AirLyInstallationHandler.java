package connectors.airLyInstallationConnector;

import org.json.JSONException;
import org.json.JSONObject;

import connectors.handler.AbstractHandler;
import connectors.models.Response;

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
	
	public AirLyInstallationHandler() {
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
