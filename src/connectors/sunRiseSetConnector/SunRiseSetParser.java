package connectors.sunRiseSetConnector;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import connectors.models.Response;
import connectors.parser.AbstractParser;

@Component
@Qualifier("sunRiseSet")
public class SunRiseSetParser extends AbstractParser {
	
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
	
	public SunRiseSetParser() {
		inFormatter = DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT).withZoneUTC();
		
		outFormatter = new SimpleDateFormat(OUT_DATE_FORMAT);
		outFormatter.setTimeZone(this.getCurrentTimeZone());
		
		outFormatter2 = new SimpleDateFormat(OUT_DATE_FORMAT);
		outFormatter2.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	@Override
	public void parse(Response response) throws JSONException {
		JSONObject jsonObject = new JSONObject(response.getBody());
		String sunRiseStr = jsonObject.getJSONObject(RESULTS_KEY).getString(SUN_RISE_KEY);
		String sunSetStr = jsonObject.getJSONObject(RESULTS_KEY).getString(SUN_SET_KEY);
		
		long dayLentStr = (long) jsonObject.getJSONObject(RESULTS_KEY).getInt(DAY_LENGTH_KEY);
		logger.trace("Fetched datas: rise: " + sunRiseStr + " set: " + sunSetStr + " day length: " + dayLentStr);
		
		this.addParsed("rise", outFormatter.format(DateTime.parse(sunRiseStr, inFormatter).toDate()));
		this.addParsed("set", outFormatter.format(DateTime.parse(sunSetStr, inFormatter).toDate()));
		this.addParsed("dayLength", outFormatter2.format(new Date((long)(dayLentStr*1000))));
	}
	
	private TimeZone getCurrentTimeZone() {
		return Calendar.getInstance().getTimeZone();
	}

}
