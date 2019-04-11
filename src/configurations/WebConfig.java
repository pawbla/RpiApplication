package configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import sensors.services.SensorInterface;
import sensors.services.implementations.AirLySensorInformationService;
import sensors.services.implementations.AirLySensorService;
import sensors.services.implementations.SunRiseSetService;
import sensors.services.implementations.WeatherInternalSensorService;

@Configuration
@EnableWebMvc
@ComponentScan({"controllers", "properties", "sensors", "sysInfo", "hardware"})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * Custom values declaration
	 */
	@Value("${custom.ipInternalSensor}")
	private String ipInternalSensor; 
	@Value("${custom.intSensorPassword}")
	private String intSensorPassword;	
	@Value("${custom.ipAirLy}")
	private String ipAirLy; 
	@Value("${custom.ipAirLyInstallation}")
	private String ipAirLyInstallation; 
	@Value("${custom.apiKeyAirLy}")
	private String apiKeyAirLy;	
	@Value("${custom.ipSunSetRise}")
	private String ipSunSetRise;
	
	/**
	 * Thymeleaf resources path configuration
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/css/**").addResourceLocations("file:./WebContent/WEB-INF/lib/css/");
         registry.addResourceHandler("/img/**").addResourceLocations("file:./WebContent/WEB-INF/lib/img/");
    }
    
    /**
     * View controller configuration
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
       
    /**
     * Thymeleaf template resolvers and engines
     * @return
     */
    @Bean
    public ViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver() ;
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }
    
    @Bean
    public TemplateResolver templateResolver(){
    	SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    	templateResolver.setPrefix("file:./WebContent/WEB-INF/views/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
       
        return templateResolver;
    }  
    
    /**
     * Rest Teplate builder configuration
     * @param restTemplateBuilder
     * @return
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) 
    {
        return restTemplateBuilder
           .setConnectTimeout(2000)
           .setReadTimeout(2000)
           .build();
    } 
    
    /**
     * Sensors IP configuration
     * @return
     */
    
    @Bean
    @Qualifier("internal")
    public SensorInterface internal() {
    	return new WeatherInternalSensorService(ipInternalSensor, intSensorPassword);
    }   
    
    @Bean
    @Qualifier("airLy")
    public SensorInterface airLy() {
    	return new AirLySensorService(ipAirLy, apiKeyAirLy);
    }
    
    @Bean
    @Qualifier("airLyInfo")
    public SensorInterface airLyInfo() {
    	return new AirLySensorInformationService(ipAirLyInstallation, apiKeyAirLy);
    }
    
    @Bean
    @Qualifier("sunRiseSet")
    public SensorInterface sunRiseSet() {
    	return new SunRiseSetService(ipSunSetRise);
    }
    
    
	@Override
	public void configureDefaultServletHandling (DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}