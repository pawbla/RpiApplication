package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Controller created for mocked response from external services
 * @author blach
 *
 */
@Controller
@Profile("dev")
public class MockedServices {
	
	/**
	 * Example of responses
	 */
	private static final String INTERNAL_JSON = "{\"Temperature\": \"11\", \"Humidity\": \"35\", \"Pressure\" : \"12345\"}";
	
	private static final String OPEN_WEATHER_JSON = "{\"coord\":{\"lon\":19.92,\"lat\":50.08},\"weather"
			+ "\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"base\""
			+ ":\"stations\",\"main\":{\"temp\":282.29,\"pressure\":1007,\"humidity\":93,\"temp_min\":281.48"
			+ ",\"temp_max\":282.59},\"visibility\":9000,\"wind\":{\"speed\":5.1,\"deg\":40},\"rain\":{\"1h\""
			+ ":0.51},\"clouds\":{\"all\":75},\"dt\":1570287023,\"sys\":{\"type\":1,\"id\":1701,\"message\":0.0076"
			+ ",\"country\":\"PL\",\"sunrise\":1570250737,\"sunset\":1570291914},\"timezone\":7200,\"id\":3094802,"
			+ "\"name\":\"Poznan\",\"cod\":200}";
	
	private static final String AIRLY_JSON = "{"+ 
			" \"current\": {" + 
			"    \"fromDateTime\": \"2019-10-05T14:41:06.644Z\"," + 
			"    \"tillDateTime\": \"2019-10-05T15:41:06.644Z\"," + 
			"    \"values\": [" + 
			"      {" + 
			"        \"name\": \"PM1\"," + 
			"        \"value\": 12.81" + 
			"      }," + 
			"      {" + 
			"        \"name\": \"PM25\"," + 
			"        \"value\": 17.07" + 
			"      }," + 
			"      {" + 
			"        \"name\": \"PM10\"," + 
			"        \"value\": 31.11" + 
			"      }," + 
			"      {" + 
			"        \"name\": \"PRESSURE\"," + 
			"        \"value\": 1007.43" + 
			"      }," + 
			"      {" + 
			"        \"name\": \"HUMIDITY\"," + 
			"        \"value\": 95.71" + 
			"      }," + 
			"      {" + 
			"        \"name\": \"TEMPERATURE\"," + 
			"        \"value\": 8.46" + 
			"      }" + 
			"    ]," + 
			"    \"indexes\": [" + 
			"      {" + 
			"        \"name\": \"AIRLY_CAQI\"," + 
			"        \"value\": 31.11," + 
			"        \"level\": \"LOW\"," + 
			"        \"description\": \"Air is quite good.\"," + 
			"        \"advice\": \"Time for a walk with friends or activities with your family - because the air is clean!\"," + 
			"        \"color\": \"#D1CF1E\"" + 
			"      }" + 
			"    ]," + 
			"    \"standards\": [" + 
			"      {" + 
			"        \"name\": \"WHO\"," + 
			"        \"pollutant\": \"PM25\"," + 
			"        \"limit\": 25," + 
			"        \"percent\": 68.28" + 
			"      }," + 
			"      {" + 
			"        \"name\": \"WHO\"," + 
			"        \"pollutant\": \"PM10\"," + 
			"        \"limit\": 50," + 
			"        \"percent\": 62.23" + 
			"      }" + 
			"    ]" + 
			"  }," + 
			"  \"history\": [" + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-04T15:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-04T16:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 5.53" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 7.44" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 13.25" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1010.96" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 56.05" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 11.14" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 13.25," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"It couldn't be better ;)\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 29.75" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 26.5" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-04T16:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-04T17:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 11.93" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 16.45" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 30.56" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1010.95" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 63.54" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 10.21" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 30.56," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Time for a walk with friends or activities with your family - because the air is clean!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 65.79" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 61.11" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-04T17:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-04T18:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 10.81" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 14.74" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 26.62" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1010.81" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 71.2" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 8.91" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 26.62," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"You can go outside without any worries.\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 58.98" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 53.23" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-04T18:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-04T19:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 14.34" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 20.16" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 37.28" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1010.73" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 81.92" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 7.55" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 37.28," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Great air for a walk to the park!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 80.63" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 74.57" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-04T19:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-04T20:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 19.17" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 28.5" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 55.06" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1010.71" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 89.72" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 7.2" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 53.17," + 
			"          \"level\": \"MEDIUM\"," + 
			"          \"description\": \"Well... It's been better.\"," + 
			"          \"advice\": \"Something's hanging in the air. Take care!\"," + 
			"          \"color\": \"#EFBB0F\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 113.98" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 110.13" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-04T20:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-04T21:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 19.5" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 29.25" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 56.62" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1010.42" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 92.04" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 7.33" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 54.14," + 
			"          \"level\": \"MEDIUM\"," + 
			"          \"description\": \"Well... It's been better.\"," + 
			"          \"advice\": \"Protect your lungs!\"," + 
			"          \"color\": \"#EFBB0F\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 116.99" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 113.24" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-04T21:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-04T22:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 18.84" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 27.73" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 54.07" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1010.1" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 90.68" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 7.52" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 52.55," + 
			"          \"level\": \"MEDIUM\"," + 
			"          \"description\": \"Well... It's been better.\"," + 
			"          \"advice\": \"Try to stay with your family at home.\"," + 
			"          \"color\": \"#EFBB0F\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 110.93" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 108.15" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-04T22:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-04T23:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 17.55" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 24.89" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 48.69" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1009.69" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 88.63" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 7.58" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 48.69," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Take a deep breath. Today, you can. ;)\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 99.57" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 97.38" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-04T23:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T00:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 17.6" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 25.1" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 49.47" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1009.3" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 91.72" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 7.03" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 49.47," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"The air is nice and clean today!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 100.42" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 98.93" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T00:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T01:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 18.11" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 26.14" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 51.03" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1008.77" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 93.37" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 6.85" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 50.64," + 
			"          \"level\": \"MEDIUM\"," + 
			"          \"description\": \"Well... It's been better.\"," + 
			"          \"advice\": \"Something's hanging in the air. Take care!\"," + 
			"          \"color\": \"#EFBB0F\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 104.54" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 102.06" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T01:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T02:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 17.78" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 25.63" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 50.21" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1008.57" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 92.61" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 6.83" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 50.13," + 
			"          \"level\": \"MEDIUM\"," + 
			"          \"description\": \"Well... It's been better.\"," + 
			"          \"advice\": \"Something's hanging in the air. Take care!\"," + 
			"          \"color\": \"#EFBB0F\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 102.51" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 100.42" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T02:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T03:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 17.65" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 25.5" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 49.87" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1008.4" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 92.91" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 6.88" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 49.87," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Time for a walk with friends or activities with your family - because the air is clean!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 102" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 99.75" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T03:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T04:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 17.22" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 24.48" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 48.14" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1008.15" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 93.2" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 7.02" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 48.14," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Take a breath!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 97.9" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 96.28" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T04:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T05:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 19.27" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 28.69" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 55.62" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1008.09" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 95.24" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 7.04" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 53.51," + 
			"          \"level\": \"MEDIUM\"," + 
			"          \"description\": \"Well... It's been better.\"," + 
			"          \"advice\": \"This isn't the best day for out-of-home activities.\"," + 
			"          \"color\": \"#EFBB0F\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 114.74" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 111.23" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T05:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T06:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 19.06" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 28.41" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 54.87" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1007.98" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 98.3" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 7.04" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 53.04," + 
			"          \"level\": \"MEDIUM\"," + 
			"          \"description\": \"Well... It's been better.\"," + 
			"          \"advice\": \"The smog is coming! :O\"," + 
			"          \"color\": \"#EFBB0F\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 113.65" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 109.74" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T06:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T07:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 14.57" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 20.68" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 39.15" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1007.68" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 99.77" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 7.02" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 39.15," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Great air for a walk to the park!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 82.73" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 78.29" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T07:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T08:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 8.07" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 10.68" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 19.52" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1007.47" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 96.88" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 6.92" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 19.52," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Catch your breath!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 42.71" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 39.05" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T08:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T09:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 4.59" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 6.49" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 11.87" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1007.27" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 94.98" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 7.85" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 11.87," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Great air!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 25.94" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 23.74" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T09:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T10:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 5.98" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 8.06" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 14.45" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1007.33" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 92.15" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 8.14" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 14.45," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Enjoy life!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 32.26" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 28.89" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T10:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T11:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 7.49" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 9.87" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 17.73" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1007.11" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 92.04" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 8.88" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 17.73," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Green equals clean!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 39.49" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 35.46" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T11:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T12:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 6.03" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 7.98" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 14.37" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1006.83" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 94.94" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 9.22" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 14.37," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Breathe deep! The air is clean!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 31.93" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 28.74" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T12:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T13:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 10.93" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 15" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 27.16" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1006.85" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 94.17" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 8.98" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 27.16," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Do you smell it? That's the smell of clean air. :)\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 59.99" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 54.31" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T13:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T14:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 11.01" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 15.01" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 26.8" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1006.96" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 91.23" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 8.7" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 26.8," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Take a breath!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 60.02" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 53.59" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T14:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T15:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM1\"," + 
			"          \"value\": 12.55" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 16.88" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 29.96" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PRESSURE\"," + 
			"          \"value\": 1007.19" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"HUMIDITY\"," + 
			"          \"value\": 94.29" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"TEMPERATURE\"," + 
			"          \"value\": 8.51" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 29.96," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Time for a walk with friends or activities with your family - because the air is clean!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 67.5" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 59.92" + 
			"        }" + 
			"      ]" + 
			"    }" + 
			"  ]," + 
			"  \"forecast\": [" + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T15:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T16:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 17.47" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 29.82" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 29.82," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Take a breath!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 69.87" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 59.64" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T16:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T17:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 17.61" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 30.54" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 30.54," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Do you want to see what's outside? The air is nice today!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 70.46" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 61.09" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T17:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T18:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 17.96" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 31.72" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 31.72," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"A perfect day for outdoor sports!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 71.82" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 63.43" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T18:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T19:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 18.59" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 33.34" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 33.34," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Enjoy the clean air.\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 74.37" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 66.68" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T19:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T20:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 18.98" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 34.34" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 34.34," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Great air for a walk to the park!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 75.93" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 68.68" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T20:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T21:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 18.96" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 34.64" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 34.64," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Do you smell it? That's the smell of clean air. :)\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 75.83" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 69.28" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T21:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T22:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 18.69" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 34.65" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 34.65," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"How about going for a walk?\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 74.75" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 69.31" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T22:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-05T23:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 18.24" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 34.33" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 34.33," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Take a breath!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 72.95" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 68.65" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-05T23:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T00:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 17.81" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 33.67" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 33.67," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Do you smell it? That's the smell of clean air. :)\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 71.23" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 67.34" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T00:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T01:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 17.23" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 32.9" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 32.9," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"A perfect day for outdoor sports!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 68.94" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 65.81" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T01:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T02:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 16.25" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 31.54" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 31.54," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Don't miss this day! The clean air calls!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 65.01" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 63.08" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T02:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T03:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 15.14" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 29.86" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 29.86," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"You can go out and enjoy nature without worries.\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 60.57" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 59.73" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T03:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T04:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 14.06" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 27.84" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 27.84," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Great air for a walk to the park!\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 56.26" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 55.68" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T04:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T05:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 13.14" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 26.04" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 26.04," + 
			"          \"level\": \"LOW\"," + 
			"          \"description\": \"Air is quite good.\"," + 
			"          \"advice\": \"Do you smell it? That's the smell of clean air. :)\"," + 
			"          \"color\": \"#D1CF1E\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 52.55" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 52.08" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T05:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T06:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 12.31" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 24.35" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 24.35," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Enjoy life!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 49.25" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 48.7" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T06:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T07:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 11.3" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 22.33" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 22.33," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"The air is grand today. ;)\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 45.2" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 44.67" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T07:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T08:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 10.42" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 20.35" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 20.35," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Enjoy life!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 41.67" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 40.71" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T08:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T09:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 9.96" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 19.19" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 19.19," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Zero dust - zero worries!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 39.86" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 38.37" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T09:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T10:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 9.73" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 18.66" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 18.66," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"The air is grand today. ;)\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 38.93" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 37.31" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T10:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T11:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 9.79" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 18.82" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 18.82," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Great air!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 39.14" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 37.64" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T11:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T12:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 9.78" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 19.01" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 19.01," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Zero dust - zero worries!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 39.13" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 38.02" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T12:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T13:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 9.76" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 19.32" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 19.32," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Green, green, green!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 39.05" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 38.63" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T13:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T14:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 9.77" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 19.7" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 19.7," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Perfect air for exercising! Go for it!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 39.07" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 39.4" + 
			"        }" + 
			"      ]" + 
			"    }," + 
			"    {" + 
			"      \"fromDateTime\": \"2019-10-06T14:00:00.000Z\"," + 
			"      \"tillDateTime\": \"2019-10-06T15:00:00.000Z\"," + 
			"      \"values\": [" + 
			"        {" + 
			"          \"name\": \"PM25\"," + 
			"          \"value\": 10.41" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"PM10\"," + 
			"          \"value\": 20.9" + 
			"        }" + 
			"      ]," + 
			"      \"indexes\": [" + 
			"        {" + 
			"          \"name\": \"AIRLY_CAQI\"," + 
			"          \"value\": 20.9," + 
			"          \"level\": \"VERY_LOW\"," + 
			"          \"description\": \"Great air here today!\"," + 
			"          \"advice\": \"Dear me, how wonderful!\"," + 
			"          \"color\": \"#6BC926\"" + 
			"        }" + 
			"      ]," + 
			"      \"standards\": [" + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM25\"," + 
			"          \"limit\": 25," + 
			"          \"percent\": 41.64" + 
			"        }," + 
			"        {" + 
			"          \"name\": \"WHO\"," + 
			"          \"pollutant\": \"PM10\"," + 
			"          \"limit\": 50," + 
			"          \"percent\": 41.8" + 
			"        }" + 
			"      ]" + 
			"    }" + 
			"  ]" + 
			"}";
	
	private static final String AIRLY_INSTALATIONS_JSON = "{" + 
			"  \"id\": 204," + 
			"  \"location\": {" + 
			"    \"latitude\": 50.062006," + 
			"    \"longitude\": 19.940984" + 
			"  }," + 
			"  \"address\": {" + 
			"    \"country\": \"Poland\"," + 
			"    \"city\": \"Kraków\"," + 
			"    \"street\": \"Mikołajska\"," + 
			"    \"number\": \"4B\"," + 
			"    \"displayAddress1\": \"Kraków\"," + 
			"    \"displayAddress2\": \"Mikołajska\"" + 
			"  }," + 
			"  \"elevation\": 220.38," + 
			"  \"airly\": true," + 
			"  \"sponsor\": {" + 
			"    \"name\": \"KrakówOddycha\"," + 
			"    \"description\": \"Airly Sensor is part of action\"," + 
			"    \"logo\": \"https://cdn.airly.eu/logo/KrakówOddycha.jpg\"," + 
			"    \"link\": \"https://sponsor_home_address.pl\"" + 
			"  }" + 
			"}" + 
			"";
			
	private static final String SUN_RISE_SET_JSON = "{\"results\":{\"sunrise\":\"2019-10-05T04:46:45+00:00\",\"sunset\":\"2019-10-05T16:10:24+00:00\",\"solar_noon\":\"2019-10-05T10:28:35+00:00\",\"day_length\":41019,\"civil_twilight_begin\":\"2019-10-05T04:14:35+00:00\",\"civil_twilight_end\":\"2019-10-05T16:42:34+00:00\",\"nautical_twilight_begin\":\"2019-10-05T03:37:08+00:00\",\"nautical_twilight_end\":\"2019-10-05T17:20:01+00:00\",\"astronomical_twilight_begin\":\"2019-10-05T02:59:05+00:00\",\"astronomical_twilight_end\":\"2019-10-05T17:58:04+00:00\"},\"status\":\"OK\"}";
	
	private static final String ACCU_WEATHER_JSON = "[{\"LocalObservationDateTime\":\"2019-10-05T16:55:00+02:00\",\"EpochTime\""
			+ ":1570287300,\"WeatherText\":\"Pochmurno\",\"WeatherIcon\":7,\"HasPrecipitation\":false,\"PrecipitationType\":null"
			+ ",\"IsDayTime\":true,\"Temperature\":{\"Metric\":{\"Value\":8.9,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value"
			+ "\":48.0,\"Unit\":\"F\",\"UnitType\":18}},\"RealFeelTemperature\":{\"Metric\":{\"Value\":4.0,\"Unit\":\"C\",\"UnitType"
			+ "\":17},\"Imperial\":{\"Value\":39.0,\"Unit\":\"F\",\"UnitType\":18}},\"RealFeelTemperatureShade\":{\"Metric\":{\"Value"
			+ "\":4.0,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":39.0,\"Unit\":\"F\",\"UnitType\":18}},\"RelativeHumidity"
			+ "\":93,\"DewPoint\":{\"Metric\":{\"Value\":7.8,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":46.0,\"Unit\":\"F"
			+ ""
			+ "\",\"UnitType\":18}},\"Wind\":{\"Direction\":{\"Degrees\":45,\"Localized\":\"NE\",\"English\":\"NE\"},\"Speed\":{\"Metric"
			+ "\":{\"Value\":18.5,\"Unit\":\"km/h\",\"UnitType\":7},\"Imperial\":{\"Value\":11.5,\"Unit\":\"mi/h\",\"UnitType\":9}}},\""
			+ "WindGust\":{\"Speed\":{\"Metric\":{\"Value\":18.5,\"Unit\":\"km/h\",\"UnitType\":7},\"Imperial\":{\"Value\":11.5,\"Unit\""
			+ ":\"mi/h\",\"UnitType\":9}}},\"UVIndex\":7,\"UVIndexText\":\"Niska\",\"Visibility\":{\"Metric\":{\"Value\":9.7,\"Unit\":\""
			+ "km\",\"UnitType\":6},\"Imperial\":{\"Value\":6.0,\"Unit\":\"mi\",\"UnitType\":2}},\"ObstructionsToVisibility\":\"R-\",\""
			+ "CloudCover\":100,\"Ceiling\":{\"Metric\":{\"Value\":457.0,\"Unit\":\"m\",\"UnitType\":5},\"Imperial\":{\"Value\":1500.0,"
			+ "\"Unit\":\"ft\",\"UnitType\":0}},\"Pressure\":{\"Metric\":{\"Value\":1007.0,\"Unit\":\"mb\",\"UnitType\":14},\"Imperial\":{"
			+ "\"Value\":29.74,\"Unit\":\"inHg\",\"UnitType\":12}},\"PressureTendency\":{\"LocalizedText\":\"Rośnie\",\"Code\":\"R\"},"
			+ "\"Past24HourTemperatureDeparture\":{\"Metric\":{\"Value\":-3.3,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":-6.0,"
			+ "\"Unit\":\"F\",\"UnitType\":18}},\"ApparentTemperature\":{\"Metric\":{\"Value\":10.6,\"Unit\":\"C\",\"UnitType\":17},\"Imperial"
			+ "\":{\"Value\":51.0,\"Unit\":\"F\",\"UnitType\":18}},\"WindChillTemperature\":{\"Metric\":{\"Value\":6.1,\"Unit\":\"C\","
			+ "\"UnitType\":17},\"Imperial\":{\"Value\":43.0,\"Unit\":\"F\",\"UnitType\":18}},\"WetBulbTemperature\":{\"Metric\":{\"Value"
			+ "\":8.3,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":47.0,\"Unit\":\"F\",\"UnitType\":18}},\"Precip1hr\":{\"Metric"
			+ "\":{\"Value\":0.5,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.02,\"Unit\":\"in\",\"UnitType\":1}},\"PrecipitationSummary"
			+ "\":{\"Precipitation\":{\"Metric\":{\"Value\":0.5,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.02,\"Unit\":\"in\","
			+ "\"UnitType\":1}},\"PastHour\":{\"Metric\":{\"Value\":0.5,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.02,\"Unit"
			+ "\":\"in\",\"UnitType\":1}},\"Past3Hours\":{\"Metric\":{\"Value\":1.0,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value"
			+ "\":0.04,\"Unit\":\"in\",\"UnitType\":1}},\"Past6Hours\":{\"Metric\":{\"Value\":2.2,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial"
			+ "\":{\"Value\":0.09,\"Unit\":\"in\",\"UnitType\":1}},\"Past9Hours\":{\"Metric\":{\"Value\":4.1,\"Unit\":\"mm\",\"UnitType\":3},"
			+ "\"Imperial\":{\"Value\":0.16,\"Unit\":\"in\",\"UnitType\":1}},\"Past12Hours\":{\"Metric\":{\"Value\":5.6,\"Unit\":\"mm\","
			+ "\"UnitType\":3},\"Imperial\":{\"Value\":0.22,\"Unit\":\"in\",\"UnitType\":1}},\"Past18Hours\":{\"Metric\":{\"Value\":6.1,"
			+ "\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.24,\"Unit\":\"in\",\"UnitType\":1}},\"Past24Hours\":{\"Metric"
			+ "\":{\"Value\":6.1,\"Unit\":\"mm\",\"UnitType\":3},\"Imperial\":{\"Value\":0.24,\"Unit\":\"in\",\"UnitType\":1}}},"
			+ "\"TemperatureSummary\":{\"Past6HourRange\":{\"Minimum\":{\"Metric\":{\"Value\":8.1,\"Unit\":\"C\",\"UnitType\":17},"
			+ "\"Imperial\":{\"Value\":46.0,\"Unit\":\"F\",\"UnitType\":18}},\"Maximum\":{\"Metric\":{\"Value\":9.0,\"Unit\":\"C\",\"UnitType"
			+ "\":17},\"Imperial\":{\"Value\":48.0,\"Unit\":\"F\",\"UnitType\":18}}},\"Past12HourRange\":{\"Minimum\":{\"Metric\":{\"Value"
			+ "\":7.2,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":45.0,\"Unit\":\"F\",\"UnitType\":18}},\"Maximum\":{\"Metric"
			+ "\":{\"Value\":9.0,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":48.0,\"Unit\":\"F\",\"UnitType\":18}}},\"Past24HourRange"
			+ "\":{\"Minimum\":{\"Metric\":{\"Value\":7.2,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":45.0,\"Unit\":\"F\","
			+ "\"UnitType\":18}},\"Maximum\":{\"Metric\":{\"Value\":12.2,\"Unit\":\"C\",\"UnitType\":17},\"Imperial\":{\"Value\":54.0,"
			+ "\"Unit\":\"F\",\"UnitType\":18}}}},\"MobileLink\":\"http://m.accuweather.com/\",\"Link\":\"http://www.accuweather\"}]";
	
	@RequestMapping(value = "/mocked_internalsensor", method=GET, produces = "application/json")
	@ResponseBody
	@ResponseStatus( HttpStatus.OK )
	public String internalSensorMock() {
		return INTERNAL_JSON;
	}
	
	@RequestMapping(value = "/mocked_externalsensor", method=GET, produces = "application/json")
	@ResponseBody
	@ResponseStatus( HttpStatus.OK )
	public String externalSensorMock() {
		return OPEN_WEATHER_JSON;
	}
	
	@RequestMapping(value = "/mocked_airly", method=GET, produces = "application/json")
	@ResponseBody
	@ResponseStatus( HttpStatus.OK )
	public String airlyMock() {
		return AIRLY_JSON;
	}
	
	@RequestMapping(value = "/mocked_airlyInstalation", method=GET, produces = "application/json")
	@ResponseBody
	@ResponseStatus( HttpStatus.OK )
	public String airlyInstalationMock() {
		return AIRLY_INSTALATIONS_JSON;
	}
	
	@RequestMapping(value = "/mocked_sunsetrise", method=GET)
	@ResponseBody
	@ResponseStatus( HttpStatus.OK )
	public String sunsetriseMock() {
		return SUN_RISE_SET_JSON;
	}
	
	@RequestMapping(value = "/mocked_accuweather", method=GET, produces = "application/json")
	@ResponseBody
	@ResponseStatus( HttpStatus.OK )
	public String accuWeatherMock() {
		return ACCU_WEATHER_JSON;
	}
}
