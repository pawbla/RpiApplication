package connectors;

import connectors.models.Connector;
import connectors.models.Response;
import notifications.wrapper.NotificationWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Scope("prototype")
public class RestConnector implements RestInterface {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private RestTemplate rest;
	private Connector connector;
	private Response response;
	private DateFormat dateFormat;

	private NotificationWrapper sensorErrorNotification;

	private static final int SENDER_ID = 4;

	@Autowired
	public RestConnector(@Qualifier("sensorError") NotificationWrapper sensorErrorNotification) {
		logger.info("Create REST Template object.");
		this.sensorErrorNotification = sensorErrorNotification;
		rest = new RestTemplate();
		dateFormat = new SimpleDateFormat("MM.dd HH:mm");
		response = new Response();
	}
	
	public void execute() {
		logger.debug("Execute connection to url " + connector.getRequest().getIp());
		ResponseEntity<String> resp = null;
		try {
			resp = getResponseEntity();
 			logger.debug("Received status code " + resp.getStatusCodeValue() + " for: " + connector.getRequest().getIp());
			if (resp.getStatusCodeValue() != 200) {
				response.setError(true);
				response.setModified(false);
				response.setErrorMsg(resp.getStatusCode().getReasonPhrase());
				sensorErrorNotification.notifyObserver(SENDER_ID, connector.getName() + " response Code " + response.getResponseCode());
			}
			if (StringUtils.isNotBlank(resp.getBody())) {
				logger.trace("Received response for " + connector.getRequest().getIp() + " body: " + resp.getBody());
				response.setBody(resp.getBody());
				response.setDate(dateFormat.format(new Date()));
			}
			response.setResponseCode(resp.getStatusCodeValue());
		} catch (RestClientException e) {
			response.setResponseCode(520);
			response.setErrorMsg("Unknown error has occured: " + e.getMessage());
			response.setError(true);
			response.setModified(false);
			sensorErrorNotification.notifyObserver(SENDER_ID, connector.getName() + " exception:  " + e.getMessage());
			logger.warn("An exception has occured when executed connection to url " + connector.getRequest().getIp() + ": " + e.getMessage());
		} finally {
			connector.setResponse(response);
		}
	}
	
	private ResponseEntity<String> getResponseEntity() {
		ResponseEntity<String> resp = null;
		int iter = 0;
		while (iter < 3) {
			logger.debug("Get REST data for ip " + connector.getRequest().getIp() + " iteration " + iter);
			try {
				resp = rest.exchange(connector.getRequest().getIp(), connector.getRequest().getMethod(), connector.getRequest().getEntity(), String.class);
				if (resp.getStatusCodeValue() == 200) {
					logger.debug("Received message with response code 200 for service: " + connector.getRequest().getIp());
					break;
				}
			} catch (RestClientException e) {
				logger.warn("Unable to fetch datas from ip " + connector.getRequest().getIp() + " caused by exception: " + e);		
				throw new RestClientException(e.getMessage());
			} 
			iter++;
		}	
		return resp;
	}
	
	public void setConnector(Connector connector) {
		this.connector = connector;
	}	
}
