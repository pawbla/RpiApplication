package connectors;

import connectors.models.Connector;

public interface RestInterface {
	public void setConnector(Connector connector);
	public void execute();
}
