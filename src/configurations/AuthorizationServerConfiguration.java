package configurations;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
	
   private String clientId = "clientid";
   private String clientSecret = "clientsectet";
   private String grantType = "password";
   private String scopeRead = "read";
   private String scopeWrite = "write";
   private String resourceIds = "resourceids";
	
	@Autowired
	private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

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
            .secret(clientSecret)
            .authorizedGrantTypes(grantType, "refresh_token", "client_credentials")
            .accessTokenValiditySeconds(2*3600) 
            .refreshTokenValiditySeconds(2*3600)
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
