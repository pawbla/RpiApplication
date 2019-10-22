package connectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import connectors.models.Request;

/**
 * Builder to create Request object
 * @author blach
 *
 */
@Component
public class RequestBuilder {
	
	private HttpMethod method;
	private HttpHeaders header;
	private String ip;
	private String body;
	
	public RequestBuilder() {
		header = new HttpHeaders();
	}
	
	public RequestBuilder setHttpMethod(HttpMethod method) {
		this.method = method;
		return this;
	}
	
	public RequestBuilder setURL(String ip) {
		this.ip = ip;
		return this;
	}
	
	public RequestBuilder addHeader(String key, String value) {
		header.add(key, value);
		return this;
	}
	
	public RequestBuilder addContentType(MediaType content) {
		header.setContentType(content);
		return this;
	}
	
	public RequestBuilder addBody(String body) {
		this.body = body;
		return this;
	}

	public Request build() {
		HttpEntity<Object> entity;
		if (StringUtils.isBlank(body)) {
			entity = new HttpEntity<>(header);
		} else {
			entity = new HttpEntity<>(body, header);
		}
		return new Request(method, entity , ip, body);
	}
}
