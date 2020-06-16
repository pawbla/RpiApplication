package homeSystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("configurations")
@EnableAutoConfiguration
@Configuration
public class EmbeddedApp {	
	/**
	 * Logger
	 */
	private static final Logger logger = LogManager.getLogger("EmbeddedApp");
	
	public static void main(String[] args) {	
		System.out.println("User home directory: " + System.getProperty("user.home"));
		System.out.println("User current working directory: " + System.getProperty("user.dir"));
        SpringApplication.run(EmbeddedApp.class, args);

        //System.setProperty("java.awt.headless", "false");
	}	
}