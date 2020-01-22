package configurations;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@Profile("dev")
public class DataSourceConfigurationDev {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
    @Bean 
    public DataSource dataSource() {
   	 logger.info("Create embedded database connection - for tests.");
   	 return new EmbeddedDatabaseBuilder()
   	 .setType(EmbeddedDatabaseType.H2)
   	 .addScript("classpath:/resources/database/schema.sql")
   	 .addScript("classpath:/resources/database/data.sql")
   	 .build();
    }
}
