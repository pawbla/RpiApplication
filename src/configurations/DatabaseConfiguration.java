package configurations;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "dao" })
public class DatabaseConfiguration {
	
	private String dialect;
	private String show_sql;
	private String format_sql;
	
	public DatabaseConfiguration (@Value("${hibernate.dialect}") String dialect,
			@Value("${hibernate.show-sql}") String show_sql,
			@Value("${hibernate.format-sql}") String format_sql) {
		super();
		this.dialect = dialect;
		this.show_sql = show_sql;
		this.format_sql = format_sql;
	}
    
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[] { "dao.entities" });
        sessionFactory.setHibernateProperties(hibernateProperties());        
        return sessionFactory;
     }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", Boolean.valueOf(show_sql));
        properties.put("hibernate.format_sql", Boolean.valueOf(format_sql)); 
        return properties;        
    }
     
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}
