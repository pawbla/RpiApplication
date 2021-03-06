package configurations;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
	@Value("${security.oauth2.client.id}")
	private String clientId;
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
	@Value("${security.oauth2.client.grant-type}")
	private String[] grantType;
	@Value("${security.oauth2.client.resource-ids}")
	private String resourceIds;
	@Value("${security.oauth2.client.access-token-validity-seconds}")
	private int accessTokenValidity;
	@Value("${security.oauth2.client.refresh-token-validity-seconds}")
	private int refreshTokenValidity;
	
	private String scopeRead = "read";
	private String scopeWrite = "write";
	
	
	@Autowired
	private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;
    
    @Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception { 
    	oauthServer 
    		.tokenKeyAccess("permitAll()") 
    		.checkTokenAccess("isAuthenticated()"); 
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
    	configurer
        	.inMemory()
            .withClient(clientId)
            .secret(passwordEncoder.encode(clientSecret))
            .authorizedGrantTypes(grantType)
            .accessTokenValiditySeconds(accessTokenValidity) 
            .refreshTokenValiditySeconds(refreshTokenValidity)
            .scopes(scopeRead, scopeWrite)
            .resourceIds(resourceIds);
    }

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
	    enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
	    endpoints.tokenStore(tokenStore)
	             .accessTokenConverter(accessTokenConverter)
	             .tokenEnhancer(enhancerChain)
	             .authenticationManager(authenticationManager);
	}
}
