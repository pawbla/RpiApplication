package connectors;

import connectors.models.Response;

public interface ConnectorInterface {
	
	public void execute();
	public Response getResponse();
}
