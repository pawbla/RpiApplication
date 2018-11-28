package sensors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import homeSystem.EmbeddedApp;

@Component
public class JSONMapper {
	
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	private ResponseEntity<String> resp;
	private JSONObject jsonObject;
	private Date date;
	private DateFormat dateFormat;
	private int statusCode;
	private String dataAsString;
	
	public JSONMapper() {
		logger.debug("Create MeasuredValuesMapper.");
		date = new Date();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public void setResponse(ResponseEntity<String> resp) {
		this.resp = resp;
	}
	
	public void prepareDatas() {
		try {
			if (resp.getStatusCodeValue() == 200) {
				try { 
					jsonObject = new JSONObject(resp.getBody());
					dataAsString = dateFormat.format(date);
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