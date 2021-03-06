package connectors.sunRiseSetConnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.ConnectorInterface;
import connectors.handler.AbstractHandler;
import connectors.parser.ParserInterface;
import connectors.registry.ConnectorsRegistryInterface;

@Component
@Qualifier("sunRiseSet")
public class SunRiseSetHandler extends AbstractHandler {

	@Autowired
	public SunRiseSetHandler(@Qualifier("sunRiseSet") ParserInterface parser, 
			@Qualifier("sunRiseSet") ConnectorInterface connector, ConnectorsRegistryInterface registry) {
		super(registry);
		this.setConnector(connector.getConnector());
		this.setParser(parser);
	}
}
