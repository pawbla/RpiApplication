package connectors.registry;

import java.util.Set;

import connectors.models.Connector;

public interface ConnectorsRegistryInterface {
	
	public void registerConnector(Connector connector);
	public Connector getConnector(String name);
	public Set<String> getNamesList();
}
