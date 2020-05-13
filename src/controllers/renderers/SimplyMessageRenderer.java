package controllers.renderers;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class SimplyMessageRenderer {
	private JSONObject response;
	
	public String getJSON(String message) {
		response = new JSONObject()
				.put("message", message);

		return response.toString();
	}
}
