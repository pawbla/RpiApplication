package configurations;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbeddedTomcatConfiguration {

	private String additionalPort = "8081";

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.addAdditionalTomcatConnectors(additionalConnector());
		return tomcat;
	}

	private Connector additionalConnector() {
			Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
			connector.setScheme("http");
			connector.setPort(Integer.valueOf(additionalPort));
		return connector;
	}
}
