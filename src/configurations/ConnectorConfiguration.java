package configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;

import connectors.ConnectorInterface;
import connectors.accuWeatherConnector.AccuWeatherHandler;
import connectors.airLyConnector.AirLyHandler;
import connectors.internalConnector.InternalHandler;
import connectors.sunRiseSetConnector.SunRiseSetHandler;

@Configuration
@ComponentScan({"connectors", "parsers"})
public class ConnectorConfiguration {
	
	/**
	 * AirLy Connector configuration
	 **/
	@Autowired
	@Qualifier("airLyConnector")
	private ConnectorInterface airLyConnector;
	
	@Autowired
	private AirLyHandler airLy;
	
	private final int TIMEOUT = 120000;
	private final int DELAY_TIMEOUT = 20000;
	
	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(fixedRate = TIMEOUT, initialDelay = DELAY_TIMEOUT)
	public void fetchAirLyData() {
		airLyConnector.execute();
		airLy.parse(airLyConnector);
	}
	
	/**
	 * AccuWeather Connector configuration
	 */
	@Autowired
	@Qualifier("accuWeatherConnector")
	private ConnectorInterface accuWeatherConnector;
	
	@Autowired
	private AccuWeatherHandler accuWeather;
	
	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(cron="0 0 * * * *", zone="Europe/Warsaw")
	public void fetchAccuWeatherData() {
		accuWeatherConnector.execute();
		accuWeather.parse(accuWeatherConnector);
	}
	
	/**
	 * Internal Connector configuration
	 */	
	@Autowired
	@Qualifier("internalConnector")
 	private ConnectorInterface internalConnector;
	
	@Autowired
	private InternalHandler internal;
	
	private final int INT_TIMEOUT = 30000;
	private final int INT_DELAY_TIMEOUT = 20000;
	
	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(fixedRate = INT_TIMEOUT, initialDelay = INT_DELAY_TIMEOUT)
	public void fetchInternalData() {
		internalConnector.execute();
		internal.parse(internalConnector);
	}
		
	/**
	 * Sun rise set configuration
	 */
	@Autowired
	@Qualifier("sunRiseSetConnector")
	private ConnectorInterface sunRiseSetConnector;
	
	@Autowired
	private SunRiseSetHandler sunRiseSet;
	
	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(cron="10 0 0 * * ?", zone="Europe/Warsaw")
	public void fetchSunRiseSetData() {
		sunRiseSetConnector.execute();
		sunRiseSet.parse(sunRiseSetConnector);
	}
}


