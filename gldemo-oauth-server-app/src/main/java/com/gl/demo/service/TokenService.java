package com.gl.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import com.gl.demo.model.Token;


/**
 * The Class TokenService.
 *
 * @author vikas.kumar3
 */
@Component
public class TokenService {
	
	/**
	 * Creates the token.
	 *
	 * @param response the response
	 * @return the token
	 */
	public static Token createToken(ResponseEntity<OAuth2AccessToken> response) {
		if (response == null) {
            throw new BadCredentialsException("Bad Credentials!!");
        }
        OAuth2AccessToken responseBody = response.getBody();
        Token token = new Token();
        token.setAccessToken(responseBody.getValue());
        token.setTokenType(responseBody.getTokenType());
        token.setRefreshToken(responseBody.getRefreshToken().getValue());
        token.setExpiresIn(responseBody.getExpiresIn());
        token.setJti((String) responseBody.getAdditionalInformation().get("jti"));
        token.setOrganization((String) responseBody.getAdditionalInformation().get("organization"));
        return token;
	}

}
