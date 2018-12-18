	package configurations;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	DataSource userDatabase;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("username").passwordParameter("password")
				.failureUrl("/login-error.html")
				.permitAll()
			
		.and()
			.logout()
			.logoutSuccessUrl("/")
		.and()			
			.httpBasic()
		.and()
			.authorizeRequests()
			.antMatchers("/registration", "/registrationRest", "/registrationCheck/*").permitAll()
			.antMatchers("/sysinfo").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/*").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
		.and()
			.exceptionHandling().authenticationEntryPoint(unauthenticatedRequestHandler());
	}
	
	@Bean
	UnauthenticatedRequestHandler unauthenticatedRequestHandler() {
	    return new UnauthenticatedRequestHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new StandardPasswordEncoder("a2z3tg");
	}
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.jdbcAuthentication()
		.dataSource(userDatabase)
		.usersByUsernameQuery("select username, password, enabled from users where username=?")
		.authoritiesByUsernameQuery("select username, role from roles where username=?")
		.passwordEncoder(passwordEncoder());
	}
	
	private static class UnauthenticatedRequestHandler implements AuthenticationEntryPoint {

	    @Override
	    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
	        if (request.getServletPath().startsWith("/weatherRest")) {
	            response.setStatus(403);
	        } else {
	            response.sendRedirect("/login");
	        }
	    }
	}
	

}
	