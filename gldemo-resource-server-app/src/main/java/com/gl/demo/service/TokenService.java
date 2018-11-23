package com.gl.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * The Class TokenService.
 *
 * @author vikas.kumar3
 */
@Component
public class TokenService {
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> fetchClaims(){
		HashMap<String, Object> claims = new HashMap<>();
		Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
		
		OAuth2AuthenticationDetails oauthDetails
	      = (OAuth2AuthenticationDetails) authentication.getDetails();
		
		if(!ObjectUtils.isEmpty(oauthDetails)) {
			claims = (HashMap<String, Object>) oauthDetails.getDecodedDetails();
		}
		
		return claims;
	}
}
