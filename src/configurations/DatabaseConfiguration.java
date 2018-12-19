package configurations;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan({"users"})
public class DatabaseConfiguration {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    Environment environment;
	
    //MySQL DB connection
    @Profile("prod")
	@Bean
	public DriverManagerDataSource dataSource() {
    	logger.info("Create mySQL databse connection.");
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl(environment.getProperty("spring.datasource.url"));
	    driverManagerDataSource.setUsername(environment.getProperty("spring.datasource.username"));
	    driverManagerDataSource.setPassword(environment.getProperty("spring.datasource.password"));
	    return driverManagerDataSource;
	}
    
	@Profile("prod")
	@Bean
	public JdbcTemplate jdbcTemplate (DataSource dataSource) {
	 return new JdbcTemplate(dataSource);
	}
         
     //database for test purpose - embedded DB
     @Profile("dev")
     @Bean 
     public DataSource eDataSource() {
    	 logger.info("Create embedded database connection - for tests.");
    	 return new EmbeddedDatabaseBuilder()
    	 .setType(EmbeddedDatabaseType.H2)
    	 .addScript("classpath:/resources/schema.sql")
    	 .addScript("classpath:/resources/data.sql")
    	 .build();
     }
     
     @Profile("dev")
     @Bean
     public JdbcTemplate jdbcTemplateE (DataSource eDataSource) {
    	 logger.info("Create embedded database connection");
    	 return new JdbcTemplate(eDataSource);
     }
}
