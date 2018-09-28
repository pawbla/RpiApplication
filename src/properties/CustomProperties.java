package properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {
	private String ipInternalSensor;
	
	public String getIpInternalSensor() {
		return ipInternalSensor;
	}
	public void setIpInternalSensor(String ipInternalSensor) {
		this.ipInternalSensor = ipInternalSensor;
	}
}
