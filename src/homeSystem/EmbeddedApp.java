package homeSystem;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
        String log4jConfigFile = System.getProperty("user.dir") + File.separator + "log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);
		logger.info("************************************************************************");
		logger.info("********************** START EMBEDDED APPLICATION **********************");
		logger.info("**********************                            **********************");
		logger.info("************************************************************************");
		logger.info("User home directory: " + System.getProperty("user.home"));
		logger.info("User current working directory: " + System.getProperty("user.dir"));
        SpringApplication.run(EmbeddedApp.class, args);
        
	}	
}