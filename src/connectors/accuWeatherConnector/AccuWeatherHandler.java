package connectors.accuWeatherConnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.ConnectorInterface;
import connectors.handler.AbstractHandler;
import connectors.parser.ParserInterface;
import connectors.registry.ConnectorsRegistryInterface;

@Component
@Qualifier("accuWeather")
public class AccuWeatherHandler  extends AbstractHandler {
	
	@Autowired
	public AccuWeatherHandler(@Qualifier("accuWeather") ParserInterface parser,
			@Qualifier("accuWeather") ConnectorInterface connector, ConnectorsRegistryInterface registry) {
		super(registry);
		this.setConnector(connector.getConnector());
		this.setParser(parser);
	}
}
