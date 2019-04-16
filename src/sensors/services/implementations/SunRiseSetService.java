package sensors.services.implementations;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import sensors.objects.Sensor;
import sensors.objects.SunRiseSetSensor;
import sensors.services.AbstractSensorInterface;

@Component
@Qualifier("sunRiseSet")
public class SunRiseSetService extends AbstractSensorInterface {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Constants
	 */
	public static final String SENSOR_NAME = "Sun Rise Sun Set Service";
	private static final String RESULTS_KEY = "results";
	private static final String SUN_RISE_KEY = "sunrise";
	private static final String SUN_SET_KEY = "sunset";
	private static final String DAY_LENGTH_KEY = "day_length";
	private static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
	private static final String OUT_DATE_FORMAT = "HH:mm";
	
	/**
	 * Variables
	 */
	private DateTimeFormatter inFormatter;
	private SimpleDateFormat outFormatter;
	private SimpleDateFormat outFormatter2;
	private DateTimeZone zone;
	
	public SunRiseSetService(@Value("${custom.ipSunSetRise}") String ip) {
		super(ip, SENSOR_NAME);
		logger.info("Create " + SunRiseSetService.SENSOR_NAME + " object with IP: " + ip);
		HttpHeaders headers = new HttpHeaders();
		this.setHeader(headers);
		inFormatter = DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT);
		outFormatter = new SimpleDateFormat(OUT_DATE_FORMAT);
		outFormatter2 = new SimpleDateFormat(OUT_DATE_FORMAT);
		outFormatter2.setTimeZone(TimeZone.getTimeZone("GMT"));
		zone = DateTimeZone.forID("Europe/Warsaw");
	}

	@Override
	public <T extends Sensor> T getSensor(T sensor)  {
		logger.debug("Prepare sensor information data for " + this.getSensorName());
		if (mapper.getResponseCode() == 200) {
			logger.debug("JSON Object:" + mapper.getJSONObject().toString());
			String sunRiseStr = mapper.getJSONObject().getJSONObject(RESULTS_KEY).getString(SUN_RISE_KEY);
			String sunSetStr = mapper.getJSONObject().getJSONObject(RESULTS_KEY).getString(SUN_SET_KEY);
			long dayLentStr = (long) mapper.getJSONObject().getJSONObject(RESULTS_KEY).getInt(DAY_LENGTH_KEY);
			logger.debug("Sun rise " + sunRiseStr + " sun set " + sunSetStr + " day length " + dayLentStr);
			((SunRiseSetSensor)sensor).setSunRiseTime(outFormatter.format(DateTime.parse(sunRiseStr, inFormatter).toDateTime(zone).toDate()));
			((SunRiseSetSensor)sensor).setSunSetTime(outFormatter.format(DateTime.parse(sunSetStr, inFormatter).toDateTime(zone).toDate()));
			((SunRiseSetSensor)sensor).setDayLengthTime(outFormatter2.format(new DateTime(dayLentStr * 1000).toDate()));
		}
		return sensor;
	}
	
	/**
	 * Method executed with set period of time
	 */
	@Scheduled(cron="10 0 0 * * ?", zone="Europe/Warsaw")
	private void fetchDatas() {		
		this.getRestData();
	}
	
	@PostConstruct
	private void initialMeasurement() {
		this.getRestData();
	}
}
