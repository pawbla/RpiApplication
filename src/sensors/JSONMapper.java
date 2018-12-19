package sensors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class JSONMapper {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	private ResponseEntity<String> resp;
	private JSONObject jsonObject;
	private DateFormat dateFormat;
	private int statusCode;
	private String dataAsString;
	
	public JSONMapper() {
		logger.debug("Create MeasuredValuesMapper.");
		dateFormat = new SimpleDateFormat("MM.dd HH:mm");
	}
	
	public void setResponse(ResponseEntity<String> resp) {
		this.resp = resp;
	}
	
	public void prepareDatas() {	
		logger.info("Convert response to object as a JSON.");
		try {
			logger.debug("Status code: " + resp.getStatusCodeValue());
			if (resp.getStatusCodeValue() == 200) {
				try { 
					jsonObject = new JSONObject(resp.getBody());
					dataAsString = dateFormat.format(new Date());
				} catch (JSONException e) {
					logger.warn("Unable to create jsonObject from datas: " + resp.getBody());
				}	
			}
			statusCode = resp.getStatusCodeValue();
		} catch (Exception e) {
			statusCode = 110;
			jsonObject = null;
			logger.warn("Unable to read Status Code Value. Exception" + e);		
		}		
	}
	
	public JSONObject getJSONObject() {
		return jsonObject;
	}
	
	public int getResponseCode() {
		return statusCode;
	}
	
	public String getDateAsString() {
		return dataAsString;
	}

}