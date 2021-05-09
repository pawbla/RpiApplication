package connectors.airLyConnector;

import connectors.RestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.ConnectorInterface;
import connectors.handler.AbstractHandler;
import connectors.parser.ParserInterface;
import connectors.registry.ConnectorsRegistryInterface;

@Component
@Qualifier("AirLy")
public class AirLyHandler extends AbstractHandler {
	
	@Autowired
	public AirLyHandler(RestInterface restConnector, @Qualifier("AirLy") ParserInterface parser,
						@Qualifier("AirLy") ConnectorInterface connector, ConnectorsRegistryInterface registry) {
		super(restConnector, registry);
		this.setConnector(connector.getConnector());
		this.setParser(parser);
	}
}
