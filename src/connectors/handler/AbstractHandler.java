package connectors.handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import connectors.ConnectorInterface;
import connectors.RestConnector;
import connectors.models.Connector;
import connectors.models.Response;
import connectors.parser.ParserInterface;

public abstract class AbstractHandler implements HandlerInterface {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private ParserInterface parser;
	private RestConnector restConnector;
	private Connector connector;
	
	public void setConnector(Connector connector) {
		this.restConnector = new RestConnector();
		this.restConnector.setConnector(connector);
		this.connector = connector;	
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
