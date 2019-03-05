package homeSystem;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("configurations")
@EnableAutoConfiguration
@Configuration
public class EmbeddedApp {	
	
	public static void main(String[] args) {		
        SpringApplication.run(EmbeddedApp.class, args);
        
	}	
}