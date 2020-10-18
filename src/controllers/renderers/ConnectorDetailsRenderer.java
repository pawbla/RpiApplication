package controllers.renderers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import connectors.models.Connector;
import connectors.registry.ConnectorsRegistryInterface;

@Component
public class ConnectorDetailsRenderer implements RestRespRenderer {
	
	private ConnectorsRegistryInterface register;
	
	@Autowired
	public ConnectorDetailsRenderer(ConnectorsRegistryInterface register) {
		this.register = register;
	}

	@Override
	public String getJSON() {
		JSONObject response = new JSONObject();
		JSONArray array = new JSONArray();
		register.getNamesList().forEach(name -> {
			array.put(populateValue(name));
		});
		return response.put("connectors", array).toString();
	}
	
	private JSONObject populateValue(String name) {
		JSONObject response = new JSONObject();
		Connector connector = register.getConnector(name);
		response.put("name", connector.getName())
			.put("provider", connector.getProvider())
			.put("link", connector.getLinkToProviderPage())
			.put("position", connector.getSensorPosition())
			.put("responseCode", connector.getResponse().getResponseCode())
			.put("isError", connector.getResponse().isError())
			.put("errorMessage", connector.getResponse().getErrorMsg())
			.put("date", connector.getResponse().getDate());
		return response; 
	}

}
