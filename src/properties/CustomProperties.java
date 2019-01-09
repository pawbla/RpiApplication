package properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {
	private String ipInternalSensor;
	private String ipExternalSensor;
	private String intSensorPassword;
	private String ipAirLy;
	private String apiKeyAirLy;
	
	public String getIpInternalSensor() {
		return ipInternalSensor;
	}
	public void setIpInternalSensor(String ipInternalSensor) {
		this.ipInternalSensor = ipInternalSensor;
	}
	public String getIpExternalSensor() {
		return ipExternalSensor;
	}
	public void setIpExternalSensor(String ipExternalSensor) {
		this.ipExternalSensor = ipExternalSensor;
	}
	public String getIntSensorPassword() {
		return intSensorPassword;
	}
	public void setIntSensorPassword(String intSensorPassword) {
		this.intSensorPassword = intSensorPassword;
	}
	public String getIpAirLy() {
		return ipAirLy;
	}
	public void setIpAirLy(String ipAirLy) {
		this.ipAirLy = ipAirLy;
	}
	public String getApiKeyAirLy() {
		return apiKeyAirLy;
	}
	public void setApiKeyAirLy(String apiKeyAirLy) {
		this.apiKeyAirLy = apiKeyAirLy;
	}
}
