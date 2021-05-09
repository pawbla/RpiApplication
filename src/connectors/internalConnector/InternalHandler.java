package connectors.internalConnector;

import connectors.RestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.ConnectorInterface;
import connectors.handler.AbstractHandler;
import connectors.parser.ParserInterface;
import connectors.registry.ConnectorsRegistryInterface;

@Component
@Qualifier("internal")
public class InternalHandler extends AbstractHandler {
	
	@Autowired
	public InternalHandler(RestInterface restConnector, @Qualifier("internal") ParserInterface parser,
						   @Qualifier("internal") ConnectorInterface connector, ConnectorsRegistryInterface registry) {
		super(restConnector, registry);
		this.setConnector(connector.getConnector());
		this.setParser(parser);
	}
}
