package connectors.handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import connectors.RestConnector;
import connectors.models.Connector;
import connectors.models.Response;
import connectors.parser.ParserInterface;
import connectors.registry.ConnectorsRegistryInterface;

public abstract class AbstractHandler implements HandlerInterface {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private ParserInterface parser;
	private RestConnector restConnector;
	private Connector connector;
	private ConnectorsRegistryInterface registry;
	private int delay;
	private int confTime;
	
	public AbstractHandler(ConnectorsRegistryInterface registry) {
		this.registry = registry;
	}
	
	public void setConnector(Connector connector) {
		this.restConnector = new RestConnector();
		this.restConnector.setConnector(connector);
		this.connector = connector;	
		this.registry.registerConnector(connector);
		this.delay = 0;
		this.confTime = 0;
	}
	
	public void setParser(ParserInterface parser) {
		this.parser = parser;
	}
	
	public Response getResponse() {
		return this.connector.getResponse();
	}
	
	@Override
	public void setRecovery(int delay, int confTime) {
		this.delay = delay;
		this.confTime = confTime;
	}
	
	@Override
	public void execute() {
		int iter = 0;
		do {
			this.restConnector.execute();
			Response response = this.connector.getResponse();
			this.parser.parse(response);
			if (response.getResponseCode() == 200 || confTime == 0) {
				break;
			}
			logger.debug("Prepare " + iter + " recovery reading datas for connector " + this.connector.getName());
			try {
				Thread.sleep((long)delay * 60 * 1000);
			} catch (InterruptedException e) {
				logger.error("An exception has occured during thread sleep " + e.getMessage());
			}
			iter++;
		} while (iter < confTime / (delay * 2));
		
	}

	public String getResponseValue(String key) {
		return this.parser.getParsed().get(key);
	}
}
