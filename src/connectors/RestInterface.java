package connectors;

import connectors.models.Request;
import connectors.models.Response;

public interface RestInterface {
	public void setRequest(Request request);
	public void execute();
	public Response getResponse();
}
