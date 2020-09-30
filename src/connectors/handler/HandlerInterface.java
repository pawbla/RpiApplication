package connectors.handler;

import org.springframework.stereotype.Component;

import connectors.RestInterface;
import connectors.models.Response;
import connectors.parser.ParserInterface;

public interface HandlerInterface {
	
	void execute();
	void setParser(ParserInterface parser);
	void setRestConnector(RestInterface restConnector);
	Response getResponse();	
	String getResponseValue(String key);
}
