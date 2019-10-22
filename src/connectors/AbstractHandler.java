package connectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;

import connectors.models.Response;

public abstract class AbstractHandler {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private Response response;

	public void parse(ConnectorInterface connector) {
		this.response = connector.getResponse();
		if (response.isModified() && !response.isError()) {
			try {
				parser(response);
			} catch (JSONException e) {
				response.setError(true);
				response.setErrorMsg("An exception has occured during JSON convertion: " + e.getMessage());
				logger.warn("An exception has occured during JSON convertion: " + e.getMessage());
			}
		}
	}
	
	public Response getResponse() {
		return response;
	}
	

	protected abstract void parser(Response response) throws JSONException;
}
