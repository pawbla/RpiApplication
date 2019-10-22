package connectors;

import org.springframework.http.ResponseEntity;

import connectors.models.Response;

public interface ConnectorInterface {
	
	public void execute();
	public Response getResponse();
}
