package connectors.airLyInstallationConnector;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.Values;
import connectors.models.Response;
import connectors.parser.AbstractParser;

@Component
@Qualifier("AirLyInstallation")
public class AirLyInstalltionParser  extends AbstractParser {
	
	public enum AirLyInstalltionValues implements Values {
		
		COUNTRY("country"),
		CITY("city"),
		STREET("street");

		public final String value;
		
		private AirLyInstalltionValues(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	/**
	 * Constants
	 */
	private static final String ADDRESS_KEY = "address";
	private static final String COUNTRY_KEY = "country";
	private static final String CITY_KEY = "city"; 
	private static final String STREET_KEY = "street";

	@Override
	public void parse(Response response) throws JSONException {
		JSONObject jsonArray = new JSONObject(response.getBody()).getJSONObject(ADDRESS_KEY);
		this.addParsed(AirLyInstalltionValues.COUNTRY, jsonArray.getString(COUNTRY_KEY));
		this.addParsed(AirLyInstalltionValues.CITY, jsonArray.getString(CITY_KEY));
		this.addParsed(AirLyInstalltionValues.STREET, jsonArray.getString(STREET_KEY));
	}

}
