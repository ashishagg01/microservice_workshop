/**
 * 
 */
package com.gl.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Token.
 *
 * @author vikas.kumar3
 */
public class Token {
	/**
     * Instantiates a new oauth token model.
     */
    public Token() {
        // do nothing
    }

    /** The access token. */
    @JsonProperty("access_token")
    private String accessToken;

    /** The token type. */
    @JsonProperty("token_type")
    private String tokenType;

    /** The refresh token. */
    @JsonProperty("refresh_token")
    private String refreshToken;

    /** The expires in. */
    @JsonProperty("expires_in")
    private int expiresIn;

    /** The jti. */
    @JsonProperty("jti")
    private String jti;
    
    private String organization;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
}
