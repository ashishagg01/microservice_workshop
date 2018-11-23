/**
 * 
 */
package com.gl.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * The Class Oauth2AuthorizationServer.
 *
 * @author vikas.kumar3
 */
@Configuration
public class Oauth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {
	
	/** The authentication manager. */
	@Autowired
    AuthenticationManager authenticationManager;
	
	/** The user details service. */
	@Autowired
    private UserDetailsService userDetailsService;
    
    
 	@Override
     public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
             .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()");
     }
 
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	clients.inMemory()
        .withClient("app1")
            .secret("secret")
            .authorizedGrantTypes("implicit","password", "authorization_code", "refresh_token")
            .scopes("read", "write")
            .accessTokenValiditySeconds(3600)// 1 hour
            .refreshTokenValiditySeconds(2592000)// 30 days

        .and()
        
        .withClient("app2")
            .secret("secret")
            .authorizedGrantTypes("password", "authorization_code", "refresh_token")
            .scopes("read", "write")
            .accessTokenValiditySeconds(3600)// 1 hour
            .refreshTokenValiditySeconds(2592000);// 30 days
      }
    
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
        endpoints.tokenStore(tokenStore())
            .pathMapping("/oauth/token", "/v1/oauth/token")
            .tokenEnhancer(tokenEnhancerChain)
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService);
    }
    
    /**
     * Access token converter.
     *
     * @return the jwt access token converter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
    	 final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
         final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
         converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
         return converter;
    }
   
    /**
     * Token services.
     *
     * @return the default token services
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
    
    /**
     * Token enhancer.
     *
     * @return the token enhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }
    
    /**
     * Token store.
     *
     * @return the token store
     */
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

}
