package connectors.internalConnector;

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
	public InternalHandler(@Qualifier("internal") ParserInterface parser, 
			@Qualifier("internal") ConnectorInterface connector, ConnectorsRegistryInterface registry) {
		super(registry);
		this.setConnector(connector.getConnector());
		this.setParser(parser);
	}
}
