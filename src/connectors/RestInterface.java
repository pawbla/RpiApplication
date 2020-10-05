package connectors;

import connectors.models.Connector;
import connectors.models.Request;
import connectors.models.Response;

public interface RestInterface {
	public void setConnector(Connector connector);
	public void execute();
}
