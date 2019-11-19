package configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	//below variables should be moved to configuration before commit !!!!
	private String resourceIds = "resourceids";
	
	@Autowired
    private ResourceServerTokenServices tokenServices;
	
	@Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http 
    		.requestMatchers() 
    		.and() 
    		.authorizeRequests() 
    		.antMatchers("/", "/index", "/static/**").permitAll()
    		.antMatchers("/api/**") 
    		.authenticated();
    }
}
