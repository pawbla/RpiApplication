package homeSystem;

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
	
	public static void main(String[] args) {
        SpringApplication.run(EmbeddedApp.class, args);
        
	}	
}