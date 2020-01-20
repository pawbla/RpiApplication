package configurations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

@Configuration
@EnableWebMvc
@ComponentScan({"controllers", "properties", "sensors", "sysInfo", "hardware",})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceResolver resolver = new ReactResourceResolver();
        registry.addResourceHandler("/**")
                .resourceChain(true)
                .addResolver(resolver);
	}
	
	public class ReactResourceResolver implements ResourceResolver {

        private static final String REACT_DIR = "./WebContent/react/";

        private static final String REACT_STATIC_DIR = "static";

        private Resource index = new FileSystemResource(REACT_DIR + "index.html");
        private List<String> rootStaticFiles = Arrays.asList("favicon.io",
                "asset-manifest.json", "manifest.json", "service-worker.js");

        @Override
        public Resource resolveResource(
            HttpServletRequest request, String requestPath,
            List<? extends Resource> locations, ResourceResolverChain chain) {
            return resolve(requestPath, locations);
        }

        @Override
        public String resolveUrlPath(
            String resourcePath, List<? extends Resource> locations,
            ResourceResolverChain chain) {

            Resource resolvedResource = resolve(resourcePath, locations);
            if (resolvedResource == null) {
                return null;
            }
            try {
                return resolvedResource.getURL().toString();
            } catch (IOException e) {
                return resolvedResource.getFilename();
            }
        }

        private Resource resolve(
            String requestPath, List<? extends Resource> locations) {

            if (requestPath == null) {
            	return null;
            }

            if (rootStaticFiles.contains(requestPath)
                    || requestPath.startsWith(REACT_STATIC_DIR)) {
                return new FileSystemResource(REACT_DIR + requestPath);
            } else
                return index;
        }
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new StandardPasswordEncoder("a2z3tg");
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
    
	@Override
	public void configureDefaultServletHandling (DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}