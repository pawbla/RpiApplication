package connectors.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import connectors.models.Connector;

@Component
public class ConnectorsRegistry implements ConnectorsRegistryInterface {
	
	public Map<String, Connector> registry;
	
	public ConnectorsRegistry() {
		registry = new HashMap<>();
	}

	@Override
	public void registerConnector(Connector connector) {
		registry.put(connector.getName(), connector);	
	}

	@Override
	public Connector getConnector(String name) {
		return registry.get(name);
	}

	@Override
	public Set<String> getNamesList() {
		return registry.keySet();
	}

}
