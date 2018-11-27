package sensors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import homeSystem.EmbeddedApp;

public class MeasuredValuesMapper {
	
	/**
	 * Logger
	 */
	private static final Logger logger = LogManager.getLogger(EmbeddedApp.class);
	private ResponseEntity<String> resp;
	private HashMap<String,String> map;
	private ObjectMapper mapper;
	private Date date;
	private DateFormat dateFormat;
	private int statusCode;
	private String dataAsString;
	
	public MeasuredValuesMapper() {
		mapper = new ObjectMapper();	
		map = new HashMap<String,String>();
		date = new Date();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public void setResponse(ResponseEntity<String> resp) {
		this.resp = resp;
	}
	
	public Map<String,String> getMap() {
		try {
			if (resp.getStatusCodeValue() == 200) {
				try { 
					map = mapper.readValue(resp.getBody(), new TypeReference<HashMap<String,String>>(){});
				} catch (Exception e) {
					logger.warn("Unable to get datas collected from internal weather sensor caused by exception: " + e);
				}	
			}
			dataAsString = dateFormat.format(date);
			statusCode = resp.getStatusCodeValue();
		} catch (Exception e) {
			statusCode = 110;
			map = null;
			logger.warn("Unable to read Status Code Value. Exception" + e);		
		}
		return map;
	}
	
	public int getResponseCode() {
		return statusCode;
	}
	
	public String getDateAsString() {
		return dataAsString;
	}

}