package homeSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@ComponentScan("configurations")
@EnableAutoConfiguration(exclude=HibernateJpaAutoConfiguration.class) //fix for cast problem after update Spring
@Configuration
public class EmbeddedApp {	
	
	public static void main(String[] args) {	
        SpringApplication.run(EmbeddedApp.class, args);
        //System.setProperty("java.awt.headless", "false");
	}	
}