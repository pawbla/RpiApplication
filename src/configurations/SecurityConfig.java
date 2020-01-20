package configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//below variables should be moved to configuration before commit !!!
	private String signingKey = "testSigningKey"; 
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("pass")
			.roles("USER");
	}
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http 
	        .sessionManagement()
	        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
        		.csrf().disable()
        		.authorizeRequests()
        		.antMatchers("/**","/oauth/token").permitAll()
    		.and() 
    			.formLogin() 
    			.disable();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
       JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
       converter.setSigningKey(signingKey);
       return converter;
    }

    @Bean
    public TokenStore tokenStore() {
       return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    @Primary //Making this primary to avoid any accidental duplication with another token service instance of the same name
    public DefaultTokenServices tokenServices() {
       DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
       defaultTokenServices.setTokenStore(tokenStore());
       defaultTokenServices.setSupportRefreshToken(true);
       return defaultTokenServices;
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new StandardPasswordEncoder("a2z3tg");
	}
	

}
	