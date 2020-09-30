package configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;

import connectors.RestInterface;
import connectors.accuWeatherConnector.AccuWeatherHandler;
import connectors.airLyConnector.AirLyHandler;
import connectors.handler.HandlerInterface;
import connectors.internalConnector.InternalHandler;
import connectors.sunRiseSetConnector.SunRiseSetHandler;

@Configuration
@ComponentScan({"connectors", "parsers"})
public class ConnectorConfiguration {
	
	/**
	 * AirLy Connector configuration
	 **/
	@Autowired
	@Qualifier("AirLy")
	private HandlerInterface airLy;

	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(cron="0 0/15 * ? * *", zone="Europe/Warsaw") //cron at every 15 minutes
	public void fetchAirLyData() {
		airLy.execute();
	}
	
	/**
	 * AccuWeather Connector configuration
	 */
	@Autowired
	@Qualifier("accuWeather")
	private HandlerInterface accuWeather;
	
	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(cron="0 0/30 * ? * *", zone="Europe/Warsaw")  //cron at every 30 minutes
	public void fetchAccuWeatherData() { 
		accuWeather.execute();
	}
	
	/**
	 * Internal Connector configuration
	 */	
	@Autowired
	@Qualifier("internal")
 	private HandlerInterface internal;
	
	private final int INT_TIMEOUT = 30000;
	private final int INT_DELAY_TIMEOUT = 20000;
	
	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(fixedRate = INT_TIMEOUT, initialDelay = INT_DELAY_TIMEOUT)
	public void fetchInternalData() {
		internal.execute();
	}
		
	/**
	 * Sun rise set configuration
	 */
	@Autowired
	@Qualifier("sunRiseSet")
	private HandlerInterface sunRiseSet;

	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(cron="10 0 0 * * ?", zone="Europe/Warsaw") //cron configured at 00:00:10am every day
	public void fetchSunRiseSetData() {
		sunRiseSet.execute();
	}
}


