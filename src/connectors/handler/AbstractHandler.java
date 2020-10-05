package connectors.handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import connectors.ConnectorInterface;
import connectors.RestConnector;
import connectors.RestInterface;
import connectors.models.Response;
import connectors.parser.ParserInterface;

public abstract class AbstractHandler implements HandlerInterface {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private ParserInterface parser;
	private RestConnector restConnector;
	
	public void setConnector(ConnectorInterface connector) {
		restConnector = new RestConnector();
		restConnector.setRequest(connector.getConnector().getRequest());
	}
	
	public void setParser(ParserInterface parser) {
		this.parser = parser;
	}
	
	public Response getResponse() {
		return restConnector.getResponse();
	}
	
	@Override
	public void execute() {
		restConnector.execute();
		parser.parse(restConnector.getResponse());			
	}

	public String getResponseValue(String key) {
		return parser.getParsed().get(key);
	}
}
