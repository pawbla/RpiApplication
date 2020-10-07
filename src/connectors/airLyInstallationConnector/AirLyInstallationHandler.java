package connectors.airLyInstallationConnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import connectors.ConnectorInterface;
import connectors.handler.AbstractHandler;
import connectors.parser.ParserInterface;
import connectors.registry.ConnectorsRegistryInterface;

public class AirLyInstallationHandler extends AbstractHandler {
	
	@Autowired
	public AirLyInstallationHandler(@Qualifier("AirLy") ParserInterface parser,
			@Qualifier("AirLy") ConnectorInterface connector, ConnectorsRegistryInterface registry) {
		super(registry);
		this.setConnector(connector.getConnector());
		this.setParser(parser);
	}


}
