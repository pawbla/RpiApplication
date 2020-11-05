package connectors.handler;

import connectors.models.Connector;
import connectors.models.Response;
import connectors.parser.ParserInterface;

public interface HandlerInterface {
	
	void execute();
	void setParser(ParserInterface parser);
	void setConnector(Connector Connector);
	void setRecovery(int delay, int confTime);
	Response getResponse();	
	String getResponseValue(String key);
}
