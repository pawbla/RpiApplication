package connectors.handler;

import org.springframework.stereotype.Component;

import connectors.ConnectorInterface;
import connectors.RestInterface;
import connectors.models.Connector;
import connectors.models.Response;
import connectors.parser.ParserInterface;

public interface HandlerInterface {
	
	void execute();
	void setParser(ParserInterface parser);
	void setConnector(Connector Connector);
	Response getResponse();	
	String getResponseValue(String key);
}
