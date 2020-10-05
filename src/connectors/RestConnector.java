package connectors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import connectors.models.Request;
import connectors.models.Response;
	
public class RestConnector implements RestInterface {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private RestTemplate rest;
	private Request request;
	private DateFormat dateFormat;
	private Response response;
	
	public RestConnector() {
		logger.info("Create REST Template object.");
		rest = new RestTemplate();
		response = new Response();
		dateFormat = new SimpleDateFormat("MM.dd HH:mm");
	}
	
	public void execute() {
		logger.debug("Execute connection to url " + request.getIp());
		ResponseEntity<String> resp = null;
		try {
			resp = getResponseEntity();
 			logger.debug("Received status code " + resp.getStatusCodeValue() + " for: " + request.getIp());
			if (resp.getStatusCodeValue() != 200) {
				response.setError(true);
				response.setModified(false);
				response.setErrorMsg(resp.getStatusCode().getReasonPhrase());
			}
			if (StringUtils.isNotBlank(resp.getBody())) {
				logger.trace("Received response for " + request.getIp() + " body: " + resp.getBody());
				response.setBody(resp.getBody());
				response.setDate(dateFormat.format(new Date()));
			}
		} catch (RestClientException e) {
			response.setResponseCode(520);
			response.setErrorMsg("Unknown error has occured: " + e.getMessage());
			response.setError(true);
			response.setModified(false);
			logger.warn("An exception has occured when executed connection to url " + request.getIp() + ": " + e.getMessage());
		}
	}
	
	private ResponseEntity<String> getResponseEntity() {
		ResponseEntity<String> resp = null;
		int iter = 0;
		while (iter < 3) {
			logger.debug("Get REST data for ip " + request.getIp() + " iteration " + iter);
			try {
				resp = rest.exchange(request.getIp(), request.getMethod(), request.getEntity(), String.class);
				if (resp.getStatusCodeValue() == 200) {
					logger.debug("Received message with response code 200 for service: " + request.getIp());
					break;
				}
			} catch (RestClientException e) {
				logger.warn("Unable to fetch datas from ip " + request.getIp() + " caused by exception: " + e);		
				throw new RestClientException(e.getMessage());
			} 
			iter++;
		}	
		return resp;
	}
	
	public void setRequest(Request request) {
		this.request = request;
	}
	
	public Response getResponse() {
		return response;
	}
	
}
