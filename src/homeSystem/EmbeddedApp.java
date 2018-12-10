package homeSystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("configurations")
@EnableAutoConfiguration
@Configuration
@EnableScheduling
public class EmbeddedApp {
	
	private static final Logger logger = LogManager.getLogger(EmbeddedApp.class);
	
	public static void main(String[] args) {
		logger.info("************************************************************************");
		logger.info("********************** START EMBEDDED APPLICATION **********************");
		logger.info("**********************                            **********************");
		logger.info("************************************************************************");
		logger.info("User home directory: " + System.getProperty("user.home"));
		logger.info("User current working directory: " + System.getProperty("user.dir"));
        SpringApplication.run(EmbeddedApp.class, args);
        
	}	
}