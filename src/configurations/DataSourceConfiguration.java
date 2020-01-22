package configurations;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile("prod")
public class DataSourceConfiguration {
	
	@Autowired
    Environment environment;
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Bean
	public DataSource dataSource() {
    	logger.info("Create mySQL databse connection.");
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl(environment.getProperty("spring.datasource.url"));
	    driverManagerDataSource.setUsername(environment.getProperty("spring.datasource.username"));
	    driverManagerDataSource.setPassword(environment.getProperty("spring.datasource.password"));
	    return driverManagerDataSource;
	}

}
