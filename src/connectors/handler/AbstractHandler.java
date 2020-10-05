package connectors.handler;
import org.springframework.beans.factory.annotation.Autowired;

import connectors.RestConnector;
import connectors.models.Connector;
import connectors.models.Response;
import connectors.parser.ParserInterface;
import connectors.registry.ConnectorsRegistryInterface;

public abstract class AbstractHandler implements HandlerInterface {
	
	private ParserInterface parser;
	private RestConnector restConnector;
	private Connector connector;
	private ConnectorsRegistryInterface registry;
	
	public AbstractHandler(ConnectorsRegistryInterface registry) {
		this.registry = registry;
	}
	
	public void setConnector(Connector connector) {
		this.restConnector = new RestConnector();
		this.restConnector.setConnector(connector);
		this.connector = connector;	
		this.registry.registerConnector(connector);
	}
	
	public void setParser(ParserInterface parser) {
		this.parser = parser;
	}
	
	public Response getResponse() {
		return this.connector.getResponse();
	}
	
	@Override
	public void execute() {
		this.restConnector.execute();
		this.parser.parse(this.connector.getResponse());			
	}

	public String getResponseValue(String key) {
		return this.parser.getParsed().get(key);
	}
}
